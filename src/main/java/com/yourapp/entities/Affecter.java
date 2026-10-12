package com.yourapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "affecter")
public class Affecter {

    @EmbeddedId
    private AffecterId id;

    @ManyToOne
    @MapsId("codeemp")
    @JoinColumn(name = "codeemp", insertable = false, updatable = false)
    private Employe employe;

    @ManyToOne
    @MapsId("codelieu")
    @JoinColumn(name = "codelieu", insertable = false, updatable = false)
    private Lieu lieu;

    public Affecter() {}

    public Affecter(AffecterId id, Employe employe, Lieu lieu) {
        this.id = id;
        this.employe = employe;
        this.lieu = lieu;
    }

    // Getters et Setters
    public AffecterId getId() { return id; }
    public void setId(AffecterId id) { this.id = id; }
    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }
    public Lieu getLieu() { return lieu; }
    public void setLieu(Lieu lieu) { this.lieu = lieu; }
}