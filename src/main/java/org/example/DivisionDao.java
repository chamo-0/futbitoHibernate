package org.example;

import org.hibernate.Session;

import java.util.List;
import java.util.ArrayList;

public class DivisionDao {

    public List<Division> Encontrartodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Division", Division.class).list();
        } catch (Exception e) {
            System.err.println("Error obteniendo divisiones: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}