package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @Column(name = "division")
    private String division;

    @Column(name = "name")
    private String name;

    @Column(name = "Country")
    private String Pais;

    @OneToMany(mappedBy = "division")
    private List<Match> match;

    public Division(String division, String name, String Pais) {
        this.division = division;
        this.name = name;
        this.Pais = Pais;
    }

    public Division() {

    }

    // Getters y Setters
    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPais() { return Pais; }
    public void setPais(String pais) { this.Pais = pais; }

    @Override
    public String toString() {
        return "Division{division='" + division + "', name='" + name + "', Pais='" + Pais + "'}";
    }
}