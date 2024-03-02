package com.misoft.crud.service;

import com.misoft.crud.DTO.EmployeeDTO;
import com.misoft.crud.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    public ResponseEntity<?> getAllEmployees();
    public ResponseEntity<?> getEmployeeById(String id);
    public ResponseEntity<?> createEmployee(EmployeeDTO employeeDTO);
    public ResponseEntity<?> updateEmployee(String id, EmployeeDTO employeeDTO);
    public ResponseEntity<?> deleteEmployee(String id);

}
