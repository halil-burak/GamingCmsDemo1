package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import com.turkcell.playcell.cms.cmsdemo1.service.CategoryService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Object> getCategories() {
        logger.info("Retrieving categories");
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        if (!categoryService.retrieveCategories().isEmpty()) {
            List<Category> categoryList = categoryService.retrieveCategories();
            for (Category category : categoryList) {
                JSONObject json = new JSONObject();
                json.put("id", category.getId());
                json.put("name", category.getName());
                json.put("url", category.getUrl());
                json.put("gamelist", category.getGameList());
                jsonObjects.add(json);
            }
        }
        return new ResponseEntity<Object>(jsonObjects.toString(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        logger.info("Adding a category");
        List<Category> categoryList = categoryService.retrieveCategories();
        JSONObject json = new JSONObject();
        for (Category ctg : categoryList) {
            if (ctg.getName().equals(category.getName())) {
                return new ResponseEntity<String>("There is already one category with this name.", HttpStatus.BAD_REQUEST);
            }
        }
        categoryService.saveCategory(category);
        json.put("id", category.getId());
        json.put("url", category.getUrl());
        json.put("name", category.getName());
        json.put("gameList", category.getGameList());
        return new ResponseEntity<Object>(json.toMap(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Retrieving a category.");
        if (categoryService.existsById(id)) {
            Category category = categoryService.retrieveCategory(id);
            JSONObject json = new JSONObject();
            json.put("id", category.getId());
            json.put("name", category.getName());
            json.put("url", category.getUrl());
            json.put("gamelist", category.getGameList());

            return new ResponseEntity<Object>(json.toMap(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Deleting a category.");
        if (categoryService.existsById(id)) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category newCategory, @PathVariable(name = "id") Long id) {
        if (categoryService.existsById(id)) {
            Category oldCategory = categoryService.retrieveCategory(id);
            if (newCategory.getName() != null)
                oldCategory.setName(newCategory.getName());
            oldCategory.setGameList(null);
            List<Game> gameList = newCategory.getGameList();
            if (gameList != null) {
                oldCategory.getGameList().addAll(gameList);
            }
            categoryService.saveCategory(oldCategory);

            JSONObject json = new JSONObject();
            json.put("id", newCategory.getId());
            json.put("name", newCategory.getName());
            json.put("url", newCategory.getUrl());
            json.put("gamelist", newCategory.getGameList());

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
