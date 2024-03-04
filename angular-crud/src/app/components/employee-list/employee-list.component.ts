import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeService } from '../../service/employee.service';
import { Router } from '@angular/router';
import { Employee } from '../../model/employee.model';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit{

  displayedColumns: string[] = ['serial', 'name', 'department', 'city', 'phone', 'email', 'action'];

  employees!: MatTableDataSource<Employee>;
  errorMessage!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private employeeService: EmployeeService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  onEdit(id:string) {
    this.router.navigate(['/employee/' + id]);
  }

  onView(id:string) {
    this.router.navigate(['/viewEmployee/' + id]);
  }

  onDelete(id:string) {
    this.employeeService.deleteEmployee(id).subscribe((response: any) => {
      alert("Employee Deleted Successfully");
      this.ngOnInit();
    },
    (error: any) => {
      this.errorMessage = error;
    }
  );
  }

  getAllEmployees(): void {
    this.employeeService.getAllEmployees().subscribe((response: any) => {
      this.employees = new MatTableDataSource(response);
      this.employees.paginator = this.paginator;
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }


}
