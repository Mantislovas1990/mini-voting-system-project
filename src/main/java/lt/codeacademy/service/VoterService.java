package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<Object[]> getVoteCountByGender() {

        try (Session session = HibernateConfig.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Voter> root = criteriaQuery.from(Voter.class);
            criteriaQuery.multiselect(root.get("gender"), criteriaBuilder.count(root.get("gender")));
            criteriaQuery.groupBy(root.get("gender"));
            Query<Object[]> query = session.createQuery(criteriaQuery);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Object[]> getVoteCountByCity() {

        try (Session session = HibernateConfig.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Voter> root = criteriaQuery.from(Voter.class);
            criteriaQuery.multiselect(root.get("city"), criteriaBuilder.count(root.get("city")));
            criteriaQuery.groupBy(root.get("city"));
            Query<Object[]> query = session.createQuery(criteriaQuery);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
