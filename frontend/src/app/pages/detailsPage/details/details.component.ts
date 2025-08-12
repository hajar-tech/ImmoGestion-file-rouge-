import { Component } from '@angular/core';
import {NavebarComponent} from '../../../shared/navebar/navebar.component';
import {FooterComponent} from '../../../shared/footer/footer.component';

@Component({
  selector: 'app-details',
  imports: [
    NavebarComponent,
    FooterComponent
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {

}
