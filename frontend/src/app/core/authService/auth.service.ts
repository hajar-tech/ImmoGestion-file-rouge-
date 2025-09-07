import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Utilisateur} from '../../moduls/Utilisateur';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient) { }

     private apiUrl  = "http://localhost:8080/api/auth"

  register(user : Utilisateur):Observable<any>{
    return this.http.post(`${this.apiUrl}/register` , user, {
      responseType: 'text' as 'json' // 👈 FORCEMENT NÉCESSAIRE pour forcer Angular à accepter string
    });

  }


  login(email : string , password : string): Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/login`,{email , password})
      .pipe(
        tap(res => {
          // Stocker token + rôle + userId
          localStorage.clear();
          localStorage.setItem('token', res.token);
          console.log("Token saved in localeStorage :" , res.token)

          //const payload = JSON.parse(atob(res.token.split('.')[1]));
         // localStorage.setItem('role' , payload.role);
         })
      );
  }

  logout(){
    localStorage.clear();
  }

  isLoggedIn(): boolean{
    return !!localStorage.getItem('token');
  }

  getUserRole(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return (payload.role ?? '').replace('ROLE_', '');
    } catch {
      return null;
    }
  }


}
