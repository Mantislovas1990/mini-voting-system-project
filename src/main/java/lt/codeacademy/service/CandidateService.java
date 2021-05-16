package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Candidate;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public Candidate getWinnerOfElection() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        Candidate candidate = null;
        try {
            Query<Long> query =
                    session.createQuery("SELECT voter.candidate.id " +
                            "FROM Voter voter " +
                            "GROUP BY voter.candidate " +
                            "ORDER BY COUNT(voter.candidate) DESC", Long.class);
            query.setMaxResults(1).uniqueResult();
            candidate = session.get(Candidate.class, query.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return candidate;
    }
}

