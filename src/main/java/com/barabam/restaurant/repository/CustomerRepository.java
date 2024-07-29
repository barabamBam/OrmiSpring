package com.barabam.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barabam.restaurant.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
