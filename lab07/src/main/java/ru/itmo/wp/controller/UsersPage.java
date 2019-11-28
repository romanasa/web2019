package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String usersPost(Model model, HttpSession httpSession, HttpServletRequest request) {
        try {
            User user = userService.findById(Long.parseLong(request.getParameter("id")));
            String status = request.getParameter("status");
            if (user != null) {
                if (user.isDisabled() && status.equals("enable") || !user.isDisabled() && status.equals("disable")) {
                    userService.updateDisabled(user.getId(), !user.isDisabled());
                }
            } else {
                putMessage(httpSession, "No such user");
            }
        } catch (Exception ignored) {
            putMessage(httpSession, "Something's gone wrong");
        }
        return "redirect:/users/all";
    }
}
