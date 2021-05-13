package lt.codeacademy.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(
            mappedBy = "candidate",
            cascade = CascadeType.ALL)
    private List<Voter> voters;

    public Candidate() {
    }


    public Candidate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("==========%nID = %d%nFIRST NAME = %s%nLAST NAME = %s%n==========",
                getId(), firstName, lastName);
    }

    public Long getId() {
        return id;
    }

}
