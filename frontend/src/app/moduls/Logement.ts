
export interface Logement {
  idLogement?: number;
  numeroAppartement: string;
  etageNumber: number;
  surface: number;
  prix: number;
  nombreChambre: number; // Ajouté pour correspondre au backend
  salleBain: number; // Ajouté pour correspondre au backend (notez la casse "salleBain")
  propriete: string; // Ajouté pour correspondre au backend
  aGarage: boolean; // Ajouté pour correspondre au backend
  aTerrasse: boolean; // Ajouté pour correspondre au backend
  aAscenseur: boolean; // Ajouté pour correspondre au backend
  description: string;
  imageUrls: string[];
  type: string;
  statut: string;
}
