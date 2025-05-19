package ch.peter.ngo.geocalc3d.service;

import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.repository.FigureInputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShapeService {

    private static final Logger logger = LoggerFactory.getLogger(ShapeService.class);

    private final FigureInputRepository figureInputRepository;

    public ShapeService(FigureInputRepository figureInputRepository) {
        this.figureInputRepository = figureInputRepository;
    }

    public FigureInput saveShape(ShapeInputDto dto) {
        try {
            FigureInput figureInput = new FigureInput();
            figureInput.setShapeType(dto.getShapeType());
            figureInput.setParameters(dto.getParameters());

            // 👇 Benutzername aus Spring Security holen
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            figureInput.setOwner(currentUsername);

            return figureInputRepository.save(figureInput);
        } catch (Exception e) {
            logger.error("Fehler beim Speichern der Figur: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<FigureInput> getAllShapes() {
        return figureInputRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
