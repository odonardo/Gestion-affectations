package com.yourapp.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @Column(name = "codeemp", length = 10)
    private String codeemp;

    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "poste", length = 50)
    private String poste;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Affecter> affectations = new ArrayList<>();

    public Employe() {}

    public Employe(String codeemp, String nom, String prenom, String poste) {
        this.codeemp = codeemp;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    // Getters et Setters
    public String getCodeemp() { return codeemp; }
    public void setCodeemp(String codeemp) { this.codeemp = codeemp; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    public List<Affecter> getAffectations() { return affectations; }
    public void setAffectations(List<Affecter> affectations) { this.affectations = affectations; }
}