package ch.peter.ngo.geocalc3d.service;

import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.repository.FigureInputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShapeService {

    private static final Logger logger = LoggerFactory.getLogger(ShapeService.class);

    private final FigureInputRepository figureInputRepository;

    public ShapeService(FigureInputRepository figureInputRepository) {
        this.figureInputRepository = figureInputRepository;
    }

    public FigureInput saveShape(ShapeInputDto dto) {
        try {
            // Konvertiere DTO in Entity
            FigureInput figureInput = new FigureInput();
            figureInput.setShapeType(dto.getShapeType());
            figureInput.setParameters(dto.getParameters());
            figureInput.setOwner(dto.getOwner());

            // Speichere in der Datenbank
            return figureInputRepository.save(figureInput);
        } catch (Exception e) {
            logger.error("Fehler beim Speichern der Figur: {}", e.getMessage(), e);
            throw e; // Exception weiterwerfen, damit sie im Controller behandelt wird
        }
    }
}
