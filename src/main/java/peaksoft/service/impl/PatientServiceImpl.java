package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepo;
import peaksoft.service.PatientService;

import java.util.List;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepository hospitalRepository) {
        this.patientRepo = patientRepo;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    @Transactional
    public Patient save(Long id,Patient newPatient) {
         newPatient.setHospital(hospitalRepository.findById(id));
         return patientRepo.save(newPatient);
    }

    @Override
    public List<Patient> getAll(Long id) {
        return patientRepo.getAll(id);
    }

    @Override
    public void deleteById(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public Patient getById(Long id) {
        return patientRepo.getById(id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
        patientRepo.update(id, newPatient);
    }
}
