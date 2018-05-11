import { Role } from '../../../common/dto/role';
import { RoleService } from '../../../services/role.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {
  public dataLoaded = false;
  public roles: Array<Role>;
  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles().subscribe((result: Array<Role>) => {
        if (result != null) {
           this.roles = result;
            this.dataLoaded = true;
         } else {
           this.roles = null;
         }
      });
  }
}
