package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Doctor> doctors;
    @Transient
    private Long hospitalId;
    @ManyToOne(
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Hospital hospital;


}
