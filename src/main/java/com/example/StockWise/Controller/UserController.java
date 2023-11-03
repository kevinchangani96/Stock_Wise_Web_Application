package com.example.StockWise.Controller;

import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.Credential;
import com.example.StockWise.Model.dto.ResetDTO;
import com.example.StockWise.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
//@Tag(name = "Greeting", description = "Endpoints for greeting messages")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    private String registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.registerUser(user);
    }

    @PostMapping("/addFund")
    private String addFund(@RequestParam String email,double amount) throws NoSuchAlgorithmException {
        return userService.addFund(email,amount);
    }

    @GetMapping("/login")
    private String loginUser(@RequestBody Credential credential) throws NoSuchAlgorithmException {
        return userService.loginUser(credential);
    }

    @GetMapping("/logout")
    private String signoutUser(@RequestParam String email) throws NoSuchAlgorithmException {
        return userService.signoutUser(email);
    }

    @PostMapping("/resetpass")
    private String resetPassword(@RequestParam String email){
        return userService.resetPassword(email);
    }

    @PostMapping("/verify")
    private String verifyOtp(@RequestBody ResetDTO user) throws NoSuchAlgorithmException {
        return userService.verifyOtp(user);
    }

}