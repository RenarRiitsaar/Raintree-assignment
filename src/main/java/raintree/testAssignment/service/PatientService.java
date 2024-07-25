package raintree.testAssignment.service;

import org.springframework.stereotype.Service;
import raintree.testAssignment.model.Patient;

import java.util.List;

@Service
public interface PatientService {

    List<Patient> getAllPatients();
}
