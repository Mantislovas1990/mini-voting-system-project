package lt.codeacademy.service;

import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.VoteInterface;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import java.util.ArrayList;
import java.util.List;

public class VoteService implements VoteInterface {


    private List<Voter> votingList = new ArrayList<>();

    @Override
    public Voter addVoter(Voter voter) {
//        votingList.add(voter);
        return voter;
    }

    @Override
    public List<Voter> getAllVoters() {
        return votingList;
    }


    @Override
    public City getMostActiveVotingCity() {
        if (getKaunasCityVoteNumber() < getVilniusCityVoteNumber()) {
            return City.VILNIUS;
        }
        return City.KAUNAS;
    }

    @Override
    public Long getVilniusCityVoteNumber() {
        return votingList.stream()
                .filter(city -> city.getCity() == City.VILNIUS)
                .count();
    }

    @Override
    public Long getKaunasCityVoteNumber() {
        return votingList.stream()
                .filter(city -> city.getCity() == City.KAUNAS)
                .count();
    }

    @Override
    public Gender getMostActiveGender() {
        if (getMaleCount() > getFemaleCount()) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    @Override
    public Long getMaleCount() {
        return votingList.stream()
                .filter(gender -> gender.getGender() == Gender.MALE)
                .count();
    }

    @Override
    public Long getFemaleCount() {
        return votingList.stream()
                .filter(gender -> gender.getGender() == Gender.FEMALE)
                .count();
    }

    public void setVotingList(List<Voter> votingList) {
        this.votingList = votingList;
    }

    public int getAverageAge() {
        int sum = 0;
        for (Voter age : votingList) {
            sum += age.getAge();
        }
        return sum / votingList.size();
    }

    public List<Voter> removeRecord(int id) {
        votingList.removeIf(vote -> vote.getId() == id);
        return votingList;
    }
}
