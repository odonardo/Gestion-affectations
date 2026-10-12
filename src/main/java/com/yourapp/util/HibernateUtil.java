package com.yourapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class HibernateUtil {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Constructeur privé pour empêcher l'instanciation
    private HibernateUtil() {
        // Rien à initialiser
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(); // charge hibernate.cfg.xml depuis classpath
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            // Affichage FORCÉ dans la console Tomcat (même si le logger échoue)
            System.err.println("================ ERREUR HIBERNATE ================");
            System.err.println("Message : " + ex.getMessage());
            System.err.println("Cause : " + ex.getCause());
            System.err.println("StackTrace complet :");
            ex.printStackTrace();
            // Log via logger également
            LOGGER.severe("Échec de l'initialisation de SessionFactory : " + ex.getMessage());
            // Re-lancer pour que l'application plante proprement
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            LOGGER.info("SessionFactory fermée.");
        }
    }
}