package com.barabam.restaurant.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barabam.restaurant.dto.MenuDto;
import com.barabam.restaurant.dto.StoreDto;
import com.barabam.restaurant.entity.Store;
import com.barabam.restaurant.service.StoreService;

@RestController
@RequestMapping("/api/restaurant/store")
public class StoreController {

	private StoreService storeService;

	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	@GetMapping
	public ResponseEntity<List<StoreDto>> getAllStores() {
		List<StoreDto> storeDtos = storeService.getAllStores();
		return ResponseEntity.ok(storeDtos);
	}

	@PostMapping
	public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) {
		StoreDto createStoreDto = storeService.createStore(storeDto);
		return ResponseEntity.ok(createStoreDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<StoreDto> updateStore(@PathVariable("id") int id, @RequestBody StoreDto updateStoreDto) {
		return storeService.updateStore(id, updateStoreDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteStore(@PathVariable("id") int id) {
    return storeService.deleteStore(id)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
	}

	/*@GetMapping("/sales")
	public ResponseEntity<Map<StoreDto, Long>> getSales(
		@RequestParam("startdate") LocalDate startdate,
		@RequestParam("enddate") LocalDate enddate
	) {
		Map<StoreDto, Long> sales = storeService.getSales(startdate, enddate);
		return ResponseEntity.ok(sales);
	}*/

}
