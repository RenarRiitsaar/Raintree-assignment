package raintree.testAssignment.service.impl;

import org.springframework.stereotype.Service;
import raintree.testAssignment.model.Insurance;
import raintree.testAssignment.repository.InsuranceRepository;
import raintree.testAssignment.service.InsuranceService;

import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }
}
