import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/model/employee.model';
import { EmployeeService } from 'src/app/service/employee.service';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.scss']
})
export class ViewEmployeeComponent implements OnInit{
  id: any;
  employee!: Employee;
  errorMessage: any;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getEmployeeById(this.id);
  }

  onEdit(id:string) {
    this.router.navigate(['/employee/' + id]);
  }

  getEmployeeById(id: string): void {
    this.employeeService.getEmployeeById(id).subscribe((response: any) => {
      this.employee = response;
        },
        (error: any) => {
          this.errorMessage = error;
        }
      );
  }

}
