import { Routes } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {AdminComponent} from './pages/adminDashboard/admin/admin.component';
import {ClientMainComponent} from './pages/clientView/client-main/client-main.component';

export const routes: Routes = [
  {path: '' , redirectTo: 'home' , pathMatch: 'full'},
  {path : 'home' , component: HomeComponent},
  {path : 'login' , component: LoginComponent},
  {path : 'register' , component: RegisterComponent},
  {path : 'admin' , component: AdminComponent},
  {path : 'client_property' , component: ClientMainComponent}

];
