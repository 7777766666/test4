package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(value = "")
    public String printWelcome(ModelMap modelMap, Principal principal){
        User user = userDAO.getFindByEmail(principal.getName());
        modelMap.addAttribute("user", user);
        return "user/user";
    }


//
//        private final UserService userService;
//        private final PasswordEncoder passwordEncoder;
//
//
//        @Autowired
//        public UserController(UserService userService, PasswordEncoder passwordEncoder) {
//            this.userService = userService;
//            this.passwordEncoder = passwordEncoder;
//        }
//
//        @GetMapping("/user")
//        public String getUser(Model model, Authentication authentication) {
//            User user = (User) authentication.getPrincipal();
//            model.addAttribute("user", user);
//            model.addAttribute("roless", user.getRoles());
//            return "user/user";
//        }

    }