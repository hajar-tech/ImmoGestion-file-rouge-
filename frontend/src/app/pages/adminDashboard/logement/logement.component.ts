import {Component, OnInit} from '@angular/core';
import {LogementService} from '../../../core/logement/logement.service';
import {Logement} from '../../../moduls/Logement';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-logement',
  imports: [
    NgForOf
  ],
  templateUrl: './logement.component.html',
  standalone: true,
  styleUrl: './logement.component.css'
})
export class LogementComponent implements OnInit{
  logements : Logement[] = [];
  constructor(private logementService : LogementService) {
  }

  ngOnInit(): void {
    this.logementService.getAllLogements().subscribe({
      next :(data)=> this.logements=data,
      error : (err) => console.error('Erreur chargement logements',err)
    })















  }


}
