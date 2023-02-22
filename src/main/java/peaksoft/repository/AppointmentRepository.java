package peaksoft.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.App;
import peaksoft.model.Appointment;
import peaksoft.model.Department;

import java.util.List;

@Repository

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    List<Appointment> getAll(Long id);

    Appointment findById(Long id);

    void deleteById(Long id);

    void update(Long id,Appointment newAppointment);

}
