package com.barabam.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barabam.restaurant.entity.Order;
import com.barabam.restaurant.entity.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select sum(o.menu.price) from OrderItem o where o.order.orderId = ?1")
  long findByOrder_OrderId(long orderId);
}
