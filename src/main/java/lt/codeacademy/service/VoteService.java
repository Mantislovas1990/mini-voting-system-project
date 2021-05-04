package lt.codeacademy.service;


import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.Candidates;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import java.util.List;

public interface VoteService {

    void addVoter(Voter voter);

    List<Voter> getAllVoters();

    Long getTotalCountOfVotes();

    int getCandidateVoteCount(Candidates candidates);

    Candidates getWinnerOfElection();

    Long getVilniusCityVoteNumber();

    Long getKaunasCityVoteNumber();

    City getMostActiveVotingCity();

    Gender getMostActiveGender();

    Long getMaleCount();

    Long getFemaleCount();
}