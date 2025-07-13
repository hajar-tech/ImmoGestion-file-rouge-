import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {NgIf} from '@angular/common';
import {AuthService} from '../../core/authService/auth.service';
import {Utilisateur} from '../../moduls/Utilisateur';

@Component({
  selector: 'app-register',
  imports: [

    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm;
  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router , private authService : AuthService) {

    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      numeroTelephone: ['', Validators.required],
      carteIdentite: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', Validators.required],
    });
  }

  submitting = false;
  errorMessage = '';

  onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    this.submitting = true;
    this.errorMessage = '';



    this.authService.register(this.registerForm.getRawValue() as Utilisateur).subscribe({
      next: (message: string) => {
        console.log(message); // "Utilisateur enregistré avec succès."
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.submitting = false;
        this.errorMessage = err.error || 'Erreur lors de l’inscription.';
        console.error(err);
      }
    });
  }



}
