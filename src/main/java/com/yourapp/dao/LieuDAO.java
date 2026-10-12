// package com.yourapp.dao;

// import com.yourapp.entities.Lieu;
// import com.yourapp.util.HibernateUtil;
// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;

// import java.util.List;
// import java.util.logging.Logger;

// public class LieuDAO {

//     private static final Logger LOGGER = Logger.getLogger(LieuDAO.class.getName());

//     public void save(Lieu lieu) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.saveOrUpdate(lieu);
//             tx.commit();
//             LOGGER.info("Lieu sauvegardé : " + lieu.getCodelieu());
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur sauvegarde lieu : " + e.getMessage());
//             throw new RuntimeException(e);
//         }
//     }

//     public Lieu findById(String codelieu) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(Lieu.class, codelieu);
//         }
//     }

//     public List<Lieu> findAll() {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Query<Lieu> query = session.createQuery("FROM Lieu", Lieu.class);
//             return query.list();
//         }
//     }

//     public void delete(String codelieu) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             Lieu l = session.get(Lieu.class, codelieu);
//             if (l != null) {
//                 session.delete(l);
//                 tx.commit();
//                 LOGGER.info("Lieu supprimé : " + codelieu);
//             }
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur suppression lieu : " + e.getMessage());
//             throw new RuntimeException(e);
//         }
//     }

//     public List<Lieu> searchByCodeOrDesignation(String keyword) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             String hql = "FROM Lieu WHERE codelieu LIKE :keyword OR designation LIKE :keyword";
//             Query<Lieu> query = session.createQuery(hql, Lieu.class);
//             query.setParameter("keyword", "%" + keyword + "%");
//             return query.list();
//         }
//     }
// }





package com.yourapp.dao;

import com.yourapp.entities.Lieu;
import com.yourapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class LieuDAO {

    private static final Logger LOGGER = Logger.getLogger(LieuDAO.class.getName());

    public void save(Lieu lieu) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(lieu);
            tx.commit();
            LOGGER.info("Lieu sauvegardé : " + lieu.getCodelieu());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur sauvegarde lieu : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Lieu findById(String codelieu) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lieu.class, codelieu);
        }
    }

    public List<Lieu> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Lieu> query = session.createQuery("FROM Lieu", Lieu.class);
            return query.list();
        }
    }

    public void delete(String codelieu) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Lieu l = session.get(Lieu.class, codelieu);
            if (l != null) {
                session.delete(l);
                tx.commit();
                LOGGER.info("Lieu supprimé : " + codelieu);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur suppression lieu : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Recherche multi‑critères par mot‑clé.
     * Porte sur : code, désignation et province.
     */
    public List<Lieu> searchByKeyword(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Lieu WHERE codelieu LIKE :keyword OR designation LIKE :keyword OR province LIKE :keyword";
            Query<Lieu> query = session.createQuery(hql, Lieu.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.list();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la recherche : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}