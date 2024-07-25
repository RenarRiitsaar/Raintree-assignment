package raintree.testAssignment.service;

import org.springframework.stereotype.Service;
import raintree.testAssignment.model.Insurance;

import java.util.List;

@Service
public interface InsuranceService {

    List<Insurance> getAllInsurances();
}
