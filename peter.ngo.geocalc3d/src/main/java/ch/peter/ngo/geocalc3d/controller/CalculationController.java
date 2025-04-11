package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.service.CalculationService;
import ch.peter.ngo.geocalc3d.service.ShapeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculate")
public class CalculationController {

    private final CalculationService calculationService;
    private final ShapeService shapeService;

    public CalculationController(CalculationService calculationService, ShapeService shapeService) {
        this.calculationService = calculationService;
        this.shapeService = shapeService;
    }

    @PostMapping
    public CalculationResult calculate(@RequestBody FigureInput input) {
        // optional: zuerst speichern (falls nötig)
        FigureInput savedInput = shapeService.saveShape(new ch.peter.ngo.geocalc3d.dto.ShapeInputDto(
                input.getShapeType(), input.getParameters(), input.getOwner()));

        return calculationService.calculate(savedInput);
    }
}
