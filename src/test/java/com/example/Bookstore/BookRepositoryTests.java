package com.example.Bookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTests {
	
	@Autowired
	private BookRepository repo;
	@Autowired
	private CategoryRepository catRepo;
	
	@Test
	public void findAllFindsPresetBooks() {
		List<Book> books = (List<Book>) repo.findAll();
		List<Category> cats = (List<Category>) catRepo.findAll();
		
		
		assertThat(books).hasSize(5);
		assertThat(books.get(0).getAuthor()).isEqualTo("Jussi");
		assertThat(books.get(1).getTitle()).isEqualTo("mad ventures");
		assertThat(books.get(2).getYear()).isEqualTo(2005);
		assertThat(books.get(3).getIsbn()).isEqualTo("0-2330-3763-5");
		assertThat(books.get(4).getCategory()).isEqualTo(cats.get(2));
	}
	
	//create
	@Test
	public void createNewBook() {
		List<Category> cats = (List<Category>) catRepo.findAll();
		Book newBook = new Book("ttle", "auth", 2000, "isbn", 15, cats.get(0));
		repo.save(newBook);
		assertThat(newBook.getId()).isNotNull();
	}
	
	//delete
	@Test
	public void deleteFirstBookById() {
		List<Book> books = (List<Book>) repo.findAll();
		long id = books.get(0).getId();
		repo.deleteById(id);
		assertThat(repo.findAll()).hasSize(4);
	}
	
	//search
	@Test
	public void findByTitle() {
		List<Book> books = (List<Book>) repo.findAll();
		long id = books.get(0).getId();
		String title = books.get(0).getTitle();
		Book foundBook = repo.findById(id).get();
		assertThat(foundBook.getTitle()).isEqualTo(title);
	}
	
}
