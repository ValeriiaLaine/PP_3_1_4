package ru.kata.spring.boot_security.demo.controller;

import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String showAllUsers(@ModelAttribute ("user") User user, Principal principal, Model model) {
        User authenticatedUser = userService.findByUsername(principal.getName());
        model.addAttribute ("authenticatedUser", authenticatedUser);
        model.addAttribute ("roleOfAuthenticatedUser", authenticatedUser.getRoles());
        model.addAttribute("users", userService.findAll());
        model.addAttribute( "AllRoles", roleService.findAll());
        return "admin_panel";
    }

    @GetMapping("/user-profile/{id}")
    public String findUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("AllRoles", user.getRoles());
        return "user-page";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("AllRoles", roleService.findAll());
        return "redirect:/admin";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User updateUser, @PathVariable("id") Long id) {
        userService.updateUser(updateUser); //Находим по id того юзера, которого надо изменить
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String form_for_create_user(Model model, User user) {
        model.addAttribute("user", new User());
        List<Role> roles = (List<Role>) roleService.findAll();
        model.addAttribute("AllRoles", roles);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("user") @Valid User user ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}