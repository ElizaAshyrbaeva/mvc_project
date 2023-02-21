package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
