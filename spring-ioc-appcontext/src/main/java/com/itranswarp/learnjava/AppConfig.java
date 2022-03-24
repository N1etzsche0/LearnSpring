package com.itranswarp.learnjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.itranswarp.learnjava.service.MailService;
import com.itranswarp.learnjava.service.User;
import com.itranswarp.learnjava.service.UserService;

@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.Login("FxxkJxxe@123.com", "Fxxk");
        System.out.println(user.getUserName());
    }

}