package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;

import javax.print.Doc;
import java.util.List;
@Repository
public interface DoctorRepository {
    Doctor save(Doctor doctor);
    List<Doctor> getAll();
    Doctor findById(Long id);
    void delete(Long id);
    void update(Long id, Doctor updatedDoctor);
    List<Doctor>getAll(Long id);
}
