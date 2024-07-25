package raintree.testAssignment.service.impl;

import org.springframework.stereotype.Service;
import raintree.testAssignment.model.Patient;
import raintree.testAssignment.repository.PatientRepository;
import raintree.testAssignment.service.PatientService;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
