import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  //vérifier si un token existe dans le localStorage
  const token = localStorage.getItem('token');

  if(token){
    return true;//accès autorisé
  }else {
    //redirection vers login si pas de token
    router.navigate(['/login']);
    return false;
  }

};
