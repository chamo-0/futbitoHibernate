package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Configuración básica sin validación estricta
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Añadir entidades manualmente
            configuration.addAnnotatedClass(Division.class);
            configuration.addAnnotatedClass(Match.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error inicializando Hibernate: " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}