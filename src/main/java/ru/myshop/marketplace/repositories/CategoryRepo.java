package ru.myshop.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myshop.marketplace.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
