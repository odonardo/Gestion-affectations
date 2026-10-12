// package com.yourapp.dao;

// import com.yourapp.entities.Employe;
// import com.yourapp.util.HibernateUtil;
// import org.hibernate.Session;
// import org.hibernate.Transaction;
// import org.hibernate.query.Query;
// import java.util.List;
// import java.util.logging.Logger;

// public class EmployeDAO {

//     private static final Logger LOGGER = Logger.getLogger(EmployeDAO.class.getName());

//     public void save(Employe employe) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             session.saveOrUpdate(employe);
//             tx.commit();
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur lors de la sauvegarde de l'employé : " + e.getMessage());
//         }
//     }

//     public Employe findById(String codeemp) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             return session.get(Employe.class, codeemp);
//         }
//     }

//     public List<Employe> findAll() {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             Query<Employe> query = session.createQuery("FROM Employe", Employe.class);
//             return query.list();
//         }
//     }

//     public void delete(String codeemp) {
//         Transaction tx = null;
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             tx = session.beginTransaction();
//             Employe e = session.get(Employe.class, codeemp);
//             if (e != null) session.delete(e);
//             tx.commit();
//         } catch (Exception e) {
//             if (tx != null) tx.rollback();
//             LOGGER.severe("Erreur lors de la suppression de l'employé : " + e.getMessage());
//         }
//     }

//     public List<Employe> searchByCodeOrName(String keyword) {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//             String hql = "FROM Employe WHERE codeemp LIKE :keyword OR nom LIKE :keyword";
//             Query<Employe> query = session.createQuery(hql, Employe.class);
//             query.setParameter("keyword", "%" + keyword + "%");
//             return query.list();
//         }
//     }
// }



package com.yourapp.dao;

import com.yourapp.entities.Employe;
import com.yourapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class EmployeDAO {

    private static final Logger LOGGER = Logger.getLogger(EmployeDAO.class.getName());

    public void save(Employe employe) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(employe);
            tx.commit();
            LOGGER.info("Employé sauvegardé : " + employe.getCodeemp());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur lors de la sauvegarde de l'employé : " + e.getMessage());
        }
    }

    public Employe findById(String codeemp) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employe.class, codeemp);
        }
    }

    public List<Employe> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employe> query = session.createQuery("FROM Employe", Employe.class);
            return query.list();
        }
    }

    public void delete(String codeemp) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Employe e = session.get(Employe.class, codeemp);
            if (e != null) {
                session.delete(e);
                tx.commit();
                LOGGER.info("Employé supprimé : " + codeemp);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.severe("Erreur lors de la suppression de l'employé : " + e.getMessage());
        }
    }

    /**
     * Recherche multi‑critères par mot‑clé.
     * Porte sur : code, nom, prénom et poste.
     */
    public List<Employe> searchByKeyword(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Employe WHERE codeemp LIKE :keyword OR nom LIKE :keyword OR prenom LIKE :keyword OR poste LIKE :keyword";
            Query<Employe> query = session.createQuery(hql, Employe.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.list();
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la recherche : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}