import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Locataire} from '../../../../moduls/Locataire';
import {Logement} from '../../../../moduls/Logement';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {LogementService} from '../../../../core/logement/logement.service';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-associer-locataire',
  imports: [
    FormsModule,
    NgForOf
  ],
  templateUrl: './associer-locataire.component.html',
  styleUrl: './associer-locataire.component.css'
})
export class AssocierLocataireComponent implements OnInit{
  @Input() locataire!: Locataire;
  @Output() close = new EventEmitter<void>();
  @Output() updated = new EventEmitter<Locataire>(); // ⚡ on ne renvoie plus le locataire, on notifie juste pour refresh

  logements: Logement[] = [];
  selectedLogementId!: number;

  constructor(
    private locataireService: LocataireService,
    private logementService: LogementService
  ) {}

  ngOnInit(): void {
    this.logementService.getAllLogements().subscribe({
      next: (res) => this.logements = res,
      error: (err) => console.log('Erreur fetch logements', err)
    });
  }

  // ⚡ Fonction associer modifiée
  associer() {
    if (!this.selectedLogementId) {
      alert('Veuillez sélectionner un logement');
      return;
    }

    this.locataireService.associerLocataire(this.locataire.id!, this.selectedLogementId)
      .subscribe({
        next: () => {
          alert('Logement assigné avec succès !');
          this.updated.emit(this.locataire); // notifier parent pour refresh
          this.close.emit();   // fermer modal
        },
        error: (err) => {
          alert(err.error);
          console.log('Erreur associer locataire', err)
        }
      });
  }


  cancel() {
    this.close.emit();
  }
}
