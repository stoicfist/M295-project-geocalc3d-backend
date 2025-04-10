package ch.peter.ngo.geocalc3d.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.postgresql.util.PGobject;

@Converter(autoApply = true)
public class JsonbConverter implements AttributeConverter<Object, PGobject> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public PGobject convertToDatabaseColumn(Object attribute) {
        try {
            PGobject pgObject = new PGobject();
            pgObject.setType("jsonb");
            pgObject.setValue(objectMapper.writeValueAsString(attribute));
            return pgObject;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert to PGobject", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(PGobject dbData) {
        try {
            if (dbData != null) {
                return objectMapper.readValue(dbData.getValue(), Object.class);
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert PGobject to Object", e);
        }
    }
}
