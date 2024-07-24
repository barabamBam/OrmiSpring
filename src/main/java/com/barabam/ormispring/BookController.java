package com.barabam.ormispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// CREATE: 새 책 추가
	@PostMapping
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
		BookDTO createdBook = bookService.createBook(bookDTO);
		return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
	}

	// READ: 모든 책 조회
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		List<BookDTO> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	// READ: 특정 ID의 책 조회
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return bookService.getBookById(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// UPDATE: 책 정보 업데이트
	@PutMapping("/{id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		return bookService.updateBook(id, bookDTO)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// DELETE: 책 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		boolean deleted = bookService.deleteBook(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	// READ: ISBN으로 책 검색
	@GetMapping("/search/isbn")
	public ResponseEntity<BookDTO> searchBookByIsbn(@RequestParam String isbn) {
		return bookService.getBookByIsbn(isbn)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	// READ: 가격범위로 책 검색
	@GetMapping("/price-range")
	public ResponseEntity<List<BookDTO>> getBooksByPriceRange(
		@RequestParam Double minPrice,
		@RequestParam Double maxPrice) {
		List<BookDTO> books = bookService.getBooksByPriceRange(minPrice, maxPrice);
		return ResponseEntity.ok(books);
	}
	// READ: 재고수량으로 책 검색
	@GetMapping("/in-stock")
	public ResponseEntity<List<BookDTO>> getBooksWithStockGreaterThan(@RequestParam Integer minStock) {
		List<BookDTO> books = bookService.getBooksWithStockGreaterThan(minStock);
		return ResponseEntity.ok(books);
	}
	// READ: 특정 키워드가 포함된 책 검색
	@GetMapping("/search/title")
	public ResponseEntity<List<BookDTO>> getBooksByTitleContaining(@RequestParam String keyword) {
		List<BookDTO> books = bookService.getBooksByTitleContaining(keyword);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/search")
	public ResponseEntity<List<BookDTO>> getBooksByTitleContaining(@ModelAttribute SearchData searchData) {
		List<BookDTO> books = bookService.findByTitleContainsIgnoreCaseAndAuthorContainsIgnoreCase(searchData);
		return ResponseEntity.ok(books);
	}

}
