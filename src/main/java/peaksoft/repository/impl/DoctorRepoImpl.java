package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Doctor;
import peaksoft.repository.DoctorRepository;

import java.util.List;

@Repository
@Transactional
public class DoctorRepoImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        return entityManager.createQuery("select d from Doctor d ",Doctor.class).getResultList();
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
