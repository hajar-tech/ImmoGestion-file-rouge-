package com.immoGestion.backend.models;

import com.immoGestion.backend.Enums.SpecialityEmploye;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("EMPLOYE")
public class Employe extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private SpecialityEmploye speciality;

    @OneToMany(mappedBy = "employe")
    private List<Tache> taches;

}
