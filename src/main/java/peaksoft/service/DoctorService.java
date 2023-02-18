package peaksoft.service;

import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor save(Doctor doctor);
    List<Doctor> getAll();
    Doctor findById(Long id);
    void delete(Long id);
    void update(Long id, Doctor updatedDoctor);

}
