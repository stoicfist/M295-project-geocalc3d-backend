package ch.peter.ngo.geocalc3d.service;

import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.repository.FigureInputRepository;
import org.springframework.stereotype.Service;

@Service
public class ShapeService {

    private final FigureInputRepository figureInputRepository;

    public ShapeService(FigureInputRepository figureInputRepository) {
        this.figureInputRepository = figureInputRepository;
    }

    public FigureInput saveShape(ShapeInputDto dto) {
        // Konvertiere DTO in Entity
        FigureInput figureInput = new FigureInput();
        figureInput.setShapeType(dto.getShapeType());
        figureInput.setParameters(dto.getParameters());
        figureInput.setOwner(dto.getOwner());

        // Speichere in der Datenbank
        return figureInputRepository.save(figureInput);
    }
}
