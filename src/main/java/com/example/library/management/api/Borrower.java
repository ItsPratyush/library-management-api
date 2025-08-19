package com.example.library.management.api;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity @Data
public class Borrower {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(
	    name = "borrower_books",  // The join table
	    joinColumns = @JoinColumn(name = "borrower_id"),
	    inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private List<Book> borrowedBooks = new ArrayList<>();

}
