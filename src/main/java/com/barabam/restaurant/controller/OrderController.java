package com.barabam.restaurant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barabam.restaurant.dto.OrderDto;
import com.barabam.restaurant.service.OrderService;

@RestController
@RequestMapping("/api/restaurant/order")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> orderDtos = orderService.getAllOrders();
		return ResponseEntity.ok(orderDtos);
	}

	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
		OrderDto createOrderDto = orderService.createOrder(orderDto);
		return ResponseEntity.ok(createOrderDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto updateOrderDto) {
		return orderService.updateOrder(id, updateOrderDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
		return orderService.deleteOrder(id)
		? ResponseEntity.noContent().build()
		: ResponseEntity.notFound().build();

	}
}
