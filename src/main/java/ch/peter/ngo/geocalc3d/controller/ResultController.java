package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import ch.peter.ngo.geocalc3d.repository.CalculationResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final CalculationResultRepository resultRepository;

    public ResultController(CalculationResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping
    public List<CalculationResult> getAllResults() {
        return resultRepository.findAll();
    }
}
