package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Department;

import java.util.List;
@Service
public interface DepartmentService {
    Department save(Long hospitalId,Department department);

    List<Department> getAll();

    Department findById(Long id);

    void deleteById(Long id);

    void update(Long id,Department newDepartment);
    List<Department>getAll(Long id);
}
