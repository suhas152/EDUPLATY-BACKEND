package com.klef.eduplaty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.AuthDTO;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController 
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody AuthDTO dto)
    {
        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public User login(@RequestBody AuthDTO dto)
    {
        return userService.loginUser(dto.getEmail(), dto.getPassword());
    }
}