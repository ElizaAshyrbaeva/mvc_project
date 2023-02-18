package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;

import java.util.List;
@Repository
public interface HospitalRepository {
    Hospital save(Hospital hospital);

    List<Hospital> getAllHospital();

    Hospital findById(Long id);

    void deleteHospital(Long id);

    void updateHospital(Long id,Hospital updatedHospital);
}
