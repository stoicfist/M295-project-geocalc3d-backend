package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.service.ShapeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shapes")
@Tag(name = "Shapes", description = "Verwaltung von 3D-Figuren")
public class ShapeController {

    private final ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Operation(summary = "Neue Figur erstellen", description = "Erstellt eine neue geometrische Figur mit Parametern wie Radius oder Höhe", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Figur erfolgreich erstellt")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<FigureInput> createShape(@RequestBody ShapeInputDto input) {
        FigureInput saved = shapeService.saveShape(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
