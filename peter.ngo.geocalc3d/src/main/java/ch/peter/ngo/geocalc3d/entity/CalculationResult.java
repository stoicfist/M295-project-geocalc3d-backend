package ch.peter.ngo.geocalc3d.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double volume;
    private double surface;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "figure_id")
    private FigureInput figure;

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public FigureInput getFigure() {
        return figure;
    }

    public void setFigure(FigureInput figure) {
        this.figure = figure;
    }

    public CalculationResult() {
    }
}
