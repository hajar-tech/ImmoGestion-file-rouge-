export  interface LocataireDetails{

  idLocataire: number;       // id du locataire
  idLogement?: number;
  prenom : string;
   nom : string;
   numeroTelephone : string;
   carteIdentite : string;
   email : string;
   password : string;
   situationFamiliale : string;

  // Logement associé (infos principales)

   numeroAppartement : string;
   etageNumber : number;
   surface : number;
   prix : number;
   type : string;

}
