package com.barabam.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barabam.restaurant.entity.Customer;
import com.barabam.restaurant.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {


  @Query("select m from Menu m where upper(m.category) = upper(?1)")
  List<Menu> getMenuByCategory(String category);
}
