import { Component } from '@angular/core';
import {OurServiceComponent} from '../our-service/our-service.component';
import {FooterComponent} from '../../../shared/footer/footer.component';
import {HerohomeComponent} from '../herohome/herohome.component';
import {NavebarComponent} from '../../../shared/navebar/navebar.component';

@Component({
  selector: 'app-home',
  imports: [
    OurServiceComponent,
    FooterComponent,
    HerohomeComponent,
    NavebarComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
