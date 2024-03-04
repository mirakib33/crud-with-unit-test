package com.misoft.crud.service.impl;

import com.misoft.crud.DTO.EmployeeDTO;
import com.misoft.crud.constants.EmployeeConstants;
import com.misoft.crud.model.Employee;
import com.misoft.crud.repository.EmployeeRepository;
import com.misoft.crud.service.EmployeeService;
import com.misoft.crud.utills.EmployeeUtils;
import com.misoft.crud.utills.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try{
            List<Employee> employees = employeeRepository.findAll();
            log.info("Getting employee list");

//            The commented code can be implemented if we convert to dto
//            List<EmployeeDTO> employeeDTOs = employees.stream()
//                    .map(employee -> employeeToDto(employee))
//                    .collect(Collectors.toList());

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting employee list", e);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeById(String id) {
        try{
            log.info("Getting employee by employee id");
            Optional<Employee> employee = employeeRepository.findById(id);
            EmployeeDTO employeeDTO = employeeToDto(employee.get());
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting employee by id", e);
        }
        return new ResponseEntity<>(new EmployeeDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDTO employeeDTO) {
        try{
            Employee employee = dtoToEmployee(employeeDTO);
            String id = UUID.randomUUID().toString();
            employee.setId(id);
            employee.setCreatedOn(new Date());
            employeeRepository.saveAndFlush(employee);
            log.info("Employee created successfully");
            return EmployeeUtils.getResponseEntity("Employee saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating employee", e);
        }
        return EmployeeUtils.getResponseEntity(EmployeeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateEmployee(String id, EmployeeDTO employeeDTO) {
        try{
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

            employee.setId(id);
            employee.setCity(employeeDTO.getCity());
            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());
            employee.setDepartment(employeeDTO.getDepartment());
            employee.setUpdatedOn(new Date());

            employeeRepository.saveAndFlush(employee);
            log.info("Employee data updated successfully");
            return EmployeeUtils.getResponseEntity("Employee updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while updating employee", e);
        }
        return EmployeeUtils.getResponseEntity(EmployeeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(String id) {
        try{
            employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            employeeRepository.deleteById(id);
            log.info("Employee deleted successfully with id" + id);
            return EmployeeUtils.getResponseEntity("Employee deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred while deleting employee", e);
        }
        return EmployeeUtils.getResponseEntity(EmployeeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        return this.modelMapper.map(employeeDTO, Employee.class);
    }

    public EmployeeDTO employeeToDto(Employee employee) {
        return this.modelMapper.map(employee, EmployeeDTO.class);
    }


}
