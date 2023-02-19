package peaksoft.service.impl;

import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public Department save(Department department) {
        Department department1 = new Department();
        department1.setId(department.getId());
        department1.setName(department.getName());
        department1.setHospital(hospitalRepository.findById(department.getHospitalId()));
        departmentRepository.save(department1);
        return department1;
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
}
