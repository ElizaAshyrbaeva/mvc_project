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
    public Doctor save(Doctor doctor){
            entityManager.persist(doctor);
            return doctor;

    }

    @Override
    public List<Doctor> getAll() {
            return entityManager.createQuery("select d from Doctor d ", Doctor.class).getResultList();

    }

    @Override
    public Doctor findById(Long id) {
            return entityManager.find(Doctor.class, id);

    }

    @Override
    public void delete(Long id) {
            entityManager.remove(entityManager.find(Doctor.class, id));

    }

    @Override
    public void update(Long id, Doctor updatedDoctor) {
            Doctor doctor = entityManager.find(Doctor.class, id);
            doctor.setFirstName(updatedDoctor.getFirstName());
            doctor.setLastName(updatedDoctor.getLastName());
            doctor.setPosition(updatedDoctor.getPosition());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setImage(updatedDoctor.getImage());

    }

    @Override
    public List<Doctor> getAll(Long id) {
            return entityManager.createQuery("select d from Doctor d join d.hospital h where h.id=:id", Doctor.class).setParameter("id", id).getResultList();
    }
}
