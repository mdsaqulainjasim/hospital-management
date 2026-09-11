package com.hospital.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.RoleDTO;
import com.hospital.management.entity.Role;
import com.hospital.management.exception.RoleNotFoundException;
import com.hospital.management.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {

        Role role = new Role();
        role.setName(roleDTO.getName());
        Role savedRole = roleRepository.save(role);

        return convertToDTO(savedRole);
    }

    @Override
    public RoleDTO getRoleById(Long id) {

        Optional<Role> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            return convertToDTO(optionalRole.get());
        }

        throw new RoleNotFoundException("Role not found with Id : " + id);
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleByName(String name) {

        Role role = roleRepository.findByName(name);

        if (role == null) {
            throw new RoleNotFoundException("Role not found with name : " + name);
        }

        return convertToDTO(role);
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {

        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with Id : " + id));

        existingRole.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(existingRole);

        return convertToDTO(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {

        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with Id : " + id));

        roleRepository.delete(existingRole);
    }

    private RoleDTO convertToDTO(Role role) {

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        return roleDTO;
    }
}