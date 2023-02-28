package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Hospital;

import java.util.List;

@Repository
public interface HospitalRepository {
    Hospital save(Hospital hospital);

    List<Hospital> getAllHospital();

    Hospital findById(Long id);


    void updateHospital(Long id, Hospital updatedHospital);

    List<Hospital> search(String word);

    void delete(Long id);
}
