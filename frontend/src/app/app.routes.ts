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
import {authGuard} from './core/guards/auth.guard';
import {roleGuard} from './core/guards/role.guard';
import {OurServiceComponent} from './pages/homePage/our-service/our-service.component';
import {LocatairepageComponent} from './pages/locatairePage/locataire/locatairepage.component';

export const routes: Routes = [
  {path: '' , redirectTo: 'home' , pathMatch: 'full'},
  {path : 'home' , component: HomeComponent},
  {path : 'login' , component: LoginComponent},
  {path : 'register' , component: RegisterComponent},
  {path : 'service' , component: OurServiceComponent},
  {
    path : 'admin' ,
    component: AdminComponent,
    canActivate : [authGuard, roleGuard], //si non authentifier bloqué l'accès
    data:{roles: ['ADMIN']},//vérifier les roles
      children: [
         {path: '', redirectTo: 'logementAdmin', pathMatch: 'full' },
         {path : 'locataireAdmin' , component : LocataireComponent , canActivate : [authGuard, roleGuard], //si non authentifier bloqué l'accès
           data:{roles: ['ADMIN']},},
         {path : 'logementAdmin' , component : LogementComponent , canActivate : [authGuard, roleGuard], //si non authentifier bloqué l'accès
           data:{roles: ['ADMIN']},},
         {path: 'locataireDetails/:id', component: DetailLocataireComponent , canActivate : [authGuard, roleGuard], //si non authentifier bloqué l'accès
           data:{roles: ['ADMIN']}, }
      ]
  },



  {
    path : 'client_property' ,
    component: LocatairepageComponent,
    canActivate: [authGuard, roleGuard],
    data: {roles: ['LOCATAIRE']}
  },
  {
    path : 'detail_logement' ,
    component : DetailsComponent
  },


];
