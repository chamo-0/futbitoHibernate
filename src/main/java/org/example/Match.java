package org.example;

import javax.persistence.*;

@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @Column(name ="id_match")
    private Integer idMatch;

    @Column(name="match_date")
    private String matchDate;

    @Column(name="HomeTeam")
    private String homeTeam;

    @Column(name="AwayTeam")
    private String awayTeam;

    @Column(name="FTHG")
    private int fthg;

    @Column(name="FTAG")
    private int ftag;

    @Column(name = "FTR")
    private String ftr;

    @Column(name="season")
    private int season;

    @ManyToOne
    @JoinColumn(name="division")
    private Division division;

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public float getFthg() {
        return fthg;
    }

    public void setFthg(int fthg) {
        this.fthg = fthg;
    }

    public void setFtag(int ftag) {
        this.ftag = ftag;
    }

    public float getFtag() {
        return ftag;
    }



    public String getFtr() {
        return ftr;
    }

    public void setFtr(String ftr) {
        this.ftr = ftr;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Match{" +
                "idMatch=" + idMatch +
                ", matchDate=" + matchDate +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", fthg=" + fthg +
                ", ftag=" + ftag +
                ", ftr='" + ftr + '\'' +
                ", season=" + season +
                ", division=" + division +
                '}';
    }
}
