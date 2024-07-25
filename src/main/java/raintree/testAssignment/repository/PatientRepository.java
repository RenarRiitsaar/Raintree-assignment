package raintree.testAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raintree.testAssignment.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
