package ru.myshop.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.myshop.marketplace.models.CartImage;
import ru.myshop.marketplace.services.ImageService;
import ru.myshop.marketplace.services.ProductService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
public class ProductImageController {

    @Autowired
    private ImageService imageService;



    @GetMapping("/images/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws  IOException {
        CartImage image = imageService.downloadImage(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(Base64.getDecoder().decode(image.getImage()).length);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(Base64.getDecoder().decode(image.getImage()), headers, HttpStatus.OK);
        return responseEntity;
    }
}
