package com.barabam.restaurant.controller;

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
import com.barabam.restaurant.entity.Menu;
import com.barabam.restaurant.service.MenuService;

@RestController
@RequestMapping("/api/restaurant/menu")
public class MenuController {

	private MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	public ResponseEntity<List<MenuDto>> getAllMenu() {
		List<MenuDto> menuDtos = menuService.getAllMenu();
		return ResponseEntity.ok(menuDtos);
	}

	@PostMapping
	public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
		MenuDto createMenuDto = menuService.createMenu(menuDto);
		return ResponseEntity.ok(createMenuDto);
	}

	@PutMapping("{id}")
	public ResponseEntity<MenuDto> updateMenu(@PathVariable("id") int id, @RequestBody MenuDto updateMenuDto) {
		return menuService.updateMenu(id, updateMenuDto)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteMenu(@PathVariable("id") int id) {
    return menuService.deleteMenu(id)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
	}

	@GetMapping("{category}")
	public ResponseEntity<List<MenuDto>> getMenuByCategory(@PathVariable String category)  {
		List<MenuDto> menus = menuService.getMenuByCategory(category);
		return ResponseEntity.ok(menus);
	}

}
