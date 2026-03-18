package com.klef.eduplaty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    public Optional<User> findByEmail(String email);
    public List<User> findByRole(User.Role role);
}