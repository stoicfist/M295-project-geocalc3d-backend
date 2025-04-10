package ch.peter.ngo.geocalc3d.entity;

import ch.peter.ngo.geocalc3d.converter.JsonbConverter;
import ch.peter.ngo.geocalc3d.model.ShapeInput;
import ch.peter.ngo.geocalc3d.model.ShapeResult;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "jsonb")
    private ShapeInput inputData;

    @Convert(converter = JsonbConverter.class)
    @Column(columnDefinition = "jsonb")
    private ShapeResult resultData;

    private LocalDateTime timestamp;

    private String username;
}
