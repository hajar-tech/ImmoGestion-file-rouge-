import {Component, EventEmitter, OnInit, Output, Input} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LogementService} from '../../../../core/logement/logement.service';
import {Logement} from '../../../../moduls/Logement';
import {NgIf, NgClass} from '@angular/common';

@Component({
  selector: 'app-add-logement',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,

  ],
  templateUrl: './add-logement.component.html',
  styleUrls: ['./add-logement.component.css']
})
export class AddLogementComponent  implements OnInit{
  @Input() showModal: boolean = false;
  @Output() closeModal = new EventEmitter<boolean>();
  @Output() logementAdded = new EventEmitter<void>();

  addLogementForm!: FormGroup;

  isSubmitting: boolean = false;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb : FormBuilder,
              private logementService : LogementService) {
  }

  ngOnInit(): void {
    this.addLogementForm = this.fb.group({
      numeroAppartement: ['', Validators.required],
      etageNumber: [null, [Validators.required, Validators.min(0)]],
      surface: [null, [Validators.required, Validators.min(1)]],
      prix: [null, [Validators.required, Validators.min(0)]],
      propriete: ['', Validators.required],
      type: ['', Validators.required],
      statut: ['', Validators.required],
      nombreChambre: [null, [Validators.required, Validators.min(0)]],
      salleDeBain: [null, [Validators.required, Validators.min(0)]],
      aGarage: [false],
      aTerrasse: [false],
      aAscenseur: [false],
      description: [''],
      imageUrls: ['']
    });
  }

  onClose(): void {
    this.closeModal.emit(true);
    this.addLogementForm.reset();
    this.successMessage = null;
    this.errorMessage = null;
  }

  onSubmit(): void{
    if (this.addLogementForm.valid){
      this.isSubmitting = true;
      this.successMessage = null;
      this.errorMessage = null;

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
    } else {
      this.addLogementForm.markAllAsTouched();
    }
  }

  private parseImageUrls(urls: string): string[] {
    if (!urls) return [];
    return urls.split(',').map(url => url.trim()).filter(url => url.length > 0);
  }
}
