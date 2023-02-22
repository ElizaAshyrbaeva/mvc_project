package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientService {
    Patient save(Long id,Patient patient);
    List<Patient> getAll(Long id);
    void deleteById(Long id);
    Patient getById(Long id);
    void update (Long id, Patient newPatient);
}
