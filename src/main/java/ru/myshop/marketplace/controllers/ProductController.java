package ru.myshop.marketplace.controllers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.myshop.marketplace.exception.ProductNotFoundException;
import ru.myshop.marketplace.models.CartImage;
import ru.myshop.marketplace.models.Category;
import ru.myshop.marketplace.models.Product;
import ru.myshop.marketplace.repositories.CategoryRepo;
import ru.myshop.marketplace.services.CategoryService;
import ru.myshop.marketplace.services.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ProductService productService;
    @GetMapping("/add")
    public String addProductPage (Model model){

        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct (Model model, @ModelAttribute("product") Product product,
                              @RequestParam("file1") MultipartFile file1) throws IOException {


        if (file1.getSize() == 0) {
            model.addAttribute("error", "Загрузите обложку товара");
            model.addAttribute("categories", categoryService.getAll());
            return "add_product";
        }

        Category category = categoryService.getCategory(product.getCategory().getId());
        product.setCategory(category);
        category.getProducts().add(product);
        productService.save(product, file1);

        System.out.println(product.getCategory());

        return "redirect:/";
    }

    @GetMapping("/{name}")
    public String getProduct( Model model, @PathVariable String name) throws ChangeSetPersister.NotFoundException {


        Product product = productService.getProduct(name);
        if (product == null) {
            throw new ProductNotFoundException(name + "not found");
        }

        model.addAttribute("product", product);
        return "product";


    }

}
