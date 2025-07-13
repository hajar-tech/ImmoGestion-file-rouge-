import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Utilisateur} from '../../moduls/Utilisateur';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient ) { }

     private apiUrl  = "http://localhost:8080/api/auth"

  register(user : Utilisateur):Observable<any>{
    return this.http.post(`${this.apiUrl}/register` , user, {
      responseType: 'text' as 'json' // 👈 FORCEMENT NÉCESSAIRE pour forcer Angular à accepter string
    });

  }


}
