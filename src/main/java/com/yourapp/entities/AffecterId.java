package com.yourapp.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class AffecterId implements Serializable {

    @Column(name = "codeemp", length = 10)
    private String codeemp;

    @Column(name = "codelieu", length = 10)
    private String codelieu;

    @Column(name = "date")
    private LocalDate date;

    public AffecterId() {}

    public AffecterId(String codeemp, String codelieu, LocalDate date) {
        this.codeemp = codeemp;
        this.codelieu = codelieu;
        this.date = date;
    }

    // Getters et Setters
    public String getCodeemp() { return codeemp; }
    public void setCodeemp(String codeemp) { this.codeemp = codeemp; }
    public String getCodelieu() { return codelieu; }
    public void setCodelieu(String codelieu) { this.codelieu = codelieu; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AffecterId that = (AffecterId) o;
        return Objects.equals(codeemp, that.codeemp) &&
               Objects.equals(codelieu, that.codelieu) &&
               Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeemp, codelieu, date);
    }
}