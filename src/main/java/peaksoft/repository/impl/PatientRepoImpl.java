package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepo;

import java.util.List;
@Repository
@Transactional
public class PatientRepoImpl implements PatientRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public PatientRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Patient save(Patient patient) {
        entityManager.persist(patient);
        return patient;
    }

    @Override
    public List<Patient> getAll(Long id) {
        return entityManager.createQuery("select d from Patient d join d.hospital h where h.id=:id",Patient.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Patient.class, id));
    }

    @Override
    public Patient getById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void update(Long id, Patient newPatient) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setFirstName(newPatient.getFirstName());
        patient.setLastName(newPatient.getLastName());
        patient.setPhoneNumber(newPatient.getPhoneNumber());
        patient.setGender(newPatient.getGender());
        patient.setEmail(newPatient.getEmail());
        patient.setAppoitmentList(newPatient.getAppoitmentList());
    }
}
