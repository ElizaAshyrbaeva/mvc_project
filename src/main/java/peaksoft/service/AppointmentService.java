package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;

import java.util.List;
@Service
public interface AppointmentService {
    Appointment save(Long hospitalId,Appointment appointment);

    List<Appointment> getAll(Long id);

    Appointment findById(Long id);

    void deleteById(Long hId,Long id);

    void update(Long id,Appointment newAppointment);
}
