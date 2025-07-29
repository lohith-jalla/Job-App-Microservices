package com.lohith.SpringsecDemo.controllers;

import com.lohith.SpringsecDemo.models.User;
import com.lohith.SpringsecDemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String jwt=userService.register(user);
        if(jwt!=null){
            return new ResponseEntity<>(jwt, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("user", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        boolean isValid = userService.validateToken(jwt);
        return ResponseEntity.ok(isValid);
    }

}
