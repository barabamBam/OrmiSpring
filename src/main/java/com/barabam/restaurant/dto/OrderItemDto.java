package com.barabam.restaurant.dto;

import java.io.Serializable;

import com.barabam.restaurant.entity.Menu;
import com.barabam.restaurant.entity.Order;
import com.barabam.restaurant.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link com.barabam.restaurant.entity.OrderItem} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemDto {
  private long id;
  private Order order;
  private Menu menu;

  public static OrderItemDto toDto(OrderItem orderItem) {
    return OrderItemDto.builder()
        .id(orderItem.getId())
        .order(orderItem.getOrder())
        .menu(orderItem.getMenu())
        .build();
  }

  public OrderItem toEntity() {
    return new OrderItem(
        this.id,
        this.order,
        this.menu
    );
  }
}
