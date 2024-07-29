package com.barabam.restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barabam.restaurant.dto.MenuDto;
import com.barabam.restaurant.dto.OrderItemDto;
import com.barabam.restaurant.entity.OrderItem;
import com.barabam.restaurant.repository.CustomerRepository;
import com.barabam.restaurant.repository.OrderItemRepository;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderItemService(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}

	@Transactional(readOnly = true)
	public List<OrderItemDto> getAllItem() {
		return  orderItemRepository.findAll()
			.stream()
			.map(OrderItemDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public OrderItemDto createItem(OrderItemDto orderItemDto) {
		OrderItem orderItem = orderItemDto.toEntity();
		OrderItem saveItem = orderItemRepository.save(orderItem);
		return OrderItemDto.toDto(saveItem);
	}

	@Transactional
	public Optional<OrderItemDto> updateItem(Long id, OrderItemDto updateOrderItemDto) {
		return orderItemRepository.findById(id)
			.map(updatedItem -> {
				updatedItem.setOrder(updateOrderItemDto.getOrder());
				updatedItem.setMenu(updateOrderItemDto.getMenu());
				return OrderItemDto.toDto(orderItemRepository.save(updatedItem));
			});
	}

	@Transactional
	public boolean deleteItem(Long id) {
		return orderItemRepository.findById(id)
			.map(item -> {
				orderItemRepository.delete(item);
				return true;
			})
			.orElse(false);
	}

	@Transactional(readOnly = true)
	public List<MenuDto> getTopThreeMenu() {
		return orderItemRepository.getTopThreeMenu()
			.stream()
			.map(MenuDto::toDto)
			.collect(Collectors.toList());
	}
}
