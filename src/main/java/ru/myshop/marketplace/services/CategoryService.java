package ru.myshop.marketplace.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myshop.marketplace.models.Category;
import ru.myshop.marketplace.repositories.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {


    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryService (CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAll(){
        return categoryRepo.findAll();
    }

    public Category getCategory(int id) {
        return categoryRepo.findById(id).orElseThrow();
    }

    public void saveCategory(Category category){
        categoryRepo.save(category);
    }
}
