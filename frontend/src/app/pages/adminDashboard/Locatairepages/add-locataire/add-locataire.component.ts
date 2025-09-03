import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Locataire} from '../../../../moduls/Locataire';
import {LocataireService} from '../../../../core/Locataire/locataire.service';

@Component({
  selector: 'app-add-locataire',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-locataire.component.html',
  styleUrl: './add-locataire.component.css'
})
export class AddLocataireComponent {

  @Output() close = new EventEmitter<void>();
  @Output() saved = new EventEmitter<Locataire>();
  locataireForm: FormGroup;

  constructor(private fb: FormBuilder, private locataireService: LocataireService) {
    this.locataireForm = this.fb.group({
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      carteIdentite: ['', Validators.required],
      numeroTelephone: ['', Validators.required],
      password: ['', Validators.required],
      situationFamiliale: ['']
    });
  }

  onSubmit(){
    if (this.locataireForm.invalid) return;

    const locataire : Locataire = this.locataireForm.value;
    this.locataireService.createLocataire(locataire).subscribe({
      next: (res) => this.saved.emit(res),
      error: (err)=> console.error(err)
    });

  }


  onClose(){
    this.close.emit();
  }



}
