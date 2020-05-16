package com.example.ReportGenerator.web;

import com.example.ReportGenerator.model.Role;
import com.example.ReportGenerator.model.User;
import com.example.ReportGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(User user, Model model, BindingResult result) {
        User userFromDb = userRepo.findByEmail(user.getEmail());
        if (userFromDb != null) {
            result.rejectValue("message", "User exists");
            return "signup";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/signin";
    }
}
