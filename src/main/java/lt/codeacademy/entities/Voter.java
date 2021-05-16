package lt.codeacademy.entities;

import lombok.Getter;
import lombok.Setter;
import lt.codeacademy.model.constanta.City;
import lt.codeacademy.model.constanta.Gender;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "voters")

public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private City city;

    @Column(name = "date")
    private String date;


    public Voter() {
    }

    public Voter(Candidate candidate, int age, Gender gender, City city, String date) {
        this.candidate = candidate;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("ID = %d%nDATE = %s%nVOTERS AGE = %d%nGENDER = %s%nCITY: %s%n==========",
                getId(),date, age, gender, city);
    }
}
