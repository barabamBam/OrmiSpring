package com.barabam.restaurant.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.barabam.restaurant.entity.Order;
import com.barabam.restaurant.dto.CustomerDto;
import com.barabam.restaurant.dto.OrderItemDto;
import com.barabam.restaurant.dto.StoreDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link com.barabam.restaurant.entity.Order} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
  private long orderId;
  private CustomerDto customer;
  private StoreDto store;
  private Order.Status orderStatus;
  private LocalDate orderDate;
  private long totalPrice;
  private List<OrderItemDto> orderItems;

  public static OrderDto toDto(Order order) {
    return OrderDto.builder()
        .orderId(order.getOrderId())
        .customer(CustomerDto.toDto(order.getCustomer()))
        .store(StoreDto.toDto(order.getStore()))
        .orderStatus(order.getStatus())
        .orderDate(order.getOrderDate())
        .totalPrice(order.getTotalPrice())
        .orderItems(order.getOrderItems().stream().map(OrderItemDto::toDto).collect(Collectors.toList()))
        .build();
  }

  public Order toEntity() {
    return new Order(
        this.orderId,
        this.customer.toEntity(),
        this.store.toEntity(),
        this.orderStatus,
        this.orderDate,
        this.totalPrice,
        this.orderItems.stream().map(OrderItemDto::toEntity).collect(Collectors.toList())
    );
  }
}
