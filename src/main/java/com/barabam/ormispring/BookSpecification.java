package com.barabam.ormispring;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BookSpecification {

	public static Specification<Book> likeBookTitle(String title) {
    return new Specification<Book>() {
      @Override
      public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        System.out.println("title = "+ title);
        return cb.like(root.get("title"), "%" + title + "%");
      }
    };
	}

	public static Specification<Book> likeBookAuthor(String author) {
    return new Specification<Book>() {
      @Override
      public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        System.out.println("author = "+ author);
        return cb.like(root.get("author"), "%" + author + "%");
      }
    };
	}

	public static Specification<Book> equalBookStock (int stock) {
		return new Specification<Book>() {
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				System.out.println("stock = "+ stock);
				return cb.equal(root.get("stock"), stock);
			}
		};
	}
}
