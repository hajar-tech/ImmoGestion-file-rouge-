import {Component, OnInit} from '@angular/core';
import {TacheAffichage} from '../../../../moduls/TacheAffichage';
import {TacheService} from '../../../../core/Tache/tache.service';
import {NgClass, NgForOf} from '@angular/common';

@Component({
  selector: 'app-taches-admin',
  imports: [
    NgForOf,
    NgClass
  ],
  templateUrl: './taches-admin.component.html',
  styleUrl: './taches-admin.component.css'
})
export class TachesAdminComponent  implements OnInit{

  incidents : TacheAffichage[] = [];
  incidentNumber : number =0 ;
  constructor(private tacheService : TacheService) {
  }

  ngOnInit(): void {
    this.getAllIncidents();
    this.getIncidentsNumber()
  }

  getAllIncidents():void{
     this.tacheService.getIncidentsAdmin().subscribe({
       next : (res)=>{
         this.incidents = res;
       },
       error :(err)=>{
         console.log("Erreur lors du chargement des incidents! ", err);
       }
     });
  }

  getIncidentsNumber():void{
    this.tacheService.getIncidentsNumber().subscribe({
      next : res=> this.incidentNumber = res || 0,
      error : err => {
        console.log("Erreur lors de calcule!" , err);
        this.incidentNumber = 0;
      }

    });
  }

}
