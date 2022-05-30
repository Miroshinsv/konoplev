package ru.victorkonoplev.hr.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.victorkonoplev.hr.backend.entity.User;
import ru.victorkonoplev.hr.backend.repository.UserRepository;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/signup")
    ResponseEntity<User> singup(@RequestBody User entity) {
        entity.setRole("USER");
        return ResponseEntity.ok().body(userRepository.save(entity));
    }

    @PostMapping("/singing")
    ResponseEntity<User> singin(@RequestBody String email, @RequestBody String password) {
        return null;
    }
}
