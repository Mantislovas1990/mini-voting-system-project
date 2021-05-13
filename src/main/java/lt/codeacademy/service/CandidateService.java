package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Candidate;
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
            Query<Candidate> query = session.createQuery("FROM Candidate ", Candidate.class);
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

//    public List<Object[]> getWinnerOfElection() {
//        Session session = HibernateConfig.openSession();
//        Transaction transaction = session.beginTransaction();
//        List<Object[]> candidates = new ArrayList<>();
//
//        try {
//            Query<Candidate> query = session.createQuery
//                    (" SELECT Candidate.firstName, Candidate.lastName FROM Candidate JOIN Voter ON Candidate.id = Voter.candidate.id", Candidate.class);
//            candidates = query.getResultList();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//        } finally {
//            session.close();
//        }
//        return candidates;
//    }

//    public List<Candidate> getWinnerOfElection() {
//
//        try (Session session = HibernateConfig.openSession()) {
//
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//
//            CriteriaQuery<Candidate> criteriaQuery = criteriaBuilder.createQuery(Candidate.class);
//            Root<Candidate> root = criteriaQuery.from(Candidate.class);
//            root.join("voters");
//
//            criteriaQuery.multiselect(criteriaBuilder.count(root.get("id")));
//            criteriaQuery.groupBy(root.get("id"));
//            Query<Candidate> query = session.createQuery(criteriaQuery);
//
//            return query.getResultList();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Collections.emptyList();
//    }

        public List<Candidate> getWinnerOfElection () {
            Session session = HibernateConfig.openSession();
            Transaction transaction = session.beginTransaction();
            List<Candidate> winner =new ArrayList<>();
            try {
                Query<Candidate> query = session.createQuery
                        ("SELECT Candidate.firstName,Candidate.lastName from Candidate \n" +
                        "join Voter on Candidate.id = Voter.candidate.id\n" +
                        "group by Candidate.id", Candidate.class);

                query.setMaxResults(1);
                winner = query.getResultList();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            } finally {
                session.close();
            }
            return winner;
        }

//    public List<Candidate> getWinnerOfElection() {
//        Session session = HibernateConfig.openSession();
//        Transaction transaction = session.beginTransaction();
//        List<Candidate> winner = null;
//        try {
//            Query<Candidate> query = session.createQuery("SELECT Candidate.firstName, COUNT(Candidate.firstName) FROM Candidate JOIN Voter ON Candidate.id = Voter\n" +
//                    "GROUP BY candidates.id\n" +
//                    "having count(candidates.first_name) > 0", Candidate.class);
//            winner = query.getResultList();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//        } finally {
//            session.close();
//        }
//        return winner;
//    }
}

