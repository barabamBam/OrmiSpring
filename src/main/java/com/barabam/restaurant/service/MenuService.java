package com.barabam.restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barabam.restaurant.dto.CustomerDto;
import com.barabam.restaurant.dto.MenuDto;
import com.barabam.restaurant.entity.Menu;
import com.barabam.restaurant.repository.CustomerRepository;
import com.barabam.restaurant.repository.MenuRepository;

@Service
public class MenuService {

	private final MenuRepository menuRepository;

	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Transactional(readOnly = true)
	public List<MenuDto> getAllMenu() {
		return menuRepository.findAll()
			.stream().map(MenuDto::toDto)
			.collect(Collectors.toList());
	}

	@Transactional
	public MenuDto createMenu(MenuDto menuDto) {
		Menu menu = menuDto.toEntity();
		Menu savedMenu = menuRepository.save(menu);
		return MenuDto.toDto(savedMenu);
	}

	@Transactional
	public Optional<MenuDto> updateMenu(int id, MenuDto updateMenuDto) {
    return menuRepository
        .findById(id)
        .map(
            updatedMenu -> {
              updatedMenu.setName(updateMenuDto.getName());
              updatedMenu.setCategory(updateMenuDto.getCategory());
              updatedMenu.setDescription(updateMenuDto.getDescription());
              updatedMenu.setPrice(updateMenuDto.getPrice());
              return MenuDto.toDto(menuRepository.save(updatedMenu));
            });
	}

	@Transactional
	public boolean deleteMenu(int id) {
		return menuRepository.findById(id)
			.map(menu -> {
				menuRepository.delete(menu);
				return true;
			})
			.orElse(false);
	}

	@Transactional(readOnly = true)
	public List<MenuDto> getMenuByCategory(String category) {
		return menuRepository.getMenuByCategory(category)
			.stream()
			.map(MenuDto::toDto)
			.collect(Collectors.toList());
	}
}
