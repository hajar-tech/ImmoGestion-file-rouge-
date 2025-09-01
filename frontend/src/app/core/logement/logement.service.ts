import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Logement} from '../../moduls/Logement';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LogementService {

  constructor(private http : HttpClient) { }

    private  apiUrl = 'http://localhost:8080/api/logements';

  getAllLogements(): Observable<Logement[]>{
    return this.http.get<Logement[]>(`${this.apiUrl}/getAll`);
  }

  creerLogement(logement : Logement): Observable<Logement>{
    return this.http.post<Logement>(`${this.apiUrl}/creerLogement` , logement)
  }

  deleteLogement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }


  updateLogement(id: number, logement: Logement): Observable<Logement> {
    return this.http.put<Logement>(`${this.apiUrl}/${id}`, logement);
  }
}
