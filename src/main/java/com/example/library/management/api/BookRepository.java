package com.example.library.management.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitle(String Title);
	
	List<Book> findByAuthorName(String authorName);

}
