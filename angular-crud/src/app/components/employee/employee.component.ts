import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee.model';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit{

  employeeForm!: FormGroup;
  employees!: any[];
  errorMessage!: string;

  constructor(
    private employeeService: EmployeeService,
    private fb: FormBuilder,
    private router: Router
    ) { 
      this.employeeForm = this.fb.group({
        name: ['', [Validators.required]],
        department: ['', [Validators.required]],
        city: ['', [Validators.required]],
        phone: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
      });
    }

  ngOnInit(): void {
    // this.getAllEmployees();

  }

 

  getEmployeeById(id: string): void {
    this.employeeService.getEmployeeById(id).subscribe((response: any) => {
          // Handle the response
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }

  createEmployee(): void {
    if (this.employeeForm.invalid) {
      alert("Please input empty field");
      return;
    }
    this.employeeService.createEmployee(this.employeeForm.value).subscribe((response: any) => {
      alert(response.message);
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }

  updateEmployee(id: string, employee: Employee): void {
    this.employeeService.updateEmployee(id, employee).subscribe((response: any) => {
          // Handle the response
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }

  deleteEmployee(id: string): void {
    this.employeeService.deleteEmployee(id).subscribe((response: any) => {
          // Handle the response
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }


}
