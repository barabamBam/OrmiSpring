package com.barabam.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barabam.restaurant.dto.CustomerDto;
import com.barabam.restaurant.entity.Customer;
import com.barabam.restaurant.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional(readOnly = true)
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll()
			.stream().map(CustomerDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = customerDto.toEntity();
		Customer savedCustomer = customerRepository.save(customer);
		return CustomerDto.toDto(savedCustomer);
	}

	@Transactional
	public Optional<CustomerDto> updateCustomer(Long id, CustomerDto customerDto) {
		return customerRepository.findById(id)
			.map(updatedCustomer -> {
				updatedCustomer.setName(customerDto.getName());
				updatedCustomer.setPhoneNumber(customerDto.getPhoneNumber());
				updatedCustomer.setAddress(customerDto.getAddress());
				return CustomerDto.toDto(customerRepository.save(updatedCustomer));
			});
	}

	@Transactional
	public boolean deleteCustomer(Long id) {
		return customerRepository.findById(id)
			.map(customer -> {
				customerRepository.delete(customer);
				return true;
			})
			.orElse(false);
	}
}
