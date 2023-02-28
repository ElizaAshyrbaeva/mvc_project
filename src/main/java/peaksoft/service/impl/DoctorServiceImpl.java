package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final AppointmentRepository appointmentRepository;


    @Transactional
    @Override
    public Doctor save(Long id, Doctor newDoctor) {
        Hospital hospital = hospitalRepository.findById(id);
        Doctor doctor = new Doctor();
        doctor.setId(newDoctor.getId());
        doctor.setFirstName(newDoctor.getFirstName());
        doctor.setLastName(newDoctor.getLastName());
        doctor.setEmail(newDoctor.getEmail());
        doctor.setPosition(newDoctor.getPosition());
        doctor.setImage(newDoctor.getImage());
        doctor.setHospital(hospital);
        for (Long aLong : newDoctor.getDepartmentId()) {
            doctor.setDepartment(departmentRepository.findById(aLong));
        }


        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll(Long hospitalId) {
        return doctorRepository.getAll(hospitalId);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id);
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id);
        List<Appointment> appointments = doctor.getAppointmentList();
        if (appointments != null) {
            List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDoctor().getId().equals(id)).toList();
            appointmentList.forEach(s -> appointmentRepository.deleteById(s.getId()));
        }
        List<Doctor> doctors = doctor.getHospital().getDoctors();
        doctors.removeIf(d->d.getId().equals(id));

        doctorRepository.delete(id);
    }

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.findById(id);
    }
    @Transactional
    @Override
    public void update(Long id, Doctor newDoctor) {
        doctorRepository.update(id, newDoctor);
    }
}
