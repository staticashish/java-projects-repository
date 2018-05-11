import { User } from '../../../common/dto/user';
import { UserService } from '../../../services/user.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  public userList: Array<User>;
  public dataLoaded = false;
  @Output() edited = new EventEmitter<User>();

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe((result: Array<User>) => {
        if (result != null) {
           this.userList = result;
           this.dataLoaded = true;
         } else {
           this.userList = null;
         }
      });
  }

  editUser(user) {
    this.edited.emit(user);
  }

  deleteUser(user) {
    this.userService.deleteUser(user)
        .subscribe(result => {
            if (result === true) {
               this.getUsers();
             } else {
             }
        });
  }
}
