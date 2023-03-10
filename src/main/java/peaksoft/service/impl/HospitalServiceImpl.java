package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital save(Hospital hospital) {
            return this.hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAllHospital() {
            return this.hospitalRepository.getAllHospital();

    }

    @Override
    public Hospital findById(Long id) {
            return hospitalRepository.findById(id);
    }

    @Override
    public void deleteHospital(Long id) {
            this.hospitalRepository.delete(id);
    }

    @Override
    public void updateHospital(Long id, Hospital updatedHospital) {
            this.hospitalRepository.updateHospital(id, updatedHospital);

    }

    @Override
    public List<Hospital> findAll(String word) {

            if (word != null && !word.trim().isEmpty()) {
                return hospitalRepository.search(word);
            } else {
                return hospitalRepository.getAllHospital();
            }
    }
}
