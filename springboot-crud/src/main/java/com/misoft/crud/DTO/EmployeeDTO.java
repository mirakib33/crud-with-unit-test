package com.misoft.crud.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private String id;
    private String name;
    private String department;
    private String city;
    private String phone;
    private String email;
    private Date createdOn;
    private Date updatedOn;

}
