import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-sidebare',
  imports: [
    RouterLink
  ],
  templateUrl: './sidebare.component.html',
  standalone: true,
  styleUrl: './sidebare.component.css'
})
export class SidebareComponent {

  constructor(private router : Router) {
  }

  logOut():void{
    localStorage.clear();
    this.router.navigate(['/home']);

  }

}
