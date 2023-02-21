package peaksoft.repository.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.repository.AppointmentRepository;

import java.util.List;

@Repository
@Transactional
public class AppointmentRepoImpl implements AppointmentRepository {

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public List<Department> getAll() {
        return null;
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
