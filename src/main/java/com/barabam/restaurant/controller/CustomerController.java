package com.barabam.restaurant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barabam.restaurant.dto.CustomerDto;
import com.barabam.restaurant.entity.Customer;
import com.barabam.restaurant.service.CustomerService;

@RestController
@RequestMapping("/api/restaurant/customer")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<CustomerDto> customerDtos = customerService.getAllCustomers();
		return ResponseEntity.ok(customerDtos);
	}

	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		CustomerDto createCustomerDto = customerService.createCustomer(customerDto);
		return ResponseEntity.ok(createCustomerDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto updateCustomerDto) {
		return customerService.updateCustomer(id, updateCustomerDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
		return customerService.deleteCustomer(id)
			? ResponseEntity.noContent().build()
			: ResponseEntity.notFound().build();
	}

}
