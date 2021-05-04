package lt.codeacademy.entities;

import lt.codeacademy.model.constanta.Candidates;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "city")
    private City city;
    @Column(name = "candidate")
    private Candidates candidate;


    private String date;
    private static Long counter = 0L;

    public Voter(int age, Gender gender, City city, Candidates candidate, String date) {
        this.age = age;
        this.gender = gender;

        this.city = city;
        this.candidate = candidate;
        this.date = date;
        counter++;
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

    public static Long getCounter() {
        return counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void setCounter(Long counter) {
        Voter.counter = counter;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCandidate(Candidates candidate) {
        this.candidate = candidate;
    }
}
