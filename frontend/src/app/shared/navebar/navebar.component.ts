import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {AuthService} from '../../core/authService/auth.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-navebar',
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './navebar.component.html',
  styleUrl: './navebar.component.css'
})
export class NavebarComponent implements OnInit{

  isLoggedIn = false;
  role: string | null = null;

  constructor(private authService : AuthService ,
              private router : Router) {

  }


  ngOnInit(): void {
    this.refreshUser();
  }

  refreshUser():void{
    this.isLoggedIn = this.authService.isLoggedIn();
    this.role = this.authService.getRole();
  }

  logout(): void{
    this.authService.logout();
    this.refreshUser();
    this.router.navigate(['/home']);
  }

}
