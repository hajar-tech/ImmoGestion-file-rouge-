import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LogementService} from '../../../../core/logement/logement.service';
import {Logement} from '../../../../moduls/Logement';
import {CommonModule, NgForOf, NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {AddLogementComponent} from '../add-logement/add-logement.component';
import {EditLogementModalComponent} from '../edit-logement-modal/edit-logement-modal.component';

@Component({
  selector: 'app-logement',
  imports: [
    NgForOf,
    AddLogementComponent,
   CommonModule,
    EditLogementModalComponent
  ],
  templateUrl: './logement.component.html',
  standalone: true,
  styleUrl: './logement.component.css'
})
export class LogementComponent implements OnInit{
  logements : Logement[] = [];
  totalLogements: number = 0;
  @Output() toggleSidebar = new EventEmitter<void>();

  // NOUVEAU: État pour contrôler l'affichage de la modale
  showAddLogementModal: boolean = false;

  // Modale de modification
  isEditModalOpen: boolean = false;
  selectedLogement: Logement | null = null;


  constructor(private logementService : LogementService,
              private router : Router) {
  }



  ngOnInit(): void {
    this.loadLogements();
  }

  loadLogements(): void {
    this.logementService.getAllLogements().subscribe({
      next: (data) => {
        this.logements = data;
        this.totalLogements = data.length;
        console.log('Logements chargés avec succès :', this.logements);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des logements :', err);
      }
    });
  }

  onViewDetails(logement: Logement): void {
    if (logement.idLogement) {
      this.router.navigate(['/logements', logement.idLogement]);
    }
  }

  //  Méthode pour ouvrir la modale
  onAddLogement(): void {
    this.showAddLogementModal = true;
  }

  // Méthode pour fermer la modale et recharger les données
  onCloseAddLogementModal(): void {
    this.showAddLogementModal = false;
    this.loadLogements(); // Recharge les logements après la fermeture (pour afficher le nouveau logement)
  }

//---------- Suppression ----------
  onDeleteLogement(idLogement: number) {
    if (confirm('Voulez-vous vraiment supprimer ce logement ?')) {
      this.logementService.deleteLogement(idLogement).subscribe(() => {
        // Supprimer de la liste côté frontend
        this.logements = this.logements.filter(l => l.idLogement !== idLogement);
      });
    }
  }


  //------ Modification --------
  openEditModal(logement: Logement): void {
    this.selectedLogement = logement;
    this.isEditModalOpen = true;
  }

  closeEditModal(): void {
    this.isEditModalOpen = false;
    this.selectedLogement = null;
  }



  saveEditedLogement(updatedLogement: Logement): void {
    this.logementService.updateLogement(updatedLogement.idLogement!, updatedLogement)
      .subscribe(() => {
        const index = this.logements.findIndex(l => l.idLogement === updatedLogement.idLogement);
        if (index !== -1) {
          this.logements[index] = updatedLogement;
        }
        this.closeEditModal();
      });
  }




 // onToggleSidebar(): void {
 //   this.toggleSidebar.emit();
 // }

 // onSearch(event: Event): void {
 //   const searchTerm = (event.target as HTMLInputElement).value;
 //   console.log('Terme de recherche :', searchTerm);
 // }

  //trackByLogementId(index: number, logement: Logement): number | undefined {
  //  return logement.idLogement;
  //}


}
