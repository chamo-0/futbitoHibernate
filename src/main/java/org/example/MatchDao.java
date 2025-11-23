package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;

public class MatchDao {

    public List<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Match", Match.class).list();
        } catch (Exception e) {
            System.err.println("Error en findAll: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // CONSULTA 1: Partidos en temporada 2007
    public List<Match> findMatchesInSpain2007() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match m WHERE m.season = 2007";
            return session.createQuery(hql, Match.class).list();
        } catch (Exception e) {
            System.err.println("Error en consulta 2007: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // CONSULTA 2: Máximo de goles del equipo visitante
    public Integer findMaxVisitorGoals() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Probar con visitor_goals primero
            String hql = "SELECT MAX(m.visitorGoals) FROM Match m WHERE m.visitorGoals IS NOT NULL";
            Integer max1 = session.createQuery(hql, Integer.class).uniqueResult();

            // Si no hay datos, probar con FTAG
            if (max1 == null) {
                String hql2 = "SELECT MAX(m.ftag) FROM Match m WHERE m.ftag IS NOT NULL";
                Double max2 = session.createQuery(hql2, Double.class).uniqueResult();
                return max2 != null ? max2.intValue() : 0;
            }

            return max1;
        } catch (Exception e) {
            System.err.println("Error en max goles: " + e.getMessage());
            return 0;
        }
    }

    // CONSULTA 3: Equipos locales con número de apariciones por temporada
    public List<Object[]> findLocalTeamAppearancesBySeason() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT m.localTeam, m.season, COUNT(m) " +
                    "FROM Match m " +
                    "WHERE m.localTeam IS NOT NULL " +
                    "GROUP BY m.localTeam, m.season " +
                    "ORDER BY m.season, COUNT(m) DESC";
            return session.createQuery(hql, Object[].class).list();
        } catch (Exception e) {
            System.err.println("Error en equipos locales: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // CONSULTA 4: Partidos con al menos 5 goles
    public List<Match> findMatchesWithMinGoals() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match m WHERE " +
                    "(m.localGoals >= 5 OR m.visitorGoals >= 5 OR " +
                    "m.fthg >= 5 OR m.ftag >= 5)";
            return session.createQuery(hql, Match.class).list();
        } catch (Exception e) {
            System.err.println("Error en partidos con muchos goles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // CONSULTA 5: Partidos entre Tenerife y Zaragoza
    public List<Match> findMatchesBetweenTenerifeZaragoza() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Match m WHERE " +
                    "((m.localTeam = 'Tenerife' OR m.homeTeam = 'Tenerife') AND " +
                    "(m.visitorTeam = 'Zaragoza' OR m.awayTeam = 'Zaragoza')) OR " +
                    "((m.localTeam = 'Zaragoza' OR m.homeTeam = 'Zaragoza') AND " +
                    "(m.visitorTeam = 'Tenerife' OR m.awayTeam = 'Tenerife')) " +
                    "ORDER BY m.season";
            return session.createQuery(hql, Match.class).list();
        } catch (Exception e) {
            System.err.println("Error en partidos Tenerife-Zaragoza: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}