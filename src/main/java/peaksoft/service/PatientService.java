package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

/**
 * @created : Lenovo Nuriza
 **/
public interface PatientService {
    Patient save(Patient patient);
    List<Patient> getAll();
    void deleteById(Long id);
    Patient getById(Long id);
    void update (Long id, Patient newPatient);
}
