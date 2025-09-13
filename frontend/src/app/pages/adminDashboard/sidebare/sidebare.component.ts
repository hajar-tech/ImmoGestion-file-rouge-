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

  isOpen = false;

  constructor(private router : Router) {
  }


  toggleSidebar(){
    this.isOpen = !this.isOpen;
  }

  closeSidebarOnMobile(){
    if(window.innerWidth < 768){
      this.isOpen = false;
    }
  }

  logOut():void{
    localStorage.clear();
    this.router.navigate(['/home']);

  }

}
