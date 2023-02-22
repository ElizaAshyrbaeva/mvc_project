package peaksoft.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_gen",
            sequenceName = "doctor_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "doctor_gen")
    private Long id;
//    @NotNull(message = "Name should not be empty")
//    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    private String firstName;
//    @NotNull(message = "Name should not be empty")
//    @Size(min = 2,max = 50,message = "Name should be between 2 and 50 characters")
    private String lastName;

    private String position;

    private String email;
    private String image;
    @Transient
    private Long hospitalId;
    @Transient
    private List<Long> departmentId= new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Department> departmentList;
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private List<Appointment> appointmentList;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    private Hospital hospital;

}
