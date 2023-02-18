package peaksoft.service.impl;

import org.springframework.stereotype.Service;
import peaksoft.model.Department;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Department save(Department department) {
        Department department1 = new Department();
        department1.setId(department.getId());
        department1.setName(department.getName());
        department1.setHospital(hospitalRepository.findById(department.getId()));
        return department1;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Long id, Department newDepartment) {

    }
}