import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeService } from '../service/employee.service';
import { Router } from '@angular/router';
import { Employee } from '../model/employee.model';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit{

  displayedColumns: string[] = ['serial', 'name', 'department', 'city', 'phone', 'email'];

  employees!: MatTableDataSource<Employee>;
  errorMessage!: string;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.employees.paginator = this.paginator;
  }

  constructor(
    private employeeService: EmployeeService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getAllEmployees();

  }

  getAllEmployees(): void {
    this.employeeService.getAllEmployees().subscribe((response: any) => {
          this.employees = response;
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }


}
