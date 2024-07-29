package com.barabam.restaurant.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barabam.restaurant.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

/*  @Query("select s, sum(orders.totalPrice) from Store s inner join s.orders orders "
      + "where orders.orderDate between ?1 and ?2")
  List<Store> getSales (
      LocalDate orderDateStart, LocalDate orderDateEnd);*/
}
