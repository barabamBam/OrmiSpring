package com.barabam.restaurant.dto;

import java.io.Serializable;

import com.barabam.restaurant.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO for {@link com.barabam.restaurant.entity.Customer} */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
	private long id;
  	private String name;
  	private long phoneNumber;
	private String address;

  public static CustomerDto toDto(Customer customer){
	  return CustomerDto.builder()
		  		.id(customer.getId())
			  	.name(customer.getName())
			  	.phoneNumber(customer.getPhoneNumber())
			  	.address(customer.getAddress())
		      	.build();
  }

  public Customer toEntity() {
	  return new Customer(
		  this.id,
		  this.name,
		  this.phoneNumber,
		  this.address
	  );
  }

}
