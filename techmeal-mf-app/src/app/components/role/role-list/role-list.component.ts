import { RoleService } from '../../../services/role.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {

  public roles: Array<{id: number, name: string}>;
  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles().subscribe((result: Array<{id: number, name: string}>) => {
        if (result != null) {
           this.roles = result;
         } else {
           this.roles = null;
         }
      });
  }
}
