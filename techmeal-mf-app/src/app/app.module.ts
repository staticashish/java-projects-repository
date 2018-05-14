import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth.service';
import { UserService } from './services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { AuthInterceptorService } from './interceptors/auth-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { RoleListComponent } from './components/role/role-list/role-list.component';
import { RoleMasterComponent } from './components/role/role-master/role-master.component';
import { RoleService } from './services/role.service';
import { UserMasterComponent } from './components/user/user-master/user-master.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { LoadingService } from './services/loading.service';

const appRoutes: Routes = [
{ path: '', pathMatch: 'full', redirectTo: 'home'},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'role', component: RoleMasterComponent},
  { path: 'user', component: UserMasterComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    RoleListComponent,
    RoleMasterComponent,
    UserMasterComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthService,
    UserService,
    RoleService,
    LoadingService,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptorService,
        multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
