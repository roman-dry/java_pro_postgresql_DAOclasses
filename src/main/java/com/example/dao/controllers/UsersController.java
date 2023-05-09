package com.example.dao.controllers;

import com.example.dao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.dao.models.User;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userservice;

    @Autowired
    public UsersController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping()
    public String readAllUsers(Model model) {
        model.addAttribute("users", userservice.readUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userservice.readUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userservice.createUser(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userservice.readUserById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userservice.updateUserById(user, id);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") int id) {
        userservice.deleteUserById(id);
        return "redirect:/users";
    }
}
