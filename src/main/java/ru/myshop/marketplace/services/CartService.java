package ru.myshop.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myshop.marketplace.models.Cart;
import ru.myshop.marketplace.models.Product;
import ru.myshop.marketplace.models.User;
import ru.myshop.marketplace.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getByCartUser(User user){
        return cartRepository.findByUser(user);
    }

    public void addCart(Cart cart, Product product, int count){
        if (cart.getQuantities().containsKey(product)){
            count += cart.getQuantities().get(product);
        }
        // add in cart_product
        cart.getProducts().add(product);
        // add in hashMap
        cart.getQuantities().put(product, count);
        cart.setTotal(cart.getTotal() + product.getPrice());
        cartRepository.save(cart);
    }

    public void createCartShop(User user){
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    public void clearCart(Cart cart){
        cart.getQuantities().clear();
        cart.setTotal(Double.valueOf(0));
        cartRepository.save(cart);
    }

}
