import { RoleService } from '../../../services/role.service';
import { RoleListComponent } from '../role-list/role-list.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-role-master',
  templateUrl: './role-master.component.html',
  styleUrls: ['./role-master.component.css']
})
export class RoleMasterComponent implements OnInit {

  public loading = false;
  public roleData = {name: ''};
  public error: string = null;
  public success: string = null;
  public errorResponse: HttpErrorResponse;
  @ViewChild(RoleListComponent) roleList;

  constructor(private roleService: RoleService, private router: Router) { }

  ngOnInit() {
  }

  create() {
    this.loading = true;
    this.error = null;
    this.success = null;
    this.roleService.createRole(this.roleData)
      .subscribe(result => {
        if (result != null) {
           this.loading = false;
           this.success = 'New Role Created !!!';
           this.roleList.getRoles();
         } else {
           this.errorResponse = this.roleService.error;
           console.log(this.errorResponse);
           if (this.errorResponse.status === 409) {
             this.error = this.errorResponse.error.message;
           } else {
             this.error = 'Something went wrong !!!';
           }
           this.loading = false;
         }
      });
  }
}
