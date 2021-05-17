import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;
import lt.codeacademy.service.CandidateService;
import lt.codeacademy.service.VoterService;
import lt.codeacademy.util.DateTime;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoterServiceTest {

    private final VoterService voterService = new VoterService();
    private final CandidateService candidateService = new CandidateService();
    private final List<Voter> testVoters = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        HibernateConfig.buildSessionFactory();
    }

    @AfterAll
    static void afterAll() {
        HibernateConfig.closeSessionFactory();
    }

    @BeforeEach
    void beforeEach() {
        for (int i = 0; i < 4; i++) {
            Voter voter = new Voter(candidateService.getCandidateById(1L), 20+i, Gender.MALE, City.KAUNAS, DateTime.date());
            voterService.saveUpdate(voter);
            assertEquals(voter.getId(), voterService.getVoterById(voter.getId()).getId());
            testVoters.add(voter);
        }
        for (int i = 0; i < 3; i++) {
            Voter voter = new Voter(candidateService.getCandidateById(2L), 10+i, Gender.FEMALE, City.VILNIUS, DateTime.date());
            voterService.saveUpdate(voter);
            assertEquals(voter.getId(), voterService.getVoterById(voter.getId()).getId());
            testVoters.add(voter);
        }
    }

    @AfterEach
    void afterEach() {
        testVoters.forEach(testVoter -> {
            voterService.delete(testVoter.getId());
            Assertions.assertNull(voterService.getVoterById(testVoter.getId()));
        });
    }

    @Test
    void getTotalVoteCountTest(){
        assertEquals(7,voterService.getTotalCount());
    }

    @Test
    void getAllVotersListTest(){
        assertEquals(testVoters.size(), voterService.getAllVoters().size());
    }

    @Test
    void getVoteCountByGenderTest(){
        assertEquals(4,voterService.getVoteCountByGender(1));
        assertEquals(3,voterService.getVoteCountByGender(2));
    }

    @Test
    void getVoteCountByCity(){
        assertEquals(3,voterService.getVoteCountByCity(1));
        assertEquals(4,voterService.getVoteCountByCity(2));
    }

    @Test
    void getWinnerOfElectionTest(){
        assertEquals(1L, candidateService.getWinnerOfElection().getId());
    }

    @Test
    void getVoterByIdTest(){
        testVoters.forEach(testVoter -> Assertions.assertEquals(testVoter.getId(),
                voterService.getVoterById(testVoter.getId()).getId()));
    }

    @Test
    void deleteValueFromVoterListTest(){
        testVoters.forEach(testVoter -> {
            voterService.delete(testVoter.getId());
            Assertions.assertNull(voterService.getVoterById(testVoter.getId()));
        });
    }

    @Test
    void getAllCandidatesTest(){
        assertEquals(2, candidateService.getAllCandidates().size());
    }
}
