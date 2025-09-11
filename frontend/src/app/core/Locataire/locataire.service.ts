import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Locataire} from '../../moduls/Locataire';
import {Observable} from 'rxjs';
import {LocataireDetails} from '../../moduls/LocataireDetails';
import {LocataireLogementAssociation} from '../../moduls/LocataireLogementAssociation';

@Injectable({
  providedIn: 'root'
})
export class LocataireService {

  private apiUrl = 'http://localhost:8080/admin/locataires'

  constructor(private http : HttpClient) { }

  createLocataire( locataire : Locataire ): Observable<Locataire>{
    return this.http.post<Locataire>(`${this.apiUrl}` , locataire);
  }

  getAllLocataires():Observable<Locataire[]>{
    return  this.http.get<Locataire[]>(`${this.apiUrl}`);
  }


  // Modifier un locataire
  updateLocataire(id: number, dto: Locataire): Observable<Locataire> {
    return this.http.put<Locataire>(`${this.apiUrl}/${id}`, dto);
  }

  // Supprimer un locataire
  deleteLocataire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }


  // Récupérer un locataire par id (DTO simple)
  getLocataireById(id: number): Observable<Locataire> {
    return this.http.get<Locataire>(`${this.apiUrl}/${id}`);
  }

  // Détail complet pour affichage (locataire + logement + paiements + incidents)
  getLocataireDetail(id: number): Observable<LocataireDetails> {
    return this.http.get<LocataireDetails>(`${this.apiUrl}/${id}/details`);
  }

  // Associer un logement à un locataire
  associerLocataire(locataireId: number, logementId: number):Observable<any> {
    const body = { locatairId : locataireId, logementId };
    return this.http.post(`${this.apiUrl}/assign-logement`, body , {responseType: 'text'});
  }

  libererLogement(association: LocataireLogementAssociation) {
    return this.http.post(`${this.apiUrl}/liberer-logement`, association);
  }


  // locataire.service.ts
  dissocierLocataire(idLocataire: number, idLogement: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${idLocataire}/dissocier/${idLogement}`, { responseType: 'text' });
  }



}
