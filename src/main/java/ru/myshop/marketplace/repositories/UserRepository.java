package ru.myshop.marketplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myshop.marketplace.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
