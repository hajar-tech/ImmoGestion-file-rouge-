import {Component, OnInit} from '@angular/core';
import {LocataireDetails} from '../../../moduls/LocataireDetails';
import {LocataireService} from '../../../core/Locataire/locataire.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-locataire',
  imports: [
    NgIf
  ],
  templateUrl: './locatairepage.component.html',
  styleUrl: './locatairepage.component.css'
})
export class LocatairepageComponent implements OnInit{

  locataireDetails? : LocataireDetails;

  constructor(private locataireService : LocataireService) {
  }


  ngOnInit(): void {

    const userId = localStorage.getItem('userId');
    console.log("user id est " +userId);
    if (userId) {

    this.locataireService.getLocataireDetail(+userId).subscribe(
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
  }
  }

  openIncidentModal() {

  }
}
