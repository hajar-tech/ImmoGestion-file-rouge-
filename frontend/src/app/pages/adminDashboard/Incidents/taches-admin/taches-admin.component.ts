import {Component, OnInit} from '@angular/core';
import {TacheAffichage} from '../../../../moduls/TacheAffichage';
import {TacheService} from '../../../../core/Tache/tache.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-taches-admin',
  imports: [
    NgForOf
  ],
  templateUrl: './taches-admin.component.html',
  styleUrl: './taches-admin.component.css'
})
export class TachesAdminComponent  implements OnInit{

  incidents : TacheAffichage[] = [];

  constructor(private tacheService : TacheService) {
  }

  ngOnInit(): void {
    this.getAllIncidents();
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

}
