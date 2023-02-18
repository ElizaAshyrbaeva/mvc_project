package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Hospital;

import java.util.List;
@Repository
public interface DepartmentRepository {
    Department save(Department department);

    List<Department> getAll();

    Department findById(Long id);

    void deleteById(Long id);

    void update(Long id,Department newDepartment);
}
