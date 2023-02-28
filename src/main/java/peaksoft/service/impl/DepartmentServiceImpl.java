package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DepartmentServiceImpl(HospitalRepository hospitalRepository, DepartmentRepository departmentRepository, AppointmentRepository appointmentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    public Department save(Long hospitalId, Department newDepartment) {
        if (departmentRepository.getAll(hospitalId).isEmpty()) {
            Hospital hospital = hospitalRepository.findById(hospitalId);
            newDepartment.setHospital(hospital);
            return departmentRepository.save(newDepartment);
        } else {
            for (Department department : departmentRepository.getAll(hospitalId)) {
                if (department.getName().equals(newDepartment.getName())) {
                } else {
                    Hospital hospital = hospitalRepository.findById(hospitalId);
                    newDepartment.setHospital(hospital);
                    return departmentRepository.save(newDepartment);
                }
            }
        }
        return newDepartment;
    }
    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }
    @Override
        public void deleteById (Long id){
            Department department = departmentRepository.findById(id);
            List<Appointment> appointments = department.getHospital().getAppointments();

            if (appointments != null) {
                List<Appointment> appointmentList = appointments.stream().filter(s -> s.getDepartment().getId().equals(id)).toList();
                appointmentList.forEach(s -> appointmentRepository.deleteById(s.getId()));
            }

            List<Department> departments = department.getHospital().getDepartments();
            departments.removeIf(s -> s.getId().equals(id));

            departmentRepository.deleteById(id);
        }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        Department byId = departmentRepository.findById(id);
        for (Department department : departmentRepository.getAll(byId.getHospital().getId())) {
            List<Department> departments = departmentRepository.getAll(byId.getHospital().getId());
            departments.remove(byId);
            if (!department.getName().equals(newDepartment.getName())) {
                departmentRepository.update(id, newDepartment);

            }

        }
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }
}
