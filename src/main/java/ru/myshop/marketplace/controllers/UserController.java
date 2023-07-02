package ru.myshop.marketplace.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.myshop.marketplace.models.User;
import ru.myshop.marketplace.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {

        model.addAttribute("user", new User ());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model){

        if (userService.saveUser(user)){
            return "redirect:/";
        }else{
            model.addAttribute("error", "Email занят");
            return "registration";
        }
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
