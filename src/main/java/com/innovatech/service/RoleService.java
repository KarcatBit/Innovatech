package com.innovatech.service;

import com.innovatech.model.Role;
import com.innovatech.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Get role by name
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    // Get roles by hourly rate less than
    public List<Role> getRolesByHourlyRateLessThan(Double hourlyRate) {
        return roleRepository.findByHourlyRateLessThan(hourlyRate);
    }

    // Get roles by hourly rate greater than
    public List<Role> getRolesByHourlyRateGreaterThan(Double hourlyRate) {
        return roleRepository.findByHourlyRateGreaterThan(hourlyRate);
    }

    // Create role
    @Transactional
    public Role createRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RuntimeException("Role already exists with name: " + role.getName());
        }
        return roleRepository.save(role);
    }

    // Update role
    @Transactional
    public Role updateRole(Long id, Role updatedRole) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        existing.setName(updatedRole.getName());
        existing.setHourlyRate(updatedRole.getHourlyRate());

        return roleRepository.save(existing);
    }

    // Delete role
    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}