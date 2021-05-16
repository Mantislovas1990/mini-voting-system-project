package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VoterService {

    public Voter saveUpdate(Voter voter) {

        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(voter);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return voter;
    }

    public Voter getVoterById(Long id) {
        HibernateConfig.buildSessionFactory();
        Session session = null;
        Voter voter = null;
        try {
            session = HibernateConfig.openSession();
            voter = session.get(Voter.class, id);
            Hibernate.initialize(voter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(session).close();
        }
        return voter;
    }

    public Long getTotalCount() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<?> query = session.createQuery("SELECT COUNT (*) FROM Voter ");
            transaction.commit();
            return (Long) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Voter> getAllVoters() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Voter> voters = new ArrayList<>();

        try {
            Query<Voter> query = session.createQuery("FROM Voter", Voter.class);
            voters = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return voters;
    }


    public void delete(Long id) {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(getVoterById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public Long getMaleVoteCount() {
        Long result;
        Transaction transaction = null;
        try (Session session = HibernateConfig.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<Voter> root = query.from(Voter.class);
            query.select(criteriaBuilder.count(root.get("gender"))).where(criteriaBuilder.equal(root.get("gender"), Gender.MALE));
            result = session.createQuery(query).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    public Long getFemaleVoteCount() {
        Long result;
        Transaction transaction = null;
        try (Session session = HibernateConfig.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<Voter> root = query.from(Voter.class);
            query.select(criteriaBuilder.count(root.get("gender"))).where(criteriaBuilder.equal(root.get("gender"), Gender.FEMALE));
            result = session.createQuery(query).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    public Long getVilniusVoteCount() {
        Long result;
        Transaction transaction = null;
        try (Session session = HibernateConfig.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<Voter> root = query.from(Voter.class);
            query.select(criteriaBuilder.count(root.get("city"))).where(criteriaBuilder.equal(root.get("city"), City.VILNIUS));
            result = session.createQuery(query).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    public Long getKaunasVoteCount() {
        Long result;
        Transaction transaction = null;
        try (Session session = HibernateConfig.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<Voter> root = query.from(Voter.class);
            query.select(criteriaBuilder.count(root.get("city"))).where(criteriaBuilder.equal(root.get("city"), City.KAUNAS));
            result = session.createQuery(query).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }
}
