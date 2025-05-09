package ch.peter.ngo.geocalc3d.service;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.repository.CalculationResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CalculationService {

    private final CalculationResultRepository resultRepo;

    public CalculationService(CalculationResultRepository resultRepo) {
        this.resultRepo = resultRepo;
    }

    public CalculationResult calculate(FigureInput input) {
        String type = input.getShapeType().toLowerCase();
        Map<String, Double> p = input.getParameters();

        double volume = 0;
        double surface = 0;

        switch (type) {
            case "kugel":
                double r = p.getOrDefault("radius", 0.0);
                volume = (4.0 / 3.0) * Math.PI * Math.pow(r, 3);
                surface = 4 * Math.PI * Math.pow(r, 2);
                break;
            case "quader":
                double a = p.getOrDefault("a", 0.0);
                double b = p.getOrDefault("b", 0.0);
                double c = p.getOrDefault("c", 0.0);
                volume = a * b * c;
                surface = 2 * (a * b + a * c + b * c);
                break;
            case "kegel":
                r = p.getOrDefault("radius", 0.0);
                double h = p.getOrDefault("hoehe", 0.0);
                volume = (1.0 / 3.0) * Math.PI * Math.pow(r, 2) * h;
                surface = Math.PI * r * (r + Math.sqrt(r * r + h * h));
                break;
            default:
                throw new IllegalArgumentException("Unbekannter ShapeType: " + type);
        }

        CalculationResult result = new CalculationResult();
        result.setFigure(input);
        result.setVolume(volume);
        result.setSurface(surface);
        result.setTimestamp(LocalDateTime.now());

        return resultRepo.save(result);
    }
}
