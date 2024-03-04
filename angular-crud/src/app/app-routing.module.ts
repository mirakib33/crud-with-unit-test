import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './components/employee/employee.component';
import { HomeComponent } from './components/home/home.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';

const routes: Routes = [
  {
    path : "",
    component : HomeComponent
  },
  {
    path : "employee",
    component : EmployeeComponent
  },
  {
    path : "employeeList",
    component : EmployeeListComponent
  },
  {
    path : "employee/:id",
    component : EmployeeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
