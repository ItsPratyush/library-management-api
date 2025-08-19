package com.example.library.management.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author inputAuthor(@RequestBody Author author) {
		return authorRepository.save(author);
	}
	
	@PostMapping("/books/{bookId}/borrower/{borrowerId}")
	public String borrowBook(@PathVariable Long bookId, @PathVariable Long borrowerId) {
		return libraryService.borrowBook(bookId, borrowerId);
	}
	
	@PostMapping("/books/{bookId}/return")
	public String returnBook(@PathVariable Long bookId) {
		return libraryService.returnBook(bookId);
	}
	
	@GetMapping("/books/search")
	public List<Book> searchBooks(@RequestParam String title, @RequestParam String author) {
		if (title != null) {
			return libraryService.searchBooksByTitle(title);
		} else if (author != null) {
			return libraryService.searchBooksByAuthor(title);
		} else throw new RuntimeException("Please provide a search parameter (title or author)");
	}
	
	@GetMapping("/borrower/{borrowerId}/books")
	public List<Book> getBorrowedBooks(@PathVariable Long borrowerId) {
		return libraryService.getBorrowedBooks(borrowerId);
	}

}