package com.barabam.restaurant.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barabam.restaurant.dto.StoreDto;
import com.barabam.restaurant.dto.OrderDto;
import com.barabam.restaurant.entity.Store;
import com.barabam.restaurant.repository.CustomerRepository;
import com.barabam.restaurant.repository.StoreRepository;

@Service
public class StoreService {

	private final StoreRepository storeRepository;

	@Autowired
	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Transactional(readOnly = true)
	public List<StoreDto> getAllStores() {
		return storeRepository.findAll().stream()
			.map(StoreDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public StoreDto createStore(StoreDto storeDto) {
		Store store = storeDto.toEntity();
		Store saveStore = storeRepository.save(store);
		return StoreDto.toDto(saveStore);
	}

	@Transactional
	public Optional<StoreDto> updateStore(int id, StoreDto updateStoreDto) {
    return storeRepository
        .findById(id)
        .map(
            updatedStore -> {
              updatedStore.setName(updateStoreDto.getName());
              updatedStore.setPhoneNumber(updateStoreDto.getPhoneNumber());
              updatedStore.setAddress(updateStoreDto.getAddress());
              updatedStore.setOrders(
                  updateStoreDto.getOrders().stream()
                      .map(OrderDto::toEntity)
                      .collect(Collectors.toList()));
              return StoreDto.toDto(storeRepository.save(updatedStore));
            });
	}

	@Transactional
	public boolean deleteStore(int id) {
		return storeRepository.findById(id)
			.map(store -> {
				storeRepository.delete(store);
				return true;
			})
			.orElse(false);
	}

	/*@Transactional(readOnly = true)
	public Map<StoreDto, Long> getSales(LocalDate startDate, LocalDate endDate) {
		Map<StoreDto, Long> map = new HashMap<>();
		storeRepository.getSales(startDate, endDate)
			.stream()
			.map(StoreDto::toDto)
		;
		return map;
	}*/
}
