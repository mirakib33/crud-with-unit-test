import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/model/employee.model';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit{

  employeeForm!: FormGroup;
  employees!: Employee[];
  errorMessage!: string;
  update: boolean = false;
  id: any;

  constructor(
    private employeeService: EmployeeService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
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
    this.id = this.route.snapshot.paramMap.get('id');
    if(this.id !=null) {
      this.getEmployeeById(this.id);
      this.update = true;
    }

  }

  onReset() {
    this.router.navigate(['/employee']);
    this.update = false;
    this.employeeForm = this.fb.group({
      name: [''],
      department: [''],
      city: [''],
      phone: [''],
      email: [''],
    });
  }
 

  getEmployeeById(id: string): void {
    this.employeeService.getEmployeeById(id).subscribe((response: any) => {
      this.employeeForm.patchValue(response);
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
      this.router.navigate(['/employeeList']);
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }

  updateEmployee(): void {
    this.employeeService.updateEmployee(this.id, this.employeeForm.value).subscribe((response: any) => {
          alert(response.message);
          this.router.navigate(['/employeeList']);
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }


}
