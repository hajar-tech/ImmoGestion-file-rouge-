import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {TacheService} from '../../../core/Tache/tache.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-add-tache',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './add-tache.component.html',
  styleUrl: './add-tache.component.css'
})
export class AddTacheComponent implements  OnInit{

  incidentForm! : FormGroup;
  role : string = '';
  idLocataire! : number;
  idLogement! : number;

  @Input() showAddModal : boolean = false;
  @Output() close = new EventEmitter<void>();

  constructor(private fb : FormBuilder,
              private tacheService : TacheService ) {
  }

  ngOnInit(): void {
    this.role = localStorage.getItem('role')||'';
    this.idLocataire = Number(localStorage.getItem('userId'));
    console.log("id add tache loc " + this.idLocataire);
    this.idLogement = Number(localStorage.getItem('idLogement'));
    console.log("id add tache log "+ this.idLogement)

    this.incidentForm = this.fb.group({
      categorie : [''],
      description : [''],
      typeTache : [this.role === 'LOCATAIRE' ? 'INCIDENT' : 'TACHE_ADMIN'],
      locataireId : [this.idLocataire],
      logementId : [this.idLogement],
    });

  }

  onSubmitIncident():void {

    if(this.incidentForm.valid){
      const  incidentData = this.incidentForm.value;
      console.log('Données envoyées : ' ,incidentData);
      this.tacheService.addTache(incidentData).subscribe({
        next : (res)=>{
          console.log(' Incident ajouté : ', res);
          this.close.emit();
          alert('Tache ajouter avec succès ');
          this.incidentForm.reset({
            categorie: '',
            description: '',
            typeTache: this.role === 'LOCATAIRE' ? 'INCIDENT' : 'TACHE_ADMIN',
            locataireId: this.idLocataire,
            logementId: this.idLogement
          });

        },
        error:(err)=>{
          console.error('Erreur ajout incident : ', err);
          alert('Erreur lors de l\'ajout du tache');
        }
      });
    }else {
      alert('Remplissez tous les champs obligatoires');
    }
  }


  closeModal(){
    this.close.emit();
  }

}
