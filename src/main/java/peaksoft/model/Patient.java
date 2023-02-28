package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enams.Gender;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

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
//    @NotNull(message =" Name should not be empty")
//    @Size(min = 2,max = 30,message = "Name should  not be between 2 amd 30 characters")
    private String firstName;
//    @NotNull(message ="Last name should not be empty")
//    @Size(min = 2,max = 50,message = "Name should  not be between 2 amd 30 characters")
    private String lastName;

    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String email;
    @ManyToOne(cascade = {CascadeType.DETACH,MERGE,REFRESH,PERSIST})
    private Hospital hospital;
    @OneToMany(mappedBy = "patient",cascade = {CascadeType.ALL})
    private List<Appointment> appoitmentList;
    @Transient
    private Long hospitalId;


}
