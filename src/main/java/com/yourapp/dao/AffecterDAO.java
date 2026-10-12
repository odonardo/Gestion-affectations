// package com.yourapp.dao;

// import com.yourapp.entities.Affecter;
// import com.yourapp.entities.AffecterId;
// import com.yourapp.util.HibernateUtil;
// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;

// import java.util.List;
// import java.util.logging.Logger;

// public class AffecterDAO {

//     private static final Logger LOGGER = Logger.getLogger(AffecterDAO.class.getName());

//     public void save(Affecter affecter) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.saveOrUpdate(affecter);
//             tx.commit();
//             LOGGER.info("Affectation enregistrée");
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
//             throw new RuntimeException(e);
//         }
//     }

//     public Affecter findById(AffecterId id) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(Affecter.class, id);
//         }
//     }

//     public List<Affecter> findAll() {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Query<Affecter> query = session.createQuery("FROM Affecter", Affecter.class);
//             return query.list();
//         }
//     }

//     public void delete(AffecterId id) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             Affecter a = session.get(Affecter.class, id);
//             if (a != null) {
//                 session.delete(a);
//                 tx.commit();
//                 LOGGER.info("Affectation supprimée");
//             }
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
//             throw new RuntimeException(e);
//         }
//     }

//     // Recherche des affectations par code employé ou code lieu
//     public List<Affecter> searchByEmployeOrLieu(String keyword) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             String hql = "FROM Affecter a WHERE a.id.codeemp LIKE :keyword OR a.id.codelieu LIKE :keyword";
//             Query<Affecter> query = session.createQuery(hql, Affecter.class);
//             query.setParameter("keyword", "%" + keyword + "%");
//             return query.list();
//         }
//     }
// }





package com.yourapp.dao;

import com.yourapp.entities.Affecter;
import com.yourapp.entities.AffecterId;
import com.yourapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

public class AffecterDAO {

    private static final Logger LOGGER = Logger.getLogger(AffecterDAO.class.getName());

    // ------------------------ CRUD de base ------------------------

    public void save(Affecter affecter) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(affecter);
            tx.commit();
            LOGGER.info("Affectation enregistrée");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur sauvegarde affectation : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Affecter findById(AffecterId id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Affecter.class, id);
        }
    }

    public List<Affecter> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Affecter> query = session.createQuery("FROM Affecter", Affecter.class);
            return query.list();
        }
    }

    public void delete(AffecterId id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Affecter a = session.get(Affecter.class, id);
            if (a != null) {
                session.delete(a);
                tx.commit();
                LOGGER.info("Affectation supprimée");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur suppression affectation : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ------------------------ RECHERCHE MULTI‑CRITÈRES ------------------------

    /**
     * Recherche des affectations par mot‑clé dans :
     * - le code, le nom ou le prénom de l'employé
     * - le code, la désignation ou la province du lieu
     * - la date (si le mot‑clé est au format yyyy-MM-dd)
     */
    public List<Affecter> searchByKeyword(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Requête HQL avec LIKE sur plusieurs champs
            StringBuilder hql = new StringBuilder(
                "FROM Affecter a WHERE " +
                "a.id.codeemp LIKE :keyword OR " +
                "a.id.codelieu LIKE :keyword OR " +
                "a.employe.nom LIKE :keyword OR " +
                "a.employe.prenom LIKE :keyword OR " +
                "a.lieu.designation LIKE :keyword OR " +
                "a.lieu.province LIKE :keyword"
            );

            // Tester si le mot‑clé est une date valide
            boolean isDate = false;
            LocalDate date = null;
            try {
                date = LocalDate.parse(keyword);
                isDate = true;
            } catch (DateTimeParseException e) {
                // Ce n'est pas une date, on ignore
            }
            if (isDate) {
                hql.append(" OR a.id.date = :date");
            }

            Query<Affecter> query = session.createQuery(hql.toString(), Affecter.class);
            query.setParameter("keyword", "%" + keyword + "%");
            if (isDate) {
                query.setParameter("date", date);
            }
            return query.list();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la recherche : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}