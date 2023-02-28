package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepo;
import peaksoft.service.PatientService;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepository hospitalRepository, AppointmentRepository appointmentRepository) {
        this.patientRepo = patientRepo;
        this.hospitalRepository = hospitalRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public Patient save(Long id, Patient newPatient) {

            newPatient.setHospital(hospitalRepository.findById(id));
            return patientRepo.save(newPatient);

    }

    @Override
    public List<Patient> getAll(Long id) {
            return patientRepo.getAll(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Patient patient = patientRepo.getById(id);
        List<Appointment> appointments = patient.getAppoitmentList();
        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getPatient().getId().equals(id)).toList();
            appointmentList.forEach(s -> appointmentRepository.deleteById(s.getId()));
        }
        List<Patient> patients = patient.getHospital().getPatients();
        patients.removeIf(s -> s.getId().equals(id));

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
