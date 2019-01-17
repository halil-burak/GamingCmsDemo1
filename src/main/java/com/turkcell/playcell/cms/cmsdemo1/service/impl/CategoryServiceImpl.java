package com.turkcell.playcell.cms.cmsdemo1.service.impl;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;
import com.turkcell.playcell.cms.cmsdemo1.repo.CategoryRepository;
import com.turkcell.playcell.cms.cmsdemo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> retrieveCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category retrieveCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
