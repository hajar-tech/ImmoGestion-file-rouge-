import {Component, EventEmitter, OnInit, Output, Input} from '@angular/core';

import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LogementService} from '../../../../core/logement/logement.service';
import {Logement} from '../../../../moduls/Logement';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-add-logement',
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './add-logement.component.html',
  styleUrl: './add-logement.component.css'
})
export class AddLogementComponent  implements OnInit{
  // @Input pour contrôler la visibilité de la modale depuis le composant parent
  @Input() showModal: boolean = false;

  // @Output pour notifier le composant parent de fermer la modale
  @Output() closeModal = new EventEmitter<boolean>();

  // @Output pour notifier le composant parent qu'un logement a été ajouté
  @Output() logementAdded = new EventEmitter<void>();

  // FormGroup pour gérer le formulaire d'ajout
  addLogementForm!: FormGroup;

  isSubmitting: boolean = false;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb : FormBuilder,
              private logementService : LogementService) {
  }

  ngOnInit(): void {

    // Initialisation du formulaire avec des valeurs par défaut et des validateurs
    this.addLogementForm = this.fb.group({
      numeroAppartement: ['', Validators.required],
      etageNumber: [null, [Validators.required, Validators.min(0)]],
      surface: [null, [Validators.required, Validators.min(1)]],
      prix: [null, [Validators.required, Validators.min(0)]],
      type: ['', Validators.required],
      statut: ['', Validators.required],
      description: [''],
      // Utilisation d'un champ texte pour les URLs d'images séparées par des virgules pour simplifier
      imageUrls: ['']
    });
  }

  // Méthode pour fermer la modale
  onClose(): void {
    this.closeModal.emit(true);
    this.addLogementForm.reset(); // Réinitialise le formulaire lors de la fermeture
  }

  //Soumission du formulaire
  onSubmit(): void{

    if (this.addLogementForm.valid){
      this.isSubmitting = true;
      this.successMessage = null;
      this.errorMessage = null;

      //creer objet Logement
      const newLogement : Logement = {
        ...this.addLogementForm.value,
        imageUrls : this.parseImageUrls(this.addLogementForm.value.imageUrls)
      };

      this.logementService.creerLogement(newLogement).subscribe({
        next : (response) =>{
          this.isSubmitting = false;
          this.successMessage = 'Logement ajouté avec succès !';
          console.log('Logement crée : ', response);
          this.logementAdded.emit();
          this.addLogementForm.reset();
          setTimeout(()=> this.onClose(), 2000);
        },
        error: (err) =>{
          this.isSubmitting = false;
          this.errorMessage = 'Erreur lors de l\'ajout du logement. Veuillez réessayer.';
          console.error('Erreur lors de la création du logement :', err);

        }
      });

    }else {
      // Marque tous les champs comme "touchés" pour afficher les erreurs de validation
      this.addLogementForm.markAllAsTouched();
    }

  }

  // Helper pour parser les URLs d'images d'une chaîne
  private parseImageUrls(urls: string): string[] {
    if (!urls) return [];
    return urls.split(',').map(url => url.trim()).filter(url => url.length > 0);
  }

}
