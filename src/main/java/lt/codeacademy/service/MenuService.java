package lt.codeacademy.service;

import lt.codeacademy.config.HibernateConfig;
import lt.codeacademy.entities.Voter;
import lt.codeacademy.model.constanta.Candidates;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import java.util.Scanner;

import static lt.codeacademy.util.DateTime.date;

public class MenuService {

    VoteService voteServiceImpl;
    VoterService voterService;

    public MenuService(VoteService voteServiceImpl, VoterService voterService) {
        this.voteServiceImpl = voteServiceImpl;
        this.voterService =  voterService;
    }

    public void menuAddVoter(Scanner sc) {

        System.out.println("ENTER YOUR AGE");
        int age = sc.nextInt();

        System.out.println("CHOOSE YOUR GENDER:\n[1] -> MALE\n[2] -> FEMALE");
        Gender chooseYourGender = Gender.convert(sc.nextInt());
        sc.nextLine();

        System.out.println("CHOOSE CITY:\n[1] -> VILNIUS\n[2] -> KAUNAS");
        City chooseYourCity = City.convert(sc.nextInt());
        sc.nextLine();

        System.out.println("CHOOSE YOUR CANDIDATE:\n[1] -> KAZYS_VOLCIUNAS\n[2] -> PRANAS_NUZMAUSKAS");
        Candidates chooseYourCandidate = Candidates.convert(sc.nextInt());

        Voter voter = voteServiceImpl.addVoter(new Voter(age, chooseYourGender, chooseYourCity, chooseYourCandidate, date()));

        HibernateConfig.buildSessionFactory();
        voterService.save(voter);

    }

    public Candidates candidates(Scanner sc) {
        System.out.println("CHOOSE CANDIDATE:\n[1] -> KAZYS_VOLCIUNAS\n[2] -> PRANAS_NUZMAUSKAS");
        if (sc.next().equals("1")) {
            return Candidates.KAZYS_VOLCIUNAS;
        }
        return Candidates.PRANAS_NUZMAUSKAS;
    }

    public int action(int value) {
        if (value == 1 || value == 2 || value == 3 || value == 4 || value == 5 || value == 6 || value == 7 || value == 8 || value == 9 || value == 0) {
            return value;
        }
        return -1;
    }
}