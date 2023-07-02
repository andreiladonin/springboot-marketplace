package ru.myshop.marketplace.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.myshop.marketplace.Utils.ImageUtil;
import ru.myshop.marketplace.models.CartImage;
import ru.myshop.marketplace.models.Product;
import ru.myshop.marketplace.repositories.ImageRepository;
import ru.myshop.marketplace.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product, MultipartFile file1 ) throws IOException {
            CartImage image1 = convertFileToEntity(file1);
            image1.setPreviewImage(true);
            product.addImage(image1);
            product.setPreviewNameImage(image1.getName());
            productRepository.save(product);
    }
    public void save(Product product){
        productRepository.save(product);
    }

    private CartImage convertFileToEntity(MultipartFile file) throws IOException {
        CartImage image = new CartImage();
        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return image;
    }

    public List<Product> getProductsIndex(){
        return productRepository.findFirst5ByOrderByOftenBuyDesc();
    }

    public Product getProduct(String name){
        return  productRepository.findProductByName(name).orElse(null);
    }
}
