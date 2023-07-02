package ru.myshop.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.myshop.marketplace.models.Cart;
import ru.myshop.marketplace.models.Product;
import ru.myshop.marketplace.models.User;
import ru.myshop.marketplace.services.CartService;
import ru.myshop.marketplace.services.EmailService;
import ru.myshop.marketplace.services.ProductService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cart_shop")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private EmailService emailService;


    @GetMapping("")
    public String showCartPage(Model model, Authentication authentication){

        User user = (User)authentication.getPrincipal();
        Cart cartUser = cartService.getByCartUser(user);



        model.addAttribute("cart", cartUser);
        return "cart";
    }

    @PostMapping("/add_cart")
    public String addInCart(@RequestParam(name = "title") String title, Authentication authentication){
        Product product = productService.getProduct(title);

        User user = (User)authentication.getPrincipal();
        Cart cartUser = cartService.getByCartUser(user);

        if (cartUser == null){
            cartService.createCartShop(user);
            cartUser = cartService.getByCartUser(user);
            cartService.addCart(cartUser, product, 1);
        } else
        {
            cartService.addCart(cartUser, product, 1);
        }
        product.setOftenBuy(product.getOftenBuy() + 1);
        productService.save(product);
        return "redirect:/cart_shop";
    }

    @PostMapping("/buy")
    public String buyPage(Authentication authentication) throws MessagingException, IOException {


        User user = (User)authentication.getPrincipal();
        Cart cartUser = cartService.getByCartUser(user);

        emailService.sendEmailWithAttachment(user, cartUser);
        cartService.clearCart(cartUser);


        return "buy";
    }
}
