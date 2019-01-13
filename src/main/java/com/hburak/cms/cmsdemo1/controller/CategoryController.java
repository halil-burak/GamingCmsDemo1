package com.hburak.cms.cmsdemo1.controller;

import com.hburak.cms.cmsdemo1.entity.Category;
import com.hburak.cms.cmsdemo1.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getCategories() {
        List<Category> categoryList = categoryService.retrieveCategories();
        logger.info("Retrieving categories");
        return categoryList;
    }

    @PostMapping("/")
    public void addCategory(Category category) {
        logger.info("Adding a category");
        categoryService.saveCategory(category);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Retrieving a category.");
        Category category = categoryService.retrieveCategory(id);
        return category;
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Deleting a category.");
        categoryService.deleteCategory(id);
    }
}
