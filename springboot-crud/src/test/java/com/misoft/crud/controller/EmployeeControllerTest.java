package com.misoft.crud.controller;

import com.misoft.crud.DTO.EmployeeDTO;
import com.misoft.crud.model.Employee;
import com.misoft.crud.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;


    @Test
    public void getAllEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("1", "Mr Rakib", "IT", "Dhaka", "123456789", "rakib@gmail.com", new Date(), new Date()));
        mockEmployees.add(new Employee("2", "Shahin Alam", "Software", "Rangpur", "234534523", "shahin@example.com", new Date(), new Date()));
        ResponseEntity<List<Employee>> mockResponseEntity = new ResponseEntity<>(mockEmployees, HttpStatus.OK);

        when(employeeService.getAllEmployees()).thenReturn(mockResponseEntity);

        ResponseEntity<?> responseEntity = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockEmployees, responseEntity.getBody());
    }

    @Test
    public void getEmployeeById() {
        String id = "1";
        EmployeeDTO mockEmployee = new EmployeeDTO(id, "Mr Rakib", "IT", "Dhaka", "123456789", "rakib@gmail.com", new Date(), new Date());
        ResponseEntity<EmployeeDTO> mockResponseEntity = new ResponseEntity<>(mockEmployee, HttpStatus.OK);

        when(employeeService.getEmployeeById(id)).thenReturn(mockResponseEntity);

        ResponseEntity<?> responseEntity = employeeController.getEmployeeById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockEmployee, responseEntity.getBody());
    }

    @Test
    public void createEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1", "Mr Rakib", "IT", "Dhaka", "123456789", "rakib@gmail.com", new Date(), new Date());
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>("Employee saved successfully", HttpStatus.CREATED);

        when(employeeService.createEmployee(employeeDTO)).thenReturn(mockResponseEntity);

        ResponseEntity<?> responseEntity = employeeController.createEmployee(employeeDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Employee saved successfully", responseEntity.getBody());
    }

    @Test
    public void updateEmployee() {
        String id = "1";
        EmployeeDTO employeeDTO = new EmployeeDTO(id, "Mr Rakib", "IT", "Dhaka", "123456789", "rakib@gmail.com", new Date(), new Date());
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);

        when(employeeService.updateEmployee(id, employeeDTO)).thenReturn(mockResponseEntity);

        ResponseEntity<?> responseEntity = employeeController.updateEmployee(id, employeeDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Employee updated successfully", responseEntity.getBody());
    }

    @Test
    public void deleteEmployee() {
        String id = "1";
        ResponseEntity<String> mockResponseEntity = new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);

        when(employeeService.deleteEmployee(id)).thenReturn(mockResponseEntity);

        ResponseEntity<?> responseEntity = employeeController.deleteEmployee(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals("Employee deleted successfully", responseEntity.getBody());
    }

}