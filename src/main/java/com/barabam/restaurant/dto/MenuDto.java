package com.barabam.restaurant.dto;

import java.io.Serializable;

import com.barabam.restaurant.entity.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** DTO for {@link com.barabam.restaurant.entity.Menu} */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
  private int id;
  private String name;
  private String category;
  private int price;
  private String description;

  public static MenuDto toDto(Menu menu) {
    return MenuDto.builder()
        .id(menu.getId())
        .name(menu.getName())
        .category(menu.getCategory())
        .price(menu.getPrice())
        .description(menu.getDescription())
        .build();
  }

  public Menu toEntity() {
    return new Menu(
        this.id,
        this.name,
        this.category,
        this.price,
        this.description
    );
  }
}
