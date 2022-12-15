package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;

//import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView admin(Principal user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin");
        List<User> users = userService.listUsers();
        modelAndView.addObject("users", users);
        modelAndView.addObject("admin", userService.findByEmail(user.getName()));
        return modelAndView;
      }

    @PostMapping("/add")
    public String addUser(User user){
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping("/user-update/{id}")
    public String editUser(@PathVariable("id") Integer id, User user) {
        user.setId(id);
        userService.editUser(user);
        return "redirect:/admin";
    }











//    private final RoleService roleService;
//    private final PasswordEncoder passwordEncoder;
  //  private final UserRepository userRepository;


//    @Autowired
//    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder,
//                           UserRepository userRepository) {
//        this.userService = userService;
//        this.roleService = roleService;
//        this.passwordEncoder = passwordEncoder;
//
//        this.userRepository = userRepository;
//    }










//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") Integer id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//    @PostMapping("/save")
//    public String addNewUser(@ModelAttribute("newUser") User user) {
//        userService.add(user);
//        return "redirect/admin";
//    }

//    @GetMapping
//    public String getListUsers(Model model, Principal principal, Authentication authentication) {
//        User admin = (User) authentication.getPrincipal();
//  //      model.addAttribute("authUser", userService.findUserByUsername(principal.getName()));
//        model.addAttribute("userList", userService.getList());
//        model.addAttribute("newUser", new User());
//        model.addAttribute("roles",admin.getRoles());
//        return "admin";
//    }
//
//    @PatchMapping("/{id}")
//    public String userSaveEdit(@ModelAttribute("user") User user) {
//        //user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.editUser(user);
//        return "redirect:/admin";
//    }


//    @PostMapping("/newUserAdmin")
//    public String saveNewUser(
//            @ModelAttribute("newUser") User user
//            ) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.add(user);
//        return "redirect:/admin";
//    }
//
//
//
}