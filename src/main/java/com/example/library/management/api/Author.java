package com.example.library.management.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "authors") @Data
public class Author {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String title;
	String Author;
	
	Boolean isAvailable;

}
