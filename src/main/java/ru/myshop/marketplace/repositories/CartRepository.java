package ru.myshop.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myshop.marketplace.models.Cart;
import ru.myshop.marketplace.models.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser(User user);
}
