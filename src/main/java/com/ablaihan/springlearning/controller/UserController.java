package com.ablaihan.springlearning.controller;

import com.ablaihan.springlearning.domain.Role;
import com.ablaihan.springlearning.domain.User;
import com.ablaihan.springlearning.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userList(@PathVariable User user, Model model) {
        model.addAttribute("users", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public String userSave(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String password, @RequestParam String email) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }
}
