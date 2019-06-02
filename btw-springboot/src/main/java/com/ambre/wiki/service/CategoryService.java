/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.service;

import com.ambre.wiki.entities.Category;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository catRepo; 
    
    public Category newCategory(String category) {
        return catRepo.newCategory(category);
    }
    
    public Category getById(Long id) {
		return catRepo.getById(id);
    }
    
    public Iterable<Category> getAllCategories() {
		return catRepo.getAllCategories();
	}
    
    public Category updateCategory(String id, String category) {
		return catRepo.updateCategory(id, category);
	}
    public Boolean deleteCategory(Long id) {
		return catRepo.deleteCategory(id);
	}
    
    public Boolean addCategoryToWiki(String categoryId, Wiki wiki) {
		return catRepo.addCategoryToWiki(categoryId, wiki);
	}
    
    public Boolean removeCategoryFromWiki(String categoryId, Wiki wiki) {
		return catRepo.removeCategoryFromWiki(categoryId, wiki);
	}
}
