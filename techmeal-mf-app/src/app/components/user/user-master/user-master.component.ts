import { RegistrationData } from '../../../common/dto/registrationdata';
import { Role } from '../../../common/dto/role';
import { User } from '../../../common/dto/user';
import { RoleService } from '../../../services/role.service';
import { UserService } from '../../../services/user.service';
import { UserListComponent } from '../user-list/user-list.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-master',
  templateUrl: './user-master.component.html',
  styleUrls: ['./user-master.component.css']
})
export class UserMasterComponent implements OnInit {

  public loading = false;
  public errorResponse: HttpErrorResponse;
  public error: string;
  public success: string = null;
  public user = new User();
  public isEdit = false;
  public roles: Array<Role>;
  public editedUserRole: string;
  public currentRole = new Role();

  @ViewChild(UserListComponent) userList;

  constructor(private userService: UserService, private roleService: RoleService, private router: Router) { }

  ngOnInit() {
     if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['login']);
    }
    this.getRoles();
  }

  onChangeObj(newObj) {
    console.log(JSON.stringify(newObj));
    this.currentRole = newObj;
  }
  createOrEdit() {
    this.loading = true;
    this.error = null;
    this.success = null;
    this.user.roles.pop();
    this.user.roles.push(this.currentRole);
    if (this.isEdit) {
      this.userService.editUser(this.user)
              .subscribe(result => {
                  if (result === true) {
                     this.loading = false;
                     this.success = 'User edited !!!!';
                     this.userList.getUsers();
                   } else {
                     this.errorResponse = this.userService.error;
                     console.log(this.errorResponse);
                     this.error = 'Something went wrong !!!';
                     this.loading = false;
                   }
              });
    } else {
      this.userService.createUser(this.user)
        .subscribe(result => {
            if (result === true) {
               this.loading = false;
               this.success = 'User created !!!!';
               this.userList.getUsers();
             } else {
               this.errorResponse = this.userService.error;
               console.log(this.errorResponse);
               this.error = 'Something went wrong !!!';
               this.loading = false;
             }
        });
    }
  }

  getRoles() {
    this.roleService.getRoles().subscribe((result: Array<Role>) => {
        if (result != null) {
           this.roles = result;
         } else {
           this.roles = null;
         }
      });
  }

  editUser(editedUser: User) {
    this.user = editedUser;
    this.editedUserRole = this.user.roles[0].name;
    this.isEdit = true;
  }
}
