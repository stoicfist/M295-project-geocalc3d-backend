package ch.peter.ngo.geocalc3d.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class ParametersConverter implements AttributeConverter<Map<String, Double>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Double> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Map to JSON", e);
        }
    }

    @Override
    public Map<String, Double> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory()
                    .constructMapType(HashMap.class, String.class, Double.class));
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to Map", e);
        }
    }
}