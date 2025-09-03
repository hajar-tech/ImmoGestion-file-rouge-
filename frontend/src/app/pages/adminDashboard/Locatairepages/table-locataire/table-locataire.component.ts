import {Component, OnInit} from '@angular/core';
import {Locataire} from '../../../../moduls/Locataire';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {NgForOf, NgIf} from '@angular/common';
import {EditLocataireComponent} from '../edit-locataire/edit-locataire.component';
import {AssocierLocataireComponent} from '../associer-locataire/associer-locataire.component';
import {LocataireDetails} from '../../../../moduls/LocataireDetails';
import {LocataireLogementAssociation} from '../../../../moduls/LocataireLogementAssociation';
import {DetailLocataireComponent} from '../detail-locataire/detail-locataire.component';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-table-locataire',
  imports: [
    NgForOf,
    EditLocataireComponent,
    NgIf,
    AssocierLocataireComponent,
    DetailLocataireComponent,
    RouterLink
  ],
  templateUrl: './table-locataire.component.html',
  styleUrl: './table-locataire.component.css'
})
export class TableLocataireComponent implements  OnInit{

  locataires : Locataire[] = [];
  showEditModal: boolean = false;
  locataireToEdit!: Locataire;

  showDetailModal: boolean = false;
  locataireToShowId!: number;


  constructor( private locataireService : LocataireService) {
  }


  fetchLocataires(): void{

    this.locataireService.getAllLocataires().subscribe({
      next : (res)=> this.locataires = res,
      error : (err) => console.log("error lors du chargement des locataires !" , err)
    });

  }

  ngOnInit(): void {
    this.fetchLocataires();
  }

  deleteLocataire(id: number) {

    if (confirm("Voulez-vous vraiment supprimer ce locataire ?")) {
      this.locataireService.deleteLocataire(id).subscribe({
        next: () => {
          // Mise à jour de la liste localement (sans refetch complet)
          this.locataires = this.locataires.filter(l => l.id !== id);
          console.log("Locataire supprimé avec succès !");
        },
        error: (err) => {
          console.error("Erreur lors de la suppression du locataire :", err);
        }
      });
    }

  }

  editLocataire(locataire: Locataire) {
   this.locataireToEdit = locataire;
   this.showEditModal = true;
  }

  onEditClose() {
    this.showEditModal = false;
  }

  onLocataireUpdated(updatedLocataire: Locataire) {
    // Mettre à jour la liste locale
    const index = this.locataires.findIndex(l => l.id === updatedLocataire.id);
    if (index !== -1) this.locataires[index] = updatedLocataire;
  }

  libererLocataire(locataire: LocataireLogementAssociation) {
    if (!locataire.locatairId) {
      alert("Ce locataire n'a pas de logement à libérer !");
      return;
    }

    if (confirm("Voulez-vous vraiment libérer le logement de ce locataire ?")) {
      const body: LocataireLogementAssociation = {
        locatairId: locataire.locatairId!,      // id du locataire
        logementId: locataire.logementId // id du logement associé
      };

      this.locataireService.libererLogement(body).subscribe({
        next: () => {
          alert("Logement libéré avec succès !");
          this.fetchLocataires(); // recharge la liste
        },
        error: (err) => {
          alert("Erreur : " + err.error);
        }
      });
    }
  }


  voirDetails(id: number) {
    this.locataireToShowId = id;
    this.showDetailModal = true;
  }

  onDetailClose() {
    this.showDetailModal = false;
  }


}
