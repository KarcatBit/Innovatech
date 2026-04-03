package com.innovatech.service;

import com.innovatech.model.Employee;
import com.innovatech.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by RUT
    public Optional<Employee> getEmployeeByRut(String rut) {
        return employeeRepository.findById(rut);
    }

    // Get employee by email
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Get employees by rol
    public List<Employee> getEmployeesByRole(Long roleId) {
        return employeeRepository.findByRoleId(roleId);
    }

    // Create employee
    @Transactional
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already registered: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    // Update employee
    @Transactional
    public Employee updateEmployee(String rut, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(rut)
                .orElseThrow(() -> new RuntimeException("Employee not found with RUT: " + rut));

        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setTotalHours(updatedEmployee.getTotalHours());
        existing.setDv(updatedEmployee.getDv());
        existing.setRole(updatedEmployee.getRole());

        return employeeRepository.save(existing);
    }

    // Delete employee
    @Transactional
    public void deleteEmployee(String rut) {
        if (!employeeRepository.existsById(rut)) {
            throw new RuntimeException("Employee not found with RUT: " + rut);
        }
        employeeRepository.deleteById(rut);
    }
}
