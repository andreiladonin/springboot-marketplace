package ru.myshop.marketplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.myshop.marketplace.models.Cart;
import ru.myshop.marketplace.models.Product;
import ru.myshop.marketplace.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(User user, Cart cart) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(user.getEmail());

        helper.setSubject("Чек об оплате");

        // default = text/plain
        //helper.setText("Check attachment for image!

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Товары</h1>", true);
        helper.setText("<hr/>", true);

        StringBuilder bodyMsg = new StringBuilder("<h1>Товары</h1><hr/>");

        for(Map.Entry<Product, Integer> set: cart.getQuantities().entrySet()){
             bodyMsg.append("<h3>"+set.getKey().getName()+"</h3>");
             bodyMsg.append("<p>Количество: " + set.getValue()+"</p>");
        }
        bodyMsg.append("<hr/>");
        bodyMsg.append("<h2>Итого: " + cart.getTotal() +"</h2>");
        helper.setText(bodyMsg.toString() , true);

        javaMailSender.send(msg);
    }
}
