package ch.peter.ngo.geocalc3d.repository;

import ch.peter.ngo.geocalc3d.entity.FigureInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureInputRepository extends JpaRepository<FigureInput, Long> {
}
