package com.clean.code.springboot.web.rest;

import com.clean.code.springboot.domain.User;
import com.clean.code.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if(!isPasswordValid(user.getHashedPassword())){
            return new ResponseEntity("Password is not complicated", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User register successfully");
    }

    public static boolean isPasswordValid(String password) {
        // Minimum 8 characters
        if (password.length() < 8) {
            return false;
        }

        // At least one digit
        if (!Pattern.compile(".*\\d.*").matcher(password).matches()) {
            return false;
        }

        // At least one lowercase letter
        if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
            return false;
        }

        // At least one uppercase letter
        if (!Pattern.compile(".*[A-Z].*").matcher(password).matches()) {
            return false;
        }

        // At least one special character
        if (!Pattern.compile(".*[!@#$%^&*()-+_].*").matcher(password).matches()) {
            return false;
        }

        return true;
    }
}
