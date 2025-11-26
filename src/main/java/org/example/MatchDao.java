package org.example;

import org.hibernate.Session;

import java.util.List;
import java.util.ArrayList;

public class MatchDao {

    public List<Match> encontrarPartidosEspana2007(Session session) {
            String hql = "FROM Match m WHERE m.division.Pais = :pais AND m.season = :season";
            return session.createQuery(hql, Match.class)
                    .setParameter("pais", "Spain")
                    .setParameter("season", 2007)
                    .list();
    }

    public List<Match> MaxGoles(Session session) {
        String hql = "FROM Match m WHERE m.ftag = (SELECT MAX(m2.ftag) FROM Match m2)";
        return session.createQuery(hql, Match.class).list();
    }

    public List<Object[]> DevolverEquipos(Session session) {
        String hql = "SELECT m.homeTeam, m.season, COUNT(m) " +
                "FROM Match m GROUP BY m.homeTeam, m.season " +
                "ORDER BY m.homeTeam, m.season";

        return session.createQuery(hql).list();
    }
    public List<Match> divisionesConAlMenos5Goles(Session session) {
        String hql = "SELECT DISTINCT m " +
                "FROM Match m JOIN FETCH m.division d " +
                "WHERE m.fthg >= :g OR m.ftag >= :g";
        return session.createQuery(hql, Match.class)
                .setParameter("g", 5)
                .list();
    }
    public List<Match> partidosTenerifeZaragoza(Session session) {
        String hql = "FROM Match m WHERE (m.homeTeam = :team1 AND m.awayTeam = :team2) " +
                     "OR (m.homeTeam = :team2 AND m.awayTeam = :team1)";
        return session.createQuery(hql, Match.class)
                .setParameter("team1", "Tenerife")
                .setParameter("team2", "Zaragoza")
                .list();
    }





}