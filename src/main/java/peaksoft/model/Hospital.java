package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "hospitals")
@NoArgsConstructor
public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_gen",sequenceName = "hospital_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_gen")
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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if(appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Patient>patients;

    public  void  setDepartment(Doctor doctor){
        if(doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);

    }

}
