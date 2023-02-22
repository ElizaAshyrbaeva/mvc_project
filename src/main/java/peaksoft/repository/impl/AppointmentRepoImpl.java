package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.App;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@Transactional
public class AppointmentRepoImpl implements AppointmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    public AppointmentRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Appointment save(Appointment appointment) {
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAll(Long id) {
        return entityManager.createQuery("select a from Hospital h  join  h.appointments a  where  h.id=:id", Appointment.class).setParameter("id",id).getResultList();

    }

    @Override
    public Appointment findById(Long id) {
        return entityManager.find(Appointment.class,id);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Appointment.class,id));
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        appointment.setDate(newAppointment.getDate());
    }
}
