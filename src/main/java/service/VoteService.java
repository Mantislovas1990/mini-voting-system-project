package service;

import model.Voter;
import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;

import java.util.List;

public interface VoteService {

    void addVoter(Voter voter);

    List<Voter> getAllVoters();

    Integer getTotalCountOfVotes();

    int getCandidateVoteCount(Candidates candidates);

    Candidates getWinnerOfElection();

    Integer getVilniusCityVoteNumber();

    Integer getKaunasCityVoteNumber();

    City getMostActiveVotingCity();

    Gender getMostActiveGender();

    Integer getMaleCount();

    Integer getFemaleCount();
}
