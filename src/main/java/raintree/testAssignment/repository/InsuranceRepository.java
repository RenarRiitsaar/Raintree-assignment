package raintree.testAssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raintree.testAssignment.model.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

}
