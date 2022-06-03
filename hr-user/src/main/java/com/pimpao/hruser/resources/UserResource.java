package com.pimpao.hruser.resources;

import com.pimpao.hruser.entities.User;
import com.pimpao.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    @GetMapping(path = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }
}
