package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			
			//loggausjutut otettu Hinkulan esimerkistä
			log.info("Add example books");
			
			Category cat1 = new Category("Musiikki");
			Category cat2 = new Category("Viihde");
			Category cat3 = new Category("Oppikirja");
			categoryRepository.save(cat1);
			categoryRepository.save(cat2);
			categoryRepository.save(cat3);
			bookRepository.save(new Book("rumpukirja", "jussi", 1991, "0-8638-3706-9", 0, cat1));
			bookRepository.save(new Book("mad ventures", "riku", 1992, "0-7830-3379-6", 0, cat2));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			

			log.info("fetch all categories");
			for (Category cat : categoryRepository.findAll()) {
				log.info(cat.toString());
			}

		};
	}
}
