package peaksoft.repository;

import peaksoft.model.Hospital;
import peaksoft.model.Patient;

import java.util.List;


public interface PatientRepo {
    Patient save(Patient patient);
    List<Patient> getAll(Long id);
    void deleteById(Long id);
    Patient getById(Long id);
    void update (Long id, Patient newPatient);

}
