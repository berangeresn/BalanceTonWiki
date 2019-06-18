/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Category;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.service.CategoryService;
import com.ambre.wiki.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    CategoryService catService;
    @Autowired
    WikiService wikiService;
    
    @PostMapping(path="/v1/admin/new") 
	public @ResponseBody Category newCategory (@RequestParam String category) {
		Category cat = new Category();
		cat.setCategory(category);
		return catService.newCategory(category);
	}
    
    @GetMapping(path="/v1/admin/{id}")
        public @ResponseBody Category getById(@PathVariable("id") Long id) {
            return catService.getById(id);
        }
    
    @GetMapping(path="/v1/contri/all")
	public @ResponseBody Iterable<Category> getAllCategories() {
		return catService.getAllCategories();
        }
    
    @PutMapping(path="/v1/admin/update/{id}")
        public @ResponseBody Category updateCategory(@PathVariable("id") String id, @RequestParam String category) {
            Category cat = new Category();
            cat.setCategory(category);
            return catService.updateCategory(id, category);
    }
    
    @DeleteMapping(path="/v1/admin/delete/{id}")
	public @ResponseBody Boolean deleteCategory(@PathVariable("id") Long id) {
		return catService.deleteCategory(id);
	}
    
    @PutMapping(path="v1/contri/add/{categoryid}/wiki/{wikiid}")
    public @ResponseBody boolean addCategoryToWiki (@PathVariable("categoryid") String CategoryId, @PathVariable("wikiid") Long WikiId){
        Wiki wiki = wikiService.getWikiById(WikiId);
        return catService.addCategoryToWiki(CategoryId, wiki);
    }
    
    @PutMapping(path="v1/admin/remove/{categoryid}/wiki/{wikiid}")
    public @ResponseBody boolean removeCategoryFromWiki (@PathVariable("categoryid") String CategoryId, @PathVariable("wikiid") Long WikiId){
        Wiki wiki = wikiService.getWikiById(WikiId);
        return catService.removeCategoryFromWiki(CategoryId, wiki);
    }
}
