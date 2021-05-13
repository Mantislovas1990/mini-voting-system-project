package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;
import lt.codeacademy.util.DateTime;

import java.io.IOException;
import java.util.Scanner;

public class MenuService {

    VoteService voteServiceImpl;
    VoterService voterService;
    CandidateService candidateService;

    public MenuService(VoteService voteServiceImpl, VoterService voterService, CandidateService candidateService) {
        this.voteServiceImpl = voteServiceImpl;
        this.voterService = voterService;
        this.candidateService = candidateService;
    }

    public void menuAddVoter(Scanner sc) throws IOException {


        System.out.println("ENTER YOUR AGE");
        int age = sc.nextInt();

        System.out.println("CHOOSE YOUR GENDER:\n[1] -> MALE\n[2] -> FEMALE");
        Gender chooseYourGender = Gender.convert(sc.nextInt());
        sc.nextLine();

        System.out.println("CHOOSE CITY:\n[1] -> VILNIUS\n[2] -> KAUNAS");
        City chooseYourCity = City.convert(sc.nextInt());
        sc.nextLine();

        System.out.println("CHOOSE YOUR CANDIDATE BY ID:\n");
        candidateService.getAllCandidates().forEach(System.out::println);
        Long id = sc.nextLong();
        voterService.saveUpdate(new Voter(candidateService.getCandidateById(id), age, chooseYourGender, chooseYourCity, DateTime.date()));
    }

    public int action(int value) {
        if (value == 1 || value == 2 || value == 3 || value == 4 || value == 5 ||
                value == 6 || value == 7 || value == 8 || value == 9 || value == 0) {
            return value;
        }
        return -1;
    }
}