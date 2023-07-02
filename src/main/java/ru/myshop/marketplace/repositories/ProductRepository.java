package ru.myshop.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.myshop.marketplace.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Override
    Optional<Product> findById(Integer integer);

    Optional<Product> findProductByName(String name);

    @Query(value = "SELECT p FROM Product p ORDER BY p.oftenBuy desc ")
    List<Product> findFirst5ByOrderByOftenBuyDesc();
}
