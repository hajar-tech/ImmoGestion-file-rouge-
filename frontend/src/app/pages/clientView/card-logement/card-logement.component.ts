import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Logement} from '../../../moduls/Logement';
import {LogementService} from '../../../core/logement/logement.service';
import {NgForOf, NgIf} from '@angular/common';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-card-logement',
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './card-logement.component.html',
  styleUrl: './card-logement.component.css'
})
export class CardLogementComponent implements  OnChanges{
 @Input() property: string = '';
 @Input() value: string = '';

  logements: Logement[] = [];
  loading: boolean = false;
  error: string = '';

  constructor(private logementService : LogementService,
              private route: ActivatedRoute) {
  }


  /**
   * Cette méthode est appelée chaque fois qu'une variable @Input() change.
   */
 /* ngOnChanges(changes: SimpleChanges): void {
    // Kanchekiw wach l-property o l-value mghiyrin
    if (changes['property'] || changes['value']) {
      // Kan3ayto l-function dyal l-filter ghir ila kanu l-deux 9iyam m3amrin
      if (this.property && this.value) {
        this.getLogements();
      } else {
        // Ila kan shi wahed fihom khawi, kanvideiw l-liste
        this.logements = [];
      }
    }
  }*/


  ngOnChanges(changes: SimpleChanges): void {
    // Kanchekiw wach property/value tbdlou
    if ((changes['property'] || changes['value']) && this.property && this.value) {
      this.getLogements();
    }
  }

  /**
   * Cette fonction récupère les logements filtrés à partir du service.
   */
  getLogements(): void {
    this.loading = true;
    this.error = '';

    this.logementService.getLogementsByProperty(this.property, this.value)
      .subscribe({
        next: (data: Logement[]) => {
          this.logements = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'error lors de filtrage des logements.';
          console.error(err);
          this.loading = false;
        }
      });
  }

}
