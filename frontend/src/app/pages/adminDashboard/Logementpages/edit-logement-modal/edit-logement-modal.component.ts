import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {Logement} from '../../../../moduls/Logement';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {STATUTS_LOGEMENT, TYPES_LOGEMENT} from '../../../../moduls/enum';

@Component({
  selector: 'app-edit-logement-modal',
  imports: [
    ReactiveFormsModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './edit-logement-modal.component.html',
  styleUrl: './edit-logement-modal.component.css'
})
export class EditLogementModalComponent implements OnChanges{

  @Input() logementToEdit: Logement | null = null;
  @Input() isOpen: boolean = false; // contrôle ouverture modale
  @Output() close = new EventEmitter<void>();
  @Output() save = new EventEmitter<Logement>();

  logementForm!: FormGroup;
  typesLogement = TYPES_LOGEMENT;
  statutsLogement = STATUTS_LOGEMENT;

  constructor(private fb: FormBuilder) {}



  ngOnChanges(changes: SimpleChanges) {
    if (changes['logementToEdit'] && this.logementToEdit) {
      this.logementForm = this.fb.group({
        idLogement: [this.logementToEdit.idLogement],
        numeroAppartement: [this.logementToEdit.numeroAppartement, Validators.required],
        etageNumber: [this.logementToEdit.etageNumber, Validators.required],
        surface: [this.logementToEdit.surface, Validators.required],
        prix: [this.logementToEdit.prix, Validators.required],
        type: [this.logementToEdit.type, Validators.required],
        statut: [this.logementToEdit.statut, Validators.required],
        description: [this.logementToEdit.description],
        imageUrls: [this.logementToEdit.imageUrls]
      });
    }
  }


  onSubmit() {
    if (this.logementForm.valid) {
      this.save.emit(this.logementForm.value);
    }
  }

  onClose() {
    this.close.emit();
  }

}
