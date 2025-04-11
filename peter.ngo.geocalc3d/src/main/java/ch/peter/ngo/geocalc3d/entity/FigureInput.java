package ch.peter.ngo.geocalc3d.entity;

import jakarta.persistence.*;
import java.util.Map;
import ch.peter.ngo.geocalc3d.converter.ParametersConverter;

@Entity
public class FigureInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shapeType;

    @Column(columnDefinition = "jsonb", nullable = false)
    @Convert(converter = ParametersConverter.class)
    private Map<String, Double> parameters;

    private String owner;

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Double> parameters) {
        this.parameters = parameters;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}