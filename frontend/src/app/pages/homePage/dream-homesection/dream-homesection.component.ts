import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dream-homesection',
  imports: [],
  templateUrl: './dream-homesection.component.html',
  styleUrl: './dream-homesection.component.css'
})
export class DreamHomesectionComponent {


  // Had l-variables ghadi y-tbdlou meli l-user y-clicki 3la chi button
  // O ghadi n-passiwhom l-LogementListComponent
 /* selectedProperty: string = '';
  selectedValue: string = '';*/
  constructor(private router: Router) {
  }

  /**
   * Had l-function kat-khdem meli kan-clickiw 3la chi button.
   * Kan-passiw l-property w l-value li bghina n-filteriw bihom.
   * @param property L-khassiya li bghiti t-filteri 3liha.
   * @param value L-9ima dyalha.
   */
 /* filterByProperty(property: string, value: string): void {
    this.selectedProperty = property;
    this.selectedValue = value;
  }*/

  filterByProperty(value: string): void {
    this.router.navigate(['/client'], { queryParams: { property: 'propriete', value } });
  }
}
