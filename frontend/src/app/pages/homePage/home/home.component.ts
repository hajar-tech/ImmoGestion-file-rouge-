import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {OurServiceComponent} from '../our-service/our-service.component';
import {DreamHomesectionComponent} from '../dream-homesection/dream-homesection.component';
import {FooterComponent} from '../../../shared/footer/footer.component';
import {HerohomeComponent} from '../herohome/herohome.component';
import {NavebarComponent} from '../../../shared/navebar/navebar.component';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    OurServiceComponent,
    DreamHomesectionComponent,
    FooterComponent,
    HerohomeComponent,
    NavebarComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
