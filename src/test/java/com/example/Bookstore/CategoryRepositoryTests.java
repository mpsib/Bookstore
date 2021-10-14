package com.example.Bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository catRepo;
	
	
	@Test
	public void findAllFindsPresetCategories() {
		List<Category> cats = (List<Category>) catRepo.findAll();
		
		assertThat(cats).hasSize(3);
		assertThat(cats.get(0).getName()).isEqualTo("Musiikki");
		assertThat(cats.get(1).getName()).isEqualTo("Viihde");
		assertThat(cats.get(2).getName()).isEqualTo("Oppikirja");
	}
	
	//create
	@Test
	public void createNewCategory() {
		Category newCat = new Category("Historia");
		catRepo.save(newCat);
		assertThat(newCat.getId()).isNotNull();
	}
	

	//delete
	@Test
	public void deleteFirstBookById() {
		List<Category> cats = (List<Category>) catRepo.findAll();
		long id = cats.get(0).getId();
		catRepo.deleteById(id);
		assertThat(catRepo.findAll()).hasSize(2);
	}
	
	//search
	@Test
	public void findByName() {
		List<Category> cats = (List<Category>) catRepo.findAll();
		long id = cats.get(0).getId();
		String name = cats.get(0).getName();
		Category foundCategory = catRepo.findById(id).get();
		assertThat(foundCategory.getName()).isEqualTo(name);
	}
}
