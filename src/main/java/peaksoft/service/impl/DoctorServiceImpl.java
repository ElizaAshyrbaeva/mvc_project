package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.getAll();
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
