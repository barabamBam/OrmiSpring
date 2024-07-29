package com.barabam.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barabam.restaurant.entity.Menu;
import com.barabam.restaurant.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
  @Query("select o.menu, count(*) menu from OrderItem o group by o.menu.id order by menu desc limit 3")
  List<Menu> getTopThreeMenu();

}
