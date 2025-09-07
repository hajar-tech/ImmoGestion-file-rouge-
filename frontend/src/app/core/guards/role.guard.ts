import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const roleGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  //get token from localStotage
  const token = localStorage.getItem('token');
  if (!token){
    router.navigate(['/login']);
    return false;
  }

  try {
    //découper le token pur extrire le role
  //  const payload = JSON.parse(atob(token.split('.')[1]));
    const userRole = localStorage.getItem('role');

    //vérifier l'accès du role
    const allowedRoles = route.data['roles'] as Array<string>;

    // @ts-ignore
    if(allowedRoles && allowedRoles.some(r => r.toUpperCase()=== userRole.toUpperCase())){
      return true;
    }else {
      router.navigate(['/home']);
      return false
    }
  }catch (e){
    router.navigate(['/login']);
    return false;
  }
};
