package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Doctor save(Long id,Doctor doctor) {
        Hospital hospital = hospitalRepository.findById(id);
        Doctor doctor1 = new Doctor();
        doctor1.setId(doctor.getId());
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setImage(doctor.getImage());
       doctor1.setHospital(hospital);
        doctor1.getDepartmentId().forEach(s->doctor1.getDepartmentList().add(departmentRepository.findById(s)));
        doctorRepository.save(doctor1);
        return doctor1;
    }

    @Override
    public List<Doctor> getAll(Long id) {
        return doctorRepository.getAll(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        doctorRepository.delete(id);

    }

    @Override
    public void update(Long id, Doctor updatedDoctor) {
        doctorRepository.update(id, updatedDoctor);
    }
}
