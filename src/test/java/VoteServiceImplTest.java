import model.Voter;
import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;
import org.junit.jupiter.api.Test;
import util.DateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteServiceImplTest {

    List<Voter> voters = new ArrayList<>();

    Voter firstVoter = new Voter(20, Gender.MALE, City.VILNIUS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());
    Voter secondVoter = new Voter(30, Gender.FEMALE, City.VILNIUS, Candidates.PRANAS_NUZMAUSKAS, DateTime.date());
    Voter thirdVoter = new Voter(40, Gender.MALE, City.KAUNAS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());
    Voter fourthVoter = new Voter(50, Gender.MALE, City.VILNIUS, Candidates.KAZYS_VOLCIUNAS, DateTime.date());

    public VoteServiceImplTest() {
        voters.add(firstVoter);
        voters.add(secondVoter);
        voters.add(thirdVoter);
        voters.add(fourthVoter);
    }

    @Test
    void addVoterTest() {
        assertEquals(4, voters.size());
    }

    @Test
    void getTotalCountOfVotesTest() {
        int count = voters.stream()
                .map(Voter::getCounter)
                .reduce(0, Integer::sum);

        assertEquals(4, count);
    }

    @Test
    void getCandidateVoteCountTest() {
        int counter = 0;
        for (Voter candidate : voters) {
            if (Candidates.KAZYS_VOLCIUNAS.equals(candidate.getCandidate())) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }

    @Test
    void getMostActiveVotingCityTest() {
        int counter = 0;
        for (Voter city : voters) {
            if (City.VILNIUS.equals(city.getCity())) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }

    @Test
    void getKaunasCityVoteNumber() {
        int counter = 0;
        for (Voter city : voters) {
            if (City.KAUNAS.equals(city.getCity())) {
                counter++;
            }
        }
        assertEquals(1, counter);
    }

    @Test
    void getMostActiveGenderTest(){
        int counter = 0;
        for (Voter gender : voters) {
            if (Gender.MALE.equals(gender.getGender())) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }

    @Test
    void getFemaleCountTest(){
        int counter = 0;
        for (Voter gender : voters) {
            if (Gender.FEMALE.equals(gender.getGender())) {
                counter++;
            }
        }
        assertEquals(1, counter);
    }

    @Test
    void getMaleGenderTest(){
        int counter = 0;
        for (Voter gender : voters) {
            if (Gender.MALE.equals(gender.getGender())) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }

    @Test
    void averageAgeTest(){
        int sum = 0;
        for(Voter age : voters){
            sum+= age.getAge();
        }
        sum = sum / voters.size();

        assertEquals(35,sum);
    }

}