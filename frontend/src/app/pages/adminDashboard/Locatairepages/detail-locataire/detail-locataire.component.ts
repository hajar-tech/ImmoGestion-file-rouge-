import {Component, Input, OnInit} from '@angular/core';
import {LocataireDetails} from '../../../../moduls/LocataireDetails';
import {LocataireService} from '../../../../core/Locataire/locataire.service';
import {NgIf} from '@angular/common';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Locataire} from '../../../../moduls/Locataire';
import {AssocierLocataireComponent} from '../associer-locataire/associer-locataire.component';

@Component({
  selector: 'app-detail-locataire',
  imports: [
    NgIf,
    AssocierLocataireComponent
  ],
  templateUrl: './detail-locataire.component.html',
  styleUrl: './detail-locataire.component.css'
})
export class DetailLocataireComponent implements OnInit{


  locataireId!: number;
  locataireDetails!: LocataireDetails;

  showAssocierModal: boolean = false;

  locataireToAssocier!: Locataire;

  constructor(
    private route: ActivatedRoute,
    private router : Router,
    private locataireService: LocataireService
  ) {}

  ngOnInit(): void {
    this.locataireId = Number(this.route.snapshot.paramMap.get('id'));
    this.fetchLocataireDetails();
  }

  fetchLocataireDetails() {
    this.locataireService.getLocataireDetail(this.locataireId).subscribe({
      next: (res) => this.locataireDetails = res,
      error: (err) => console.error(err)
    });
  }


  goBack() {
    this.router.navigate(['/admin/locataireAdmin']);
  }

  openAssocierModal() {
    if (!this.locataireDetails) return;

    // Créer objet Locataire minimal
    this.locataireToAssocier = {
      id: this.locataireDetails.idLocataire,  // clé primaire
      nom: this.locataireDetails.nom,
      prenom: this.locataireDetails.prenom,
      email: this.locataireDetails.email,
      numeroTelephone: this.locataireDetails.numeroTelephone,
      carteIdentite: this.locataireDetails.carteIdentite,
      situationFamiliale: this.locataireDetails.situationFamiliale,
      password: '' // si nécessaire
    };

    console.log('LocataireDetails =>', this.locataireDetails);

    this.showAssocierModal = true;
  }

  closeAssocierModal() {
    this.showAssocierModal = false;
    this.fetchLocataireDetails(); // Rafraîchir les infos après association
  }

  /*onLocataireAssocie(updatedLocataire: Locataire) {
    const index = this.locataires.findIndex(l => l.idLocataire === updatedLocataire.id);
    if (index !== -1) this.locataires[index] = updatedLocataire;
  }*/

  onDissocierLogement(idLogement?: number) {
    if (!idLogement) {
      alert('Aucun logement associé à ce locataire');
      return;
    }

    if (confirm('Voulez-vous vraiment dissocier ce logement ?')) {
      this.locataireService.dissocierLocataire(this.locataireDetails.idLocataire, idLogement)
        .subscribe({
          next: () => {
            alert('Logement dissocié avec succès');
            this.fetchLocataireDetails();
          },
          error: (err) => {
            console.error('Erreur dissociation', err);
            alert('Erreur lors de la dissociation');
          }
        });
    }
  }




}
