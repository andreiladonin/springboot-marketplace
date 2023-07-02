package ru.myshop.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myshop.marketplace.models.CartImage;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<CartImage, Integer> {
    Optional<CartImage> findByName(String fileName);
}
