package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepository;

import java.util.List;

@Repository
public class DoctorRepoImpl implements DoctorRepository {

    @Override
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        return null;
    }

    @Override
    public Doctor findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, Doctor updatedDoctor) {

    }
}
