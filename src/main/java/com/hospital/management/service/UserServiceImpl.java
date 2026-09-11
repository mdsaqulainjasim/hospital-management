package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.UserDTO;
import com.hospital.management.entity.Role;
import com.hospital.management.entity.User;
import com.hospital.management.exception.UserNotFoundException;
import com.hospital.management.repository.RoleRepository;
import com.hospital.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // DTO → Entity
    private User convertToEntity(UserDTO userDTO) {

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setGender(userDTO.getGender());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setAddress(userDTO.getAddress());
        user.setStatus(userDTO.getStatus());

        if (userDTO.getRoleId() != null) {
            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Role not found with Id : " + userDTO.getRoleId()
                            ));

            user.setRole(role);
        }

        return user;
    }

    // Entity → DTO
    private UserDTO convertToDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());
        userDTO.setGender(user.getGender());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setAddress(user.getAddress());
        userDTO.setStatus(user.getStatus());

        if (user.getRole() != null) {
            userDTO.setRoleId(user.getRole().getId());
        }

        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = convertToEntity(userDTO);

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return convertToDTO(optionalUser.get());
        }

        throw new UserNotFoundException(
                "User not found with Id : " + id
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public UserDTO getUserByEmail(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return convertToDTO(optionalUser.get());
        }

        throw new UserNotFoundException(
                "User not found with Email : " + email
        );
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with Id : " + id
                        ));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setGender(userDTO.getGender());
        existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setStatus(userDTO.getStatus());

        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }

        if (userDTO.getRoleId() != null) {

            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Role not found with Id : " + userDTO.getRoleId()
                            ));

            existingUser.setRole(role);
        }

        User updatedUser = userRepository.save(existingUser);

        return convertToDTO(updatedUser);
    }

    @Override
    public UserDTO deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with Id : " + id
                        ));

        UserDTO userDTO = convertToDTO(existingUser);

        userRepository.delete(existingUser);

        return userDTO;
    }
}