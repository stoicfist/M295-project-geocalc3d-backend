package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.security.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<String, String>> getOwnResults() {
        Map<String, String> response = Map.of("message", "Deine Ergebnisse (USER)");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> getAllResults() {
        Map<String, String> response = Map.of("message", "Alle Ergebnisse (ADMIN)");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleExceptions(Exception ex) {
        Map<String, String> errorResponse = Map.of("error", "Ein Fehler ist aufgetreten", "details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
