package service;

import model.Voter;
import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;

import java.util.ArrayList;
import java.util.List;

public class VoteServiceImpl implements VoteService {


    private List<Voter> votingList = new ArrayList<>();

    @Override
    public void addVoter(Voter voter) {
        votingList.add(voter);
    }

    @Override
    public List<Voter> getAllVoters() {
        return votingList;
    }

    @Override
    public Integer getTotalCountOfVotes() {
        return votingList.stream()
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getCandidateVoteCount(Candidates candidates) {
        int counter = 0;
        for (Voter candidate : votingList) {
            if (candidates.equals(candidate.getCandidate())) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Candidates getWinnerOfElection() {
        if (getCandidateVoteCount(Candidates.KAZYS_VOLCIUNAS) > getCandidateVoteCount(Candidates.PRANAS_NUZMAUSKAS)) {
            return Candidates.KAZYS_VOLCIUNAS;
        }
        return Candidates.PRANAS_NUZMAUSKAS;
    }

    @Override
    public City getMostActiveVotingCity() {
        if (getKaunasCityVoteNumber() < getVilniusCityVoteNumber()) {
            return City.VILNIUS;
        }
        return City.KAUNAS;
    }

    @Override
    public Integer getVilniusCityVoteNumber() {
        return votingList.stream()
                .filter(city -> city.getCity() == City.VILNIUS)
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer getKaunasCityVoteNumber() {
        return votingList.stream()
                .filter(city -> city.getCity() == City.KAUNAS)
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);
    }

    @Override
    public Gender getMostActiveGender() {
        if (getKaunasCityVoteNumber() < getVilniusCityVoteNumber()) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    @Override
    public Integer getMaleCount() {
        return votingList.stream()
                .filter(gender -> gender.getGender() == Gender.MALE)
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer getFemaleCount() {
        return votingList.stream()
                .filter(gender -> gender.getGender() == Gender.FEMALE)
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);
    }

    public void setVotingList(List<Voter> votingList) {
        this.votingList = votingList;
    }

    public int getAverageAge() {
        int sum = 0;
        for(Voter age : votingList){
            sum+= age.getAge();
        }
        return sum/votingList.size();
    }
}
