package com.klef.eduplaty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.eduplaty.dto.AuthDTO;
import com.klef.eduplaty.dto.UserDTO;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.repository.TeamMemberRepository;
import com.klef.eduplaty.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Override
    public String registerUser(AuthDTO authDTO)
    {
        Optional<User> optionalUser = userRepository.findByEmail(authDTO.getEmail());

        if(optionalUser.isPresent())
        {
            return "Email already exists";
        }

        User user = new User();
        user.setName(authDTO.getName());
        user.setEmail(authDTO.getEmail());
        user.setPassword(authDTO.getPassword());
        user.setRole(User.Role.valueOf(authDTO.getRole().toUpperCase()));

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public User loginUser(String email, String password)
    {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if(user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        List<UserDTO> list = new ArrayList<>();

        for(User user : users)
        {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().name());
            list.add(dto);
        }

        return list;
    }

    @Override
    public List<UserDTO> getAllStudents()
    {
        List<User> users = userRepository.findByRole(User.Role.STUDENT);
        List<UserDTO> list = new ArrayList<>();

        for(User user : users)
        {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().name());
            list.add(dto);
        }

        return list;
    }

    @Override
    public List<UserDTO> getAllTeachers()
    {
        List<User> users = userRepository.findByRole(User.Role.TEACHER);
        List<UserDTO> list = new ArrayList<>();

        for(User user : users)
        {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().name());
            list.add(dto);
        }

        return list;
    }

    @Override
    public UserDTO getUserById(Long id)
    {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().name());
            return dto;
        }

        return null;
    }

    @Override
    public String deleteUser(Long userId)
    {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty())
        {
            return "User not found";
        }

        User user = optionalUser.get();

        if(user.getRole() == User.Role.STUDENT)
        {
            teamMemberRepository.deleteAll(teamMemberRepository.findByStudent(user));
        }

        userRepository.deleteById(userId);
        return "User deleted successfully";
    }
}