package ch.peter.ngo.geocalc3d.service;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import ch.peter.ngo.geocalc3d.model.ShapeInput;
import ch.peter.ngo.geocalc3d.model.ShapeResult;
import ch.peter.ngo.geocalc3d.repository.CalculationResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CalculationService {

    private final CalculationResultRepository repository;
    private final ObjectMapper objectMapper;

    public CalculationService(CalculationResultRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public CalculationResult calculateAndSave(String username, Map<String, Object> input) throws Exception {
        // Map in ShapeInput konvertieren
        ShapeInput shapeInput = objectMapper.convertValue(input, ShapeInput.class);

        // Berechnungen durchführen
        double seite = shapeInput.getSideLength();
        double volumen = Math.pow(seite, 3);
        double fläche = 6 * Math.pow(seite, 2);

        // Ergebnis erstellen
        ShapeResult result = new ShapeResult(volumen, fläche);

        // CalculationResult erstellen und speichern
        CalculationResult calculationResult = CalculationResult.builder()
                .username(username)
                .inputData(shapeInput)
                .resultData(result)
                .timestamp(LocalDateTime.now())
                .build();

        return repository.save(calculationResult);
    }
}
