package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.hospital.management.exception.RoleNotFoundException;

import com.hospital.management.entity.Role;
import com.hospital.management.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {

        Optional<Role> optionalRole = roleRepository.findById(id);

        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        throw new RoleNotFoundException("Role not found with Id : " + id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role updateRole(Long id, Role role) {

        Role existingRole = getRoleById(id);
        existingRole.setName(role.getName());
        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        Role existingRole = getRoleById(id);
        roleRepository.delete(existingRole);
    }

}