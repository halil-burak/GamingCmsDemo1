package com.turkcell.playcell.cms.cmsdemo1.controller;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.entity.Game;
import com.turkcell.playcell.cms.cmsdemo1.entity.PlatformGameCategory;
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
    public ResponseEntity<String> getCategories() {
        logger.info("Retrieving categories");
        if (!categoryService.retrieveCategories().isEmpty()) {
            List<Category> categoryList = categoryService.retrieveCategories();
            List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
            for (Category category : categoryList) {
                JSONObject json = new JSONObject();
                json.put("id", category.getId());
                json.put("name", category.getName());
                json.put("gamelist", category.getPgcList());
            }
            return new ResponseEntity<>(jsonObjects.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        logger.info("Adding a category");
        List<Category> categoryList = categoryService.retrieveCategories();
        for (Category ctg : categoryList) {
            if (ctg.getName().equals(category.getName())) {
                return new ResponseEntity<String>("There is already one category with this name.", HttpStatus.BAD_REQUEST);
            }
        }
        categoryService.saveCategory(category);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<String> getCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Retrieving a category.");
        if (categoryService.existsById(id)) {
            Category category = categoryService.retrieveCategory(id);
            JSONObject json = new JSONObject();
            json.put("id", category.getId());
            json.put("name", category.getName());
            json.put("gamelist", category.getPgcList());

            return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable(name = "categoryId")Long id) {
        logger.info("Deleting a category.");
        if (categoryService.existsById(id)) {
            categoryService.deleteCategory(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category newCategory, @PathVariable(name = "id") Long id) {
        if (categoryService.existsById(id)) {
            Category oldCategory = categoryService.retrieveCategory(id);
            if (newCategory.getName() != null)
                oldCategory.setName(newCategory.getName());
            oldCategory.setPgcList(null);
            List<PlatformGameCategory> gameList = newCategory.getPgcList();
            oldCategory.getPgcList().addAll(gameList);

            categoryService.saveCategory(oldCategory);

            JSONObject json = new JSONObject();
            json.put("id", newCategory.getId());
            json.put("name", newCategory.getName());
            json.put("gamelist", newCategory.getPgcList());

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
