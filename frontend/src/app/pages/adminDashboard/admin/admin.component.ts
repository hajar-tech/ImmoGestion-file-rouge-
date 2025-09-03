import { Component } from '@angular/core';
import {SidebareComponent} from '../sidebare/sidebare.component';
import {LogementComponent} from '../Logementpages/logement/logement.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-admin',
  imports: [SidebareComponent, LogementComponent, RouterOutlet],
  templateUrl: './admin.component.html',
  standalone: true,
  styleUrl: './admin.component.css'
})
export class AdminComponent {

}
