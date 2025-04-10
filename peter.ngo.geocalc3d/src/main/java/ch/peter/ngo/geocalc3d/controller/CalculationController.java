package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.entity.CalculationResult;
import ch.peter.ngo.geocalc3d.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/calculate")
public class CalculationController {

    private final CalculationService service;

    public CalculationController(CalculationService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CalculationResult> calculate(
            @RequestBody Map<String, Object> input,
            @AuthenticationPrincipal Jwt jwt
    ) {
        try {
            String username = jwt.getClaimAsString("preferred_username");
            CalculationResult result = service.calculateAndSave(username, input);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Fehlerbehandlung, falls etwas schiefgeht
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleExceptions(Exception ex) {
        Map<String, String> errorResponse = Map.of(
                "error", "Ein Fehler ist aufgetreten",
                "details", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
