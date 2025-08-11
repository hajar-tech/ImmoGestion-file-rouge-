import { Component } from '@angular/core';
import {NavebarComponent} from '../../../shared/navebar/navebar.component';
import {SearcheBareComponent} from '../searche-bare/searche-bare.component';
import {CardLogementComponent} from '../card-logement/card-logement.component';
import {FooterComponent} from '../../../shared/footer/footer.component';

@Component({
  selector: 'app-client-main',
  imports: [
    NavebarComponent,
    SearcheBareComponent,
    CardLogementComponent,
    FooterComponent
  ],
  templateUrl: './client-main.component.html',
  styleUrl: './client-main.component.css'
})
export class ClientMainComponent {

}
