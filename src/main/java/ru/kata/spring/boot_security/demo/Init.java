package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class Init {


    private UserService userService;
    private RoleService roleService;


    @Autowired
    public Init(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createTable() {
        if (roleService.getList().isEmpty()) {
            Role admin = new Role(1, "ROLE_ADMIN");
            Role user = new Role(2, "ROLE_USER");
            roleService.add(admin);
            roleService.add(user);

            List<Role> setRole1 = new ArrayList<>();
            setRole1.add(admin);
            List<Role> setRole2 = new ArrayList<>();
            setRole2.add(user);
            List<Role> setRole3 = new ArrayList<>();
            setRole3.add(admin);
            setRole3.add(user);

            User adminUser1 = new User("adminUser", "MegaAdminAndUser", 111, "au",  //$2a$12$/V8QItpaDlzt62enZ6jy2ufUPmPfwcmxrBT2M0f903zBDKL3imX6i
                    "$2a$12$RTR7TtmVoD1ZYS/tttyWJ.ShHH6ivsn9OnInWQoZbCmds3eReD/2y", setRole3);

            User user1 = new User("user", "MainUser", 55, "u@u",                   //"user@gmail.com",  $2a$12$QEP7TBCJvY2BIA1ez4uSE.TOfSayI40I8.fv/gRZHczVN2H/uqOMq
                    "u", setRole2);

            User admin1 = new User("admin", "MainAdmin", 99, "a@a",               //"admin@gmail.com",  "$2a$12$cMXTYl25dfJ616oWo5CjhOsZIXYdQSDyIorgNrYN4cCn4PLrVKwjC"
                    "a", setRole1);


            userService.add(admin1);
            userService.add(user1);
            userService.add(adminUser1);

            System.out.println(
                    "login: a      password: admin \n" +
                            "login: u       password: user \n" +
                            "login: adminUser@gmail.com  password: adminUser");
        }
    }
}
