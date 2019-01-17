package com.turkcell.playcell.cms.cmsdemo1.service;

import com.turkcell.playcell.cms.cmsdemo1.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> retrieveCategories();

    public void saveCategory(Category category);

    public Category retrieveCategory(Long id);

    public void deleteCategory(Long id);

    public boolean existsById(Long id);
}
