package com.barabam.restaurant.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private long phoneNumber;

	@Column(nullable = false)
	private String address;

	@OneToMany(mappedBy = "store")
	private List<Order> orders = new ArrayList<>();

	public void addOrder(Order order) {
		orders.add(order);
		order.setStore(this);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
		order.setStore(null);
	}
}
