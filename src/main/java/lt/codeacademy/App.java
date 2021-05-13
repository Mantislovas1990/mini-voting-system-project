package lt.codeacademy;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.Menu;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;
import lt.codeacademy.service.CandidateService;
import lt.codeacademy.service.VoterService;
import lt.codeacademy.util.DateTime;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.run();

//        HibernateConfig.buildSessionFactory();
//        VoterService voterService = new VoterService();
//        System.out.println(voterService.getVoterById(1L));
//
//        System.out.println(voterService.getTotalCount("Voter"));
//        Scanner sc = new Scanner(System.in);
//        Candidates candidates = Candidates.convert(1);

//        CandidateService candidateService = new CandidateService();
//        Voter voter = new Voter(candidateService.getCandidateById(1L),20, Gender.FEMALE, City.VILNIUS, DateTime.date());
//
//        voterService.saveUpdate(voter);

//        System.out.println(candidateService.winnerOfElections());

//        HibernateConfig.closeSessionFactory();
    }
}