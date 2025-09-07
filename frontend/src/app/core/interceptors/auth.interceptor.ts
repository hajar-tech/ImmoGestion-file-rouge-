import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  //Récupération du token
  const token = localStorage.getItem('token');

  // Si un token existe, on clone la requête et on ajoute le header Authorization
  if(token){
    const authReq = req.clone({
      setHeaders: {
        Authorization : `Bearer ${token}`
      }
    });
    return next(authReq);
  }

  // Si pas de token, on envoie la requête telle qu’elle
  return next(req);


};
