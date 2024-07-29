package com.barabam.restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barabam.restaurant.dto.OrderItemDto;
import com.barabam.restaurant.entity.Order;
import com.barabam.restaurant.dto.OrderDto;
import com.barabam.restaurant.repository.OrderRepository;


@Service
public class OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Transactional(readOnly = true)
	public List<OrderDto> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
    	orders.forEach(this::totalPrice);
		return orders.stream().map(OrderDto::toDto)
			.collect(Collectors.toList());
	}

	private void totalPrice(Order order) {
		if (order.getStatus() == Order.Status.completed) {
			order.setTotalPrice(orderRepository.findByOrder_OrderId(order.getOrderId()));
		}
	}

	@Transactional
	public OrderDto createOrder(OrderDto orderDto) {
		Order order = orderDto.toEntity();
		Order savedOrder = orderRepository.save(order);
		return OrderDto.toDto(savedOrder);
	}

	@Transactional
	public Optional<OrderDto> updateOrder(Long id, OrderDto updateOrderDto) {
		return orderRepository.findById(id)
			.map(updatedOrder -> {
				updatedOrder.setCustomer(updateOrderDto.getCustomer().toEntity());
				updatedOrder.setStore(updateOrderDto.getStore().toEntity());
				updatedOrder.setStatus(updateOrderDto.getOrderStatus());
				updatedOrder.setOrderDate(updateOrderDto.getOrderDate());
				updatedOrder.setOrderItems(
					updateOrderDto.getOrderItems()
					.stream().map(OrderItemDto::toEntity)
					.collect(Collectors.toList()));
				totalPrice(updatedOrder);
				return OrderDto.toDto(orderRepository.save(updatedOrder));
			});
	}

	@Transactional
	public boolean deleteOrder(Long id) {
		return orderRepository.findById(id)
			.map(order -> {
				orderRepository.delete(order);
				return true;
			})
			.orElse(false);
	}
	
}
