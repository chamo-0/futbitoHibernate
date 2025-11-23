package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;

public class DivisionDao {

    public List<Division> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Division", Division.class).list();
        } catch (Exception e) {
            System.err.println("Error obteniendo divisiones: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}