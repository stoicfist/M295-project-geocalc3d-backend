package ch.peter.ngo.geocalc3d.dto;

import java.util.Map;

public class ShapeInputDto {
    private String shapeType;
    private Map<String, Double> parameters;
    private String owner; // Neues Feld hinzugefügt

    // Getter und Setter
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

    public String getOwner() { // Getter für owner
        return owner;
    }

    public void setOwner(String owner) { // Setter für owner
        this.owner = owner;
    }
}