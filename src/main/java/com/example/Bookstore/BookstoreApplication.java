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
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner studentDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			
			//Usernames are UNIQUE, so you must deleteAll in Heroku. For example urepository.deleteAll() in your CommandLineRunner.
			userRepository.deleteAll();
			
			//loggausjutut otettu Hinkulan esimerkistä
			log.info("Add example books");
			
			Category cat1 = new Category("Musiikki");
			Category cat2 = new Category("Viihde");
			Category cat3 = new Category("Oppikirja");
			categoryRepository.save(cat1);
			categoryRepository.save(cat2);
			categoryRepository.save(cat3);
			bookRepository.save(new Book("rumpukirja", "Jussi", 1991, "0-8638-3706-9", 0, cat1));
			bookRepository.save(new Book("mad ventures", "Riku R", 1992, "0-7830-3379-6", 0, cat2));	
			bookRepository.save(new Book("Sitä sun tätä", "Maija Maijanen", 2005, "0-7520-3879-6", 0, cat2));	
			bookRepository.save(new Book("Koirat IRL", "Maija Maijanen", 2012, "0-2330-3763-5", 0, cat3));	
			bookRepository.save(new Book("Front and Back", "Teemu Toiminen", 2014, "0-1947-38769-2", 0, cat3));	
			
			User user1 = new User("user", "$2a$10$8RlLO1MZmOA3pgUI7iq1i.8Qld7/DrBFaNWaRe2l1f84GhNs8F0J2", "USER");
			User admin1 = new User("admin", "$2a$10$SUx2Q1/z5Kfn60CdIw5.ouj4o.z1dvS2TRXL4z/uhTFq8BWXPUiBe", "ADMIN");
			userRepository.save(user1);
			userRepository.save(admin1);
			
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
