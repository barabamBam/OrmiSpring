package com.barabam.restaurant.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.barabam.restaurant.dto.OrderDto;
import com.barabam.restaurant.entity.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link Store} */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StoreDto {
  private int id;
  private String name;
  private long phoneNumber;
  private String address;
  private List<OrderDto> orders;

  public static StoreDto toDto(Store store) {
    return StoreDto.builder()
        .id(store.getId())
        .name(store.getName())
        .phoneNumber(store.getPhoneNumber())
        .address(store.getAddress())
        .orders(store.getOrders().stream().map(OrderDto::toDto).collect(Collectors.toList()))
        .build();
  }

  public Store toEntity() {
    return new Store(
        this.id,
        this.name,
        this.phoneNumber,
        this.address,
        this.orders.stream().map(OrderDto::toEntity).collect(Collectors.toList())
    );
  }
}
