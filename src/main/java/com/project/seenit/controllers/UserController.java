package com.project.seenit.controllers;

import com.project.seenit.models.LoginRequest;
import com.project.seenit.models.Users;
import com.project.seenit.services.AuthenticationResponse;
import com.project.seenit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) throws Exception {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(service.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<Users> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findUserByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<Users> updateUser(@PathVariable String username, @Valid @RequestBody Users user){
        return new ResponseEntity<>(service.updateUser(username, user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<Users>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String username){
        return new ResponseEntity<>(service.deleteByUsername(username), HttpStatus.OK);
    }
}

