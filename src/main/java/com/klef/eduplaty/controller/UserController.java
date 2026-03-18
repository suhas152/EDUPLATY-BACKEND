package com.klef.eduplaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.UserDTO;
import com.klef.eduplaty.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController 
{
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/students")
    public List<UserDTO> getAllStudents()
    {
        return userService.getAllStudents();
    }

    @GetMapping("/teachers")
    public List<UserDTO> getAllTeachers()
    {
        return userService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        return userService.deleteUser(id);
    }
}