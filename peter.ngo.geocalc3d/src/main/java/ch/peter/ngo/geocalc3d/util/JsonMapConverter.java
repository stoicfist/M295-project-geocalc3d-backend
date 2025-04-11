package ch.peter.ngo.geocalc3d.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.postgresql.util.PGobject;

import java.util.HashMap;
import java.util.Map;

@Converter
public class JsonMapConverter implements AttributeConverter<Map<String, Double>, PGobject> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(Map<String, Double> attribute) {
        try {
            if (attribute == null) {
                return null;
            }
            PGobject pgObject = new PGobject();
            pgObject.setType("jsonb");
            pgObject.setValue(objectMapper.writeValueAsString(attribute));
            return pgObject;
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Konvertieren der Map zu PGobject (jsonb)", e);
        }
    }

    @Override
    public Map<String, Double> convertToEntityAttribute(PGobject dbData) {
        try {
            if (dbData == null || dbData.getValue() == null) {
                return new HashMap<>();
            }
            return objectMapper.readValue(dbData.getValue(), new TypeReference<Map<String, Double>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Konvertieren von PGobject (jsonb) zu Map", e);
        }
    }
}
