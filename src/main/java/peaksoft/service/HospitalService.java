package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;

import java.util.List;
@Service
public interface HospitalService {
    Hospital save(Hospital hospital);

    List<Hospital> getAllHospital();

    Hospital findById(Long id);

    void deleteHospital(Long id);

    void updateHospital(Long id,Hospital updatedHospital);
    List<Hospital>findAll(String word);
}
