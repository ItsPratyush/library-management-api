package com.example.library.management.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BorrowerRepository borrowerRepository;
	
	public String borrowBook(Long bookId, Long borrowerId) {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		Optional<Borrower> borrowerOpt = borrowerRepository.findById(borrowerId);
		
		if (bookOpt.isEmpty() || borrowerOpt.isEmpty()) return "Borrower or Book not found!";
		
		Book book = bookOpt.get();
		Borrower borrower = borrowerOpt.get();
		
		if (!book.getIsAvailable()) return "Book isn't available!";
		
		// borrowing logic
		book.setIsAvailable(false);
		book.setBorrower(borrower);
		
		bookRepository.save(book);
		return "Book borrowed successfully!";
	}
	
	public String returnBook(Long bookId) {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		
		if (bookOpt.isEmpty()) return "Book not found!";
		
		Book book = bookOpt.get();
		
		if (book.getIsAvailable()) return "Book is already available!";
		
		book.setIsAvailable(true);
		book.setBorrower(null);
		
		bookRepository.save(book);
		return "Book returned successfully!";
	}
	
	public List<Book> searchBooksByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
	public List<Book> searchBooksByAuthor(String authorName) {
		return bookRepository.findByAuthorName(authorName);
	}
	
	public List<Book> getBorrowedBooks(Long borrowerId) {
		Optional<Borrower> borrowerOpt = borrowerRepository.findById(borrowerId);
		
		if (borrowerOpt.isEmpty()) return List.of();
		
		return borrowerOpt.get().getBorrowedBooks();
	}

}
