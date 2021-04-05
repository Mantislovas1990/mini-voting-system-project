package service;

import model.Voter;
import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;

import java.util.Scanner;

import static util.DateTime.date;

public class MenuService {

    VoteServiceImpl voteServiceImpl;

    public MenuService(VoteServiceImpl voteServiceImpl) {
        this.voteServiceImpl = voteServiceImpl;
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


        voteServiceImpl.addVoter(new Voter(age, chooseYourGender, chooseYourCity, chooseYourCandidate, date()));
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