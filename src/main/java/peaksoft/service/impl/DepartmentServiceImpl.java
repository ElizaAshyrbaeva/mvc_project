package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    @Override
    public Department save(Long hospitalId,Department department) {
        Hospital hospital = hospitalRepository.findById(hospitalId);
        department.setHospital(hospital);
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
            departmentRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        departmentRepository.update(id,newDepartment);

    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepository.getAll(id);
    }
}
