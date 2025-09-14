import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TacheAffichage} from '../../moduls/TacheAffichage';

@Injectable({
  providedIn: 'root'
})
export class TacheService {

  private apiUrl = 'http://localhost:8080/api/taches';

  constructor(private  http : HttpClient) { }

  addTache(incidentData : any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, incidentData);
  }
  getIncidentsByLocataire(locataireId: number): Observable<any[]>{
       return this.http.get<any[]>(`${this.apiUrl}/locataire/${locataireId}/incidents` )
  }

  getIncidentsAdmin():Observable<TacheAffichage[]>{
    return this.http.get<TacheAffichage[]>(`${this.apiUrl}/getAll`);
  }

  getIncidentsNumber():Observable<number>{
    return this.http.get<number>(`${this.apiUrl}/total`)
  }

}
