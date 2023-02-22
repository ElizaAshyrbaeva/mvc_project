package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepo patientRepo;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, PatientRepo patientRepo) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientRepo = patientRepo;
    }
    @Transactional
    @Override
    public Appointment save(Long hospitalId,Appointment appointment) {
        Hospital hospital = hospitalRepository.findById(hospitalId);
        Appointment newAppointment = new Appointment();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(appointment.getDate1(), formatter);
        newAppointment.setDate(localDate);
        newAppointment.setId(appointment.getId());
        newAppointment.setPatient(patientRepo.getById(appointment.getPatientId()));
        newAppointment.setDoctor(doctorRepository.findById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepository.findById(appointment.getDepartmentId()));
        hospital.addAppointment(newAppointment);
        return appointmentRepository.save(newAppointment);

    }
    @Override
    public List<Appointment> getAll(Long id) {
        return appointmentRepository.getAll(id);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Appointment newAppointment) {
        appointmentRepository.update(id,newAppointment);
    }
}
