package com.misoft.crud.service;

import com.misoft.crud.DTO.EmployeeDTO;
import com.misoft.crud.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    public ResponseEntity<List<Employee>> getAllEmployees();
    public ResponseEntity<EmployeeDTO> getEmployeeById(String id);
    public ResponseEntity<String> createEmployee(EmployeeDTO employeeDTO);
    public ResponseEntity<String> updateEmployee(String id, EmployeeDTO employeeDTO);
    public ResponseEntity<String> deleteEmployee(String id);

}
