package ch.peter.ngo.geocalc3d.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShapeInput {
    private String shape;
    private double sideLength;
}