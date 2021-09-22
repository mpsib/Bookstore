package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping("/booklist")
	public String listPage(Model model) {		
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	@GetMapping("/addbook")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}
	
	@PostMapping("/addbook")
	public String addBookSave(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	
	@GetMapping("/editbook/{id}")
	public String editBookForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	
	@PostMapping("/editbook")
	public String editBookSave(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
}
