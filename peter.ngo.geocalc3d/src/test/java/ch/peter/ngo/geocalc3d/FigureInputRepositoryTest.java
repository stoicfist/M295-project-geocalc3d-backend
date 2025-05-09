package ch.peter.ngo.geocalc3d;

import ch.peter.ngo.geocalc3d.entity.FigureInput;
import ch.peter.ngo.geocalc3d.repository.FigureInputRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FigureInputRepositoryTest {

    @Autowired
    private FigureInputRepository repository;

    @Test
    void shouldSaveAndRetrieveFigureInput() {
        // Arrange
        FigureInput input = new FigureInput();
        input.setOwner("testuser");
        input.setShapeType("cube");
        input.setParameters(Map.of("side", 5.0));

        // Act
        FigureInput saved = repository.save(input);

        // Assert
        assertNotNull(saved.getId());

        Optional<FigureInput> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());

        FigureInput result = found.get();
        assertEquals("cube", result.getShapeType());
        assertEquals(5.0, result.getParameters().get("side"));
    }

    @Test
    void shouldUpdateAndDeleteFigureInput() {
        // Arrange
        FigureInput input = new FigureInput();
        input.setOwner("updateuser");
        input.setShapeType("pyramid");
        input.setParameters(Map.of("height", 10.0));
        FigureInput saved = repository.save(input);

        // Update
        saved.setShapeType("newShape");
        repository.save(saved);
        assertEquals("newShape", repository.findById(saved.getId()).get().getShapeType());

        // Delete
        repository.delete(saved);
        assertFalse(repository.findById(saved.getId()).isPresent());
    }
}
