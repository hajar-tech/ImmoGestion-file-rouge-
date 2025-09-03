import { Routes } from '@angular/router';
import {HomeComponent} from './pages/homePage/home/home.component';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {AdminComponent} from './pages/adminDashboard/admin/admin.component';
import {ClientMainComponent} from './pages/clientView/client-main/client-main.component';
import {DetailsComponent} from './pages/detailsPage/details/details.component';
import {LocataireComponent} from './pages/adminDashboard/Locatairepages/locataire/locataire.component';
import {LogementComponent} from './pages/adminDashboard/Logementpages/logement/logement.component';
import {
  DetailLocataireComponent
} from './pages/adminDashboard/Locatairepages/detail-locataire/detail-locataire.component';

export const routes: Routes = [
  {path: '' , redirectTo: 'home' , pathMatch: 'full'},
  {path : 'home' , component: HomeComponent},
  {path : 'login' , component: LoginComponent},
  {path : 'register' , component: RegisterComponent},
  {
    path : 'admin' , component: AdminComponent,

    children: [
      { path: '', redirectTo: 'logementAdmin', pathMatch: 'full' },
      {path : 'locataireAdmin' , component : LocataireComponent},
      {path : 'logementAdmin' , component : LogementComponent},
      { path: 'locataireDetails/:id', component: DetailLocataireComponent }
    ]
  },



  {path : 'client_property' , component: ClientMainComponent},
  {path : 'detail_logement' , component : DetailsComponent},


];
