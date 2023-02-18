package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enams.Gender;

import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_gen",
            sequenceName = "patient_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "patient_gen")
    private Long id;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String email;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<Appointment> appoitmentList;


}
