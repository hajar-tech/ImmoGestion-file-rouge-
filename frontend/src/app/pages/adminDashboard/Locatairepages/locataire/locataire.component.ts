import { Component } from '@angular/core';
import {AddLocataireComponent} from '../add-locataire/add-locataire.component';
import {Locataire} from '../../../../moduls/Locataire';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {NgIf} from '@angular/common';
import {TableLocataireComponent} from '../table-locataire/table-locataire.component';

@Component({
  selector: 'app-locataire',
  imports: [
    AddLocataireComponent,
    NgIf,
    TableLocataireComponent
  ],
  templateUrl: './locataire.component.html',
  styleUrl: './locataire.component.css'
})
export class LocataireComponent  {
      locataires : Locataire[] = [];

       showAddModal: boolean = false;

  constructor(private locataireService: LocataireService) {}

  openAddModal() {
    this.showAddModal = true;
  }

  closeAddModal() {
    this.showAddModal = false;
  }

  onLocataireSaved(locataire: Locataire) {
    // ajouter le locataire au tableau
    this.locataires.push(locataire);
    this.closeAddModal();
  }

}
