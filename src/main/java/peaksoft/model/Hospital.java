package peaksoft.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
//    @NotNull(message = "Name should not be empty")
//    @Size(min = 2,max = 70,message = "name should not be between 2 and 70 characters")
    private String name;
//    @NotNull(message = "Address should not be empty")
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
