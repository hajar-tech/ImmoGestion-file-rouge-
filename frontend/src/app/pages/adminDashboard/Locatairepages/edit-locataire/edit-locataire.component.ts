import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Locataire} from '../../../../moduls/Locataire';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-edit-locataire',
  imports: [
    FormsModule
  ],
  templateUrl: './edit-locataire.component.html',
  styleUrl: './edit-locataire.component.css'
})
export class EditLocataireComponent {

  @Input() locataire!: Locataire;   // Locataire à éditer
  @Output() close = new EventEmitter<void>();
  @Output() updated = new EventEmitter<Locataire>();

  editedLocataire!: Locataire;

  constructor(private locataireService: LocataireService) {}


  ngOnInit() {
    // clone pour ne pas modifier directement l'objet parent
    this.editedLocataire = { ...this.locataire };
  }

  saveChanges() {
    this.locataireService.updateLocataire(this.editedLocataire.id!, this.editedLocataire)
      .subscribe({
        next: (res) => {
          this.updated.emit(res); // renvoyer le locataire modifié
          this.close.emit();
        },
        error: (err) => console.log('Erreur update locataire', err)
      });
  }

  cancel() {
    this.close.emit();
  }

}
