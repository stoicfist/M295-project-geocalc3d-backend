package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.security.Roles;
import ch.peter.ngo.geocalc3d.service.ShapeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shapes")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Shapes", description = "Verwaltung von 3D-Figuren")
public class ShapeController {

    private static final Logger logger = LoggerFactory.getLogger(ShapeController.class);

    private final ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Operation(summary = "Neue Figur erstellen", description = "Erstellt eine neue geometrische Figur mit Parametern wie Radius oder Höhe", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Figur erfolgreich erstellt")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FigureInput> createShape(@RequestBody ShapeInputDto input) {
        try {
            FigureInput saved = shapeService.saveShape(input);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            logger.error("Fehler beim Erstellen der Figur: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Liste aller erstellten Figuren", description = "Gibt eine Liste aller gespeicherten Figuren zurück")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/history")
    public ResponseEntity<List<FigureInput>> getAllShapes() {
        List<FigureInput> history = shapeService.getAllShapes();
        return ResponseEntity.ok(history);
    }

    @Operation(summary = "Alle gespeicherten Figuren abrufen", description = "Gibt alle gespeicherten Figuren zurück", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<FigureInput>> getAllShapesDirect() {
        return ResponseEntity.ok(shapeService.getAllShapes());
    }
}
