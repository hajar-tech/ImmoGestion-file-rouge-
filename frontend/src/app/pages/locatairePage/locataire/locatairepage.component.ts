import {Component, OnInit} from '@angular/core';
import {LocataireDetails} from '../../../moduls/LocataireDetails';
import {LocataireService} from '../../../core/Locataire/locataire.service';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {AddTacheComponent} from '../../tachePages/add-tache/add-tache.component';
import {TacheService} from '../../../core/Tache/tache.service';
import {NavebarComponent} from '../../../shared/navebar/navebar.component';

@Component({
  selector: 'app-locataire',
  imports: [
    NgIf,
    AddTacheComponent,
    NgClass,
    NgForOf,
    NavebarComponent
  ],
  templateUrl: './locatairepage.component.html',
  styleUrl: './locatairepage.component.css'
})
export class LocatairepageComponent implements OnInit{

  locataireDetails? : LocataireDetails;
  showAddModal = false;
  incidents: any[] = [];
  userId!: number;

  constructor(private locataireService : LocataireService,
              private tacheService : TacheService)  {
  }


  ngOnInit(): void {

  this.userId = Number(localStorage.getItem('userId'));
    console.log("user id est " + this.userId);
    if (this.userId) {

    this.locataireService.getLocataireDetail(this.userId).subscribe(
      {
        next : (res)=> {this.locataireDetails = res;
          if (res.idLogement){
            localStorage.setItem('idLogement' , res.idLogement.toString());
          }
        },
        error :(err)=>{
          console.log("Erreur lors du chargement du locataire" , err);
        }
      }
    );

    this.loadIncidents();
   }


  }

  loadIncidents(){
    this.tacheService.getIncidentsByLocataire(this.userId).subscribe({
      next : (res)=>{
        this.incidents = res;
      },
      error : (err)=>{
        console.log("Erreur lors du chargement des incidents" , err);
      }
    });
  }


  openModal(){
    this.showAddModal = true;
  }

  closeModal(){
    this.showAddModal = false;
  }


}
