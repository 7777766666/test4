package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;


//public interface UserRepository extends JpaRepository<User, Integer> {
//  //  @EntityGraph(attributePaths = {"roles"})
// //  User findUserByUsername(String username);
//}