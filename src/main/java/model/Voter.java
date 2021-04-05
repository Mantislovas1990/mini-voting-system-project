package model;

import model.constanta.Candidates;
import model.constanta.City;
import model.constanta.Gender;


public class Voter {

    private int age;
    private Gender gender;
    private City city;
    private Candidates candidate;
    private String date;

    public Voter(int age, Gender gender, City city, Candidates candidate, String date) {
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.candidate = candidate;
        this.date = date;
    }

    public Voter() {
    }

    @Override
    public String toString() {
        return String.format("%n==========%nCANDIDATE NAME = %s%nDATE = %s%nVOTERS AGE = %d%nGENDER = %s%nCITY: %s%n==========%n",
                 candidate, date, age, gender, city);
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public City getCity() {
        return city;
    }

    public Candidates getCandidate() {
        return candidate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCounter() {
        int counter = 1;
        return counter;
    }
}
