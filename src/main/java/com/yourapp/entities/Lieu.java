package com.yourapp.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lieu")
public class Lieu {

    @Id
    @Column(name = "codelieu", length = 10)
    private String codelieu;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "province", length = 50)
    private String province;

    @OneToMany(mappedBy = "lieu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Affecter> affectations = new ArrayList<>();

    public Lieu() {}

    public Lieu(String codelieu, String designation, String province) {
        this.codelieu = codelieu;
        this.designation = designation;
        this.province = province;
    }

    // Getters et Setters
    public String getCodelieu() { return codelieu; }
    public void setCodelieu(String codelieu) { this.codelieu = codelieu; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public List<Affecter> getAffectations() { return affectations; }
    public void setAffectations(List<Affecter> affectations) { this.affectations = affectations; }
}