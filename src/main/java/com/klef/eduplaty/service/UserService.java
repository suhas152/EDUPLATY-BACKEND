package com.klef.eduplaty.service;

import java.util.List;

import com.klef.eduplaty.dto.AuthDTO;
import com.klef.eduplaty.dto.UserDTO;
import com.klef.eduplaty.entity.User;

public interface UserService 
{
    public String registerUser(AuthDTO authDTO);
    public User loginUser(String email, String password);
    public List<UserDTO> getAllUsers();
    public List<UserDTO> getAllStudents();
    public UserDTO getUserById(Long id);
    public String deleteUser(Long userId);
    public List<UserDTO> getAllTeachers();
}