package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Candidate;
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

public class CandidateService {

    public Candidate saveUpdate(Candidate candidate) {

        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(candidate);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return candidate;
    }

    public Candidate getCandidateById(Long id) {
        HibernateConfig.buildSessionFactory();
        Session session = null;
        Candidate candidate = null;
        try {
            session = HibernateConfig.openSession();
            candidate = session.get(Candidate.class, id);
            Hibernate.initialize(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(session).close();
        }
        return candidate;
    }


    public List<Candidate> getAllCandidates() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Candidate> candidates = new ArrayList<>();

        try {
            Query<Candidate> query = session.createQuery("FROM Candidate", Candidate.class);
            candidates = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return candidates;
    }

    public List<Object[]> getWinnerOfElection() {

        try (Session session = HibernateConfig.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Candidate> root = criteriaQuery.from(Candidate.class);
            root.join("voters");
            criteriaQuery.multiselect(root.get("lastName"), root.get("firstName"), criteriaBuilder.count(root.get("lastName"))).toString();
            criteriaQuery.groupBy((root.get("lastName")),root.get("firstName")).toString();
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("lastName")));
            Query<Object[]> query = session.createQuery(criteriaQuery).setMaxResults(1);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

