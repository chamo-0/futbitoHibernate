package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_match")
    private Long idMatch;

    @Column(name = "division")
    private String division;

    @Column(name = "match_date")
    @Temporal(TemporalType.DATE)
    private Date matchDate;

    @Column(name = "HomeTeam")
    private String homeTeam;

    @Column(name = "AwayTeam")
    private String awayTeam;

    @Column(name = "FTHG")
    private Double fthg;

    @Column(name = "FTAG")
    private Double ftag;

    @Column(name = "FTR")
    private String ftr;

    @Column(name = "season")
    private Integer season;

    // SOLO las columnas que EXISTEN en tu BD:
    @Column(name = "local_goals")
    private Integer localGoals;

    @Column(name = "local_team")
    private String localTeam;

    @Column(name = "visitor_goals")
    private Integer visitorGoals;

    @Column(name = "visitor_team")
    private String visitorTeam;

    // ELIMINAR division_id porque NO EXISTE en tu BD
    // @Column(name = "division_id")
    // private Long divisionId;

    public Match() {}

    // Getters y Setters - SOLO para campos que existen
    public Long getIdMatch() { return idMatch; }
    public void setIdMatch(Long idMatch) { this.idMatch = idMatch; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public Date getMatchDate() { return matchDate; }
    public void setMatchDate(Date matchDate) { this.matchDate = matchDate; }

    public String getHomeTeam() { return homeTeam; }
    public void setHomeTeam(String homeTeam) { this.homeTeam = homeTeam; }

    public String getAwayTeam() { return awayTeam; }
    public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }

    public Double getFthg() { return fthg; }
    public void setFthg(Double fthg) { this.fthg = fthg; }

    public Double getFtag() { return ftag; }
    public void setFtag(Double ftag) { this.ftag = ftag; }

    public String getFtr() { return ftr; }
    public void setFtr(String ftr) { this.ftr = ftr; }

    public Integer getSeason() { return season; }
    public void setSeason(Integer season) { this.season = season; }

    public Integer getLocalGoals() { return localGoals; }
    public void setLocalGoals(Integer localGoals) { this.localGoals = localGoals; }

    public String getLocalTeam() { return localTeam; }
    public void setLocalTeam(String localTeam) { this.localTeam = localTeam; }

    public Integer getVisitorGoals() { return visitorGoals; }
    public void setVisitorGoals(Integer visitorGoals) { this.visitorGoals = visitorGoals; }

    public String getVisitorTeam() { return visitorTeam; }
    public void setVisitorTeam(String visitorTeam) { this.visitorTeam = visitorTeam; }

    // ELIMINAR getter y setter de divisionId
    // public Long getDivisionId() { return divisionId; }
    // public void setDivisionId(Long divisionId) { this.divisionId = divisionId; }

    // Métodos auxiliares
    public String getLocalTeamEffective() {
        if (localTeam != null && !localTeam.isEmpty()) {
            return localTeam;
        }
        return homeTeam;
    }

    public String getVisitorTeamEffective() {
        if (visitorTeam != null && !visitorTeam.isEmpty()) {
            return visitorTeam;
        }
        return awayTeam;
    }

    public Integer getLocalGoalsEffective() {
        if (localGoals != null) {
            return localGoals;
        }
        return fthg != null ? fthg.intValue() : 0;
    }

    public Integer getVisitorGoalsEffective() {
        if (visitorGoals != null) {
            return visitorGoals;
        }
        return ftag != null ? ftag.intValue() : 0;
    }

    public String getWinner() {
        Integer local = getLocalGoalsEffective();
        Integer visitor = getVisitorGoalsEffective();

        if (local > visitor) {
            return getLocalTeamEffective();
        } else if (visitor > local) {
            return getVisitorTeamEffective();
        } else {
            return "Empate";
        }
    }

    @Override
    public String toString() {
        return "Match{id=" + idMatch + ", season=" + season + ", " +
                getLocalTeamEffective() + " " + getLocalGoalsEffective() +
                "-" + getVisitorGoalsEffective() + " " + getVisitorTeamEffective() +
                ", date=" + matchDate + "}";
    }
}