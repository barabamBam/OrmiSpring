package com.barabam.ormispring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
	// ISBN으로 책 찾기
	Optional<Book> findByIsbn(String isbn);

	// 가격 범위로 책 목록 찾기
	List<Book> findByPriceBetween(Double minPrice, Double maxPrice);

	// 재고가 특정 수량 이상인 책 목록 찾기
	List<Book> findByStockGreaterThanEqual(Integer minStock);

	// 제목에 특정 키워드가 포함된 책 목록 찾기
	List<Book> findByTitleContaining(String keyword);
}