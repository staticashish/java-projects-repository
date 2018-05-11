import { RegistrationData } from '../../common/dto/registrationdata';
import { UserService } from '../../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public loading = false;
  public errorResponse: HttpErrorResponse;
  public error: string;
  public registerData = new RegistrationData();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  register() {
    this.loading = true;
    this.error = null;
    this.userService.createUser(this.registerData)
      .subscribe(result => {
        if (result === true) {
           this.router.navigate(['login']);
           this.loading = false;
         } else {
           this.errorResponse = this.userService.error;
           console.log(this.errorResponse);
           this.error = 'Something went wrong !!!';
           this.loading = false;
         }
        });
  }
}
