import {Component, Input, OnInit} from '@angular/core';
import {Locataire} from '../../../../moduls/Locataire';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {NgForOf, NgIf} from '@angular/common';
import {EditLocataireComponent} from '../edit-locataire/edit-locataire.component';
import {AssocierLocataireComponent} from '../associer-locataire/associer-locataire.component';
import {LocataireDetails} from '../../../../moduls/LocataireDetails';
import {LocataireLogementAssociation} from '../../../../moduls/LocataireLogementAssociation';
import {DetailLocataireComponent} from '../detail-locataire/detail-locataire.component';
import {RouterLink} from '@angular/router';
import {AddLocataireComponent} from '../add-locataire/add-locataire.component';

@Component({
  selector: 'app-table-locataire',
  imports: [
    NgForOf,
    EditLocataireComponent,
    NgIf,
    RouterLink,
    AddLocataireComponent,
  ],
  templateUrl: './table-locataire.component.html',
  styleUrl: './table-locataire.component.css'
})
export class TableLocataireComponent implements  OnInit{


  showAddModal: boolean = false;

  locataires : Locataire[] = [];
  showEditModal: boolean = false;
  locataireToEdit!: Locataire;



  constructor( private locataireService : LocataireService) {
  }

  ngOnInit(): void {
    this.fetchLocataires();
  }

  fetchLocataires(): void{

    this.locataireService.getAllLocataires().subscribe({
      next : (res)=> {
        this.locataires = res;
        },
      error : (err) => console.log("error lors du chargement des locataires !" , err)
    });

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

  openAddModal() {
    this.showAddModal = true;
  }

  closeAddModal() {
    this.showAddModal = false;
  }

  onLocataireAdded(locataire: Locataire) {
    // ajouter le locataire au tableau
    this.locataires.push(locataire);
    this.closeAddModal();
  }




}
