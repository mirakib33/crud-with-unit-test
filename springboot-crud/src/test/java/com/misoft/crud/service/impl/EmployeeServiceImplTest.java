package com.misoft.crud.service.impl;

import com.misoft.crud.DTO.EmployeeDTO;
import com.misoft.crud.model.Employee;
import com.misoft.crud.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getAllEmployees() {
        List<Employee> mockEmployees = new ArrayList<>();
        mockEmployees.add(new Employee("1", "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date()));
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        ResponseEntity<?> response = employeeService.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployees, response.getBody());
    }

    @Test
    public void getEmployeeById() {
        String id = "1";
        Employee mockEmployee = new Employee(id, "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());
        EmployeeDTO mockEmployeeDTO = new EmployeeDTO(id, "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());

        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));
        when(employeeService.employeeToDto(mockEmployee)).thenReturn(mockEmployeeDTO);

        ResponseEntity<?> response = employeeService.getEmployeeById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployeeDTO, response.getBody());
    }

    @Test
    public void createEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1", "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());
        Employee employee = new Employee();
        when(modelMapper.map(any(), any())).thenReturn(employee);

        ResponseEntity<?> response = employeeService.createEmployee(employeeDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(employeeRepository).saveAndFlush(employee);
    }

    @Test
    public void updateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("1", "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());
        Employee employee = new Employee("1", "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));

        ResponseEntity<?> response = employeeService.updateEmployee("1", employeeDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeRepository).saveAndFlush(employee);
    }

    @Test
    public void deleteEmployee() {
        Employee employee = new Employee("1", "John Doe", "IT", "New York", "123456789", "john@example.com", new Date(), new Date());
        when(employeeRepository.findById("1")).thenReturn(Optional.of(employee));

        ResponseEntity<?> response = employeeService.deleteEmployee("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeRepository).deleteById("1");
    }

}