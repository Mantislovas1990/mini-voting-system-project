package lt.codeacademy.model;


import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.Candidates;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import java.util.List;

public interface VoteInterface {

    Voter addVoter(Voter voter);

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
