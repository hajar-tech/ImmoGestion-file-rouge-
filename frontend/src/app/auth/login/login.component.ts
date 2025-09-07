import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../core/authService/auth.service';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authServie : AuthService, private  router : Router) {
  }



  onLogin() {

    this.authServie.login(this.email , this.password).subscribe({
      next :(res)=>{
        const role = res.role;
        console.log('Role reçu : ',role);
        if(res.role === 'ADMIN'){
          this.router.navigate(['/admin']);
        }else if (role === 'LOCATAIRE'){
          this.router.navigate(['/client_property']);
        }else{
          this.router.navigate(['/home']);
        }
      },
      error : (err)=>{
        console.error('Erreur de connexion', err);
        alert('Email ou mot de passe incorrect');
      }
    });

  }
}
