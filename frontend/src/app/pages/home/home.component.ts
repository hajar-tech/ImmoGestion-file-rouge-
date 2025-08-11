import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {OurServiceComponent} from '../our-service/our-service.component';
import {DreamHomesectionComponent} from '../dream-homesection/dream-homesection.component';
import {FooterComponent} from '../../shared/footer/footer.component';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    OurServiceComponent,
    DreamHomesectionComponent,
    FooterComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
