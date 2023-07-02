package ru.myshop.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myshop.marketplace.Utils.ImageUtil;
import ru.myshop.marketplace.models.CartImage;
import ru.myshop.marketplace.repositories.ImageRepository;

import java.nio.file.FileSystemNotFoundException;
import java.util.Optional;
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public CartImage downloadImage(String fileName){
        return imageRepository.findByName(fileName)
                .orElseThrow(() -> new FileSystemNotFoundException("File not found with"));
    }
}
