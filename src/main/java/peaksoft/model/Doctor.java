package peaksoft.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

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
    private String firstName;
    private String lastName;

    private String position;

    private String email;
    private String image;
    @Transient
    private Long hospitalId;
    @Transient
    private List<Long> departmentId =new ArrayList<>();
    @ManyToMany(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private List<Department>departmentList;
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;
    @ManyToOne(cascade = {CascadeType.DETACH,MERGE,REFRESH,PERSIST})
    private Hospital hospital;

    public  void  setDepartment(Department department){
        if(departmentList==null){
            departmentList=new ArrayList<>();
        }
        departmentList.add(department);

    }

}
