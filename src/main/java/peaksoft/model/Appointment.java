package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_gen",
            sequenceName = "appointment_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "appointment_gen")
    private Long id;
    private LocalDate date;
    @Transient
    private Long departmentId;
    @Transient
    private Long doctorId;
    @Transient
    private Long patientId;
    @Transient
    private String date1;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,})
    private Patient patient;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private Doctor doctor;
    @ManyToOne(
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    private Department department;

}
