package ru.myshop.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myshop.marketplace.models.Category;
import ru.myshop.marketplace.services.CategoryService;
import ru.myshop.marketplace.services.ProductService;

@Controller
public class IndexController {

    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    public IndexController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getProductsIndex());
        return "index";
    }

    @GetMapping("/category/{id}")
    public String category(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("products", categoryService.getCategory(id).getProducts());
        return "category";
    }

    @GetMapping("/category/add")
    public String addCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "add";
    }


    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute("category") Category category) {

        categoryService.saveCategory(category);
        return "redirect:/";
    }

}
