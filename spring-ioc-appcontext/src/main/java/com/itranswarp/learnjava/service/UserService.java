package com.itranswarp.learnjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UserService {

    @Autowired
    MailService mailService;

    private List<User> users = new ArrayList<User>(List.of(new User(1, "Elliot", "55566", "123@163.com"), new User(2, "Joker", "123456", "888@173.com"), new User(3, "Jack", "Fxxk", "FxxkJxxe@123.com")));

    public UserService(@Autowired MailService mailService) {
        this.mailService = mailService;
    }

    public User Login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("Login Failed.");
    }

    public User getUser(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public User register(String username, String password, String email) {
        users.forEach(user -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("Emali Exist.");
            }
        });
        User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong() + 1, email, password, username);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
