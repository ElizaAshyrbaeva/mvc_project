package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;

import java.util.List;
@Service
public interface DoctorService {
    Doctor save(Long id,Doctor doctor);
    List<Doctor> getAll(Long id);
    Doctor findById(Long id);
    void delete(Long id);
    void update(Long id, Doctor updatedDoctor);

}
