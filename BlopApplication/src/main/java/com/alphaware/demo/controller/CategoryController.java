package com.alphaware.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphaware.demo.models.Category;
import com.alphaware.demo.service.CategoryService;



@RestController
@RequestMapping("/api/category")
public class CategoryController {

	 @Autowired
	 private CategoryService categoryService;
	
	 @GetMapping
	 public ResponseEntity<List<Category>> getAllCategories() {
	     List<Category> categories = categoryService.getAllCategories();
	     return new ResponseEntity<>(categories, HttpStatus.OK);
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	     Category category = categoryService.getCategoryById(id);
	     return new ResponseEntity<>(category, HttpStatus.OK);
	 }
	
	 @PostMapping
	 public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	     Category createdCategory = categoryService.createCategory(category);
	     return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	 }
	
	 @PutMapping("/{id}")
	 public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	     Category updatedCategory = categoryService.updateCategory(id, category);
	     return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	 }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	     categoryService.deleteCategory(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
}