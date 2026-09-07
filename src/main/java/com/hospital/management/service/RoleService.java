package com.hospital.management.service;

import java.util.List;

import com.hospital.management.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role getRoleByName(String name);

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);

}