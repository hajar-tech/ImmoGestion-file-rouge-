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
    //décoder le token pur extraire le role
   const payload = JSON.parse(atob(token.split('.')[1]));

    console.log('Payload complet:', payload);
    console.log('Role extrait:', payload.role);

    // Récupérer le rôle depuis le token, retirer le préfixe ROLE_ si besoin
    const userRole : string = payload.role;
    console.log("role in roleGuard est :" , userRole);

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
