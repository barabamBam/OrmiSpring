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

import com.barabam.restaurant.dto.MenuDto;
import com.barabam.restaurant.dto.OrderItemDto;
import com.barabam.restaurant.entity.OrderItem;
import com.barabam.restaurant.service.OrderItemService;

@RestController
@RequestMapping("/api/restaurant/orderitem")

public class OrderItemController {

	private OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@GetMapping
	public ResponseEntity<List<OrderItemDto>> getAllItem() {
		List<OrderItemDto> orderItemDtos = orderItemService.getAllItem();
		return ResponseEntity.ok(orderItemDtos);
	}

	@PostMapping
	public ResponseEntity<OrderItemDto> createItem(@RequestBody OrderItemDto orderItemDto) {
		OrderItemDto createOrderItemDto = orderItemService.createItem(orderItemDto);
		return ResponseEntity.ok(createOrderItemDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<OrderItemDto> updateItem(@PathVariable("id") Long id, @RequestBody OrderItemDto updateOrderItemDto) {
		return orderItemService.updateItem(id, updateOrderItemDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
		return orderItemService.deleteItem(id)
		? ResponseEntity.noContent().build()
		: ResponseEntity.notFound().build();

	}

	@GetMapping("/top3")
	public ResponseEntity<List<MenuDto>> getTop3Menu() {
		List<MenuDto> menuDtos = orderItemService.getTopThreeMenu();
		return ResponseEntity.ok(menuDtos);
	}
}
