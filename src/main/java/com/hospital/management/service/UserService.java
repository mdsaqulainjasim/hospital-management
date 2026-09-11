package com.hospital.management.service;

import java.util.List;

import com.hospital.management.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);

    UserDTO updateUser(Long id, UserDTO userDTO);

    UserDTO deleteUser(Long id);
}