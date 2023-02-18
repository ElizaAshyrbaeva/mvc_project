package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "hospitals")
@NoArgsConstructor
public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_gen",
            sequenceName = "hospital_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "hospital_gen")
    private Long id;

    private String link;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.ALL})
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Department> departments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Patient>patients;

}
