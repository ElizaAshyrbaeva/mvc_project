package peaksoft.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;

import java.util.List;

@Repository

public interface AppointmentRepository {
    Department save(Department department);

    List<Department> getAll();

    Department findById(Long id);

    void deleteById(Long id);

    void update(Long id,Department newDepartment);

}
