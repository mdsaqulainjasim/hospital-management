package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import com.hospital.management.entity.User;

public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);

    User updateUser(Long id, User user);

    User deleteUser(Long id);

}
