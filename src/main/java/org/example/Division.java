package org.example;

import javax.persistence.*;

@Entity
@Table(name = "divisions") // Cambiar a "divisions" (con s)
public class Division {

    @Id
    @Column(name = "division") // Esta es la columna ID según tu estructura
    private String division;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    public Division() {}

    public Division(String division, String name, String country) {
        this.division = division;
        this.name = name;
        this.country = country;
    }

    // Getters y Setters
    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return "Division{division='" + division + "', name='" + name + "', country='" + country + "'}";
    }
}