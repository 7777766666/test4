package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {



    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }
    //private final UserRepository userRepository;

    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

//    @Autowired
//    public UserServiceImpl(UserDAO userDAO,
//                           UserRepository userRepository) {
//        this.userDAO = userDAO;
//
//        this.userRepository = userRepository;
//    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println(user);
        List<String> list = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
        List<Role> roleList = listByRole(list);
        user.setRoles(roleList);
        userDAO.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);

    }
    @Override
    @Transactional
    public List<Role> listByRole(List<String> name) {
        return roleDAO.listByName(name);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        List<String> list = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
        List<Role> roleList = listByRole(list);
        user.setRoles(roleList);
        userDAO.editUser(user);
    }

    @Override
    public User findByEmail(String userName) {
        return userDAO.getFindByEmail(userName);
    }

//    @Override
//    @Transactional
//    public User findUserByUsername(String username) {
//        return userRepository.findUserByUsername(username);
//    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userDAO.getUser(username);
        user.getRoles().size();
        return user;
    }


//    @Override
//    @Transactional
//    public User getUserByUsername(String username) {
//        return null;
//    }

}
