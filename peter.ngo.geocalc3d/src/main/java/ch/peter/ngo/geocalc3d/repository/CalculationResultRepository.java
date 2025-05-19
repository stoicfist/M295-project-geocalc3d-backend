package ch.peter.ngo.geocalc3d.repository;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationResultRepository extends JpaRepository<CalculationResult, Long> {
}
