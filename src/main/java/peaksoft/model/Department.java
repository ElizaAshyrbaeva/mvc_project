package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name = "department_gen",
            sequenceName = "department_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "department_gen")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "departmentList",
            cascade = {DETACH,MERGE,PERSIST,REFRESH})
    private List<Doctor> doctors;
    @Transient
    private Long hospitalId;
    @ManyToOne(
            cascade = {DETACH,MERGE,PERSIST,REFRESH})
    private Hospital hospital;

    public void setDepartment(Doctor doctor) {
        if (doctors == null) {
            doctors = new ArrayList<>();
        }
        doctors.add(doctor);
    }
}
