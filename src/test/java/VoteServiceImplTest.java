import model.Voter;
import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.VoteServiceImpl;
import util.DateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteServiceImplTest {



    Voter firstVoter = new Voter(20, Gender.MALE, City.VILNIUS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());
    Voter secondVoter = new Voter(30, Gender.FEMALE, City.VILNIUS, Candidates.PRANAS_NUZMAUSKAS, DateTime.date());
    Voter thirdVoter = new Voter(40, Gender.MALE, City.KAUNAS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());
    Voter fourthVoter = new Voter(50, Gender.MALE, City.VILNIUS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());

    VoteServiceImpl service = new VoteServiceImpl();

    @BeforeEach
    void init() {
        service.setVotingList( new ArrayList<>());
        service.addVoter(firstVoter);
        service.addVoter(secondVoter);
        service.addVoter(thirdVoter);
        service.addVoter(fourthVoter);
    }

    @Test
    void addVoterTest() {
        assertEquals(4, service.getAllVoters().size());
    }

    @Test
    void getTotalCountOfVotesTest() {
        int voteCount = service.getTotalCountOfVotes();

        assertEquals(4, voteCount);
    }

    @Test
    void getCandidateVoteCountTest() {

        int checkVote = service.getCandidateVoteCount(Candidates.KAZYS_VOLCIUNAS);

        assertEquals(3, checkVote);
    }

    @Test
    void getMostActiveVotingCityTest() {

        City mostActive = service.getMostActiveVotingCity();

        assertEquals(City.VILNIUS, mostActive);
    }

    @Test
    void getKaunasCityVoteNumber() {

        int kaunasVotes = service.getKaunasCityVoteNumber();
        assertEquals(1, kaunasVotes);
    }

    @Test
    void getMostActiveGenderTest(){

        Gender activeGender = service.getMostActiveGender();
        assertEquals(Gender.MALE, activeGender);
    }

    @Test
    void getFemaleCountTest(){

        int femaleVotes = service.getFemaleCount();
        assertEquals(1, femaleVotes);
    }

    @Test
    void getMaleGenderTest(){

        int maleVotes = service.getMaleCount();
        assertEquals(3, maleVotes);
    }

    @Test
    void averageAgeTest(){
        int aveAge = service.getAverageAge();

        assertEquals(35, aveAge);
    }

    @Test
    void getWinnerOfElectionTest(){
        Candidates winner = service.getWinnerOfElection();
        assertEquals(Candidates.KAZYS_VOLCIUNAS,winner);
    }

}