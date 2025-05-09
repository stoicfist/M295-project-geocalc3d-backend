package ch.peter.ngo.geocalc3d;

import ch.peter.ngo.geocalc3d.controller.ShapeController;
import ch.peter.ngo.geocalc3d.dto.ShapeInputDto;
import ch.peter.ngo.geocalc3d.service.ShapeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ShapeController.class)
class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShapeService shapeService;

    @Test
    void shouldCreateShape() throws Exception {
        // Arrange
        ShapeInputDto dto = new ShapeInputDto("cube", Map.of("side", 2.0), "testuser");
        String json = new ObjectMapper().writeValueAsString(dto);

        when(shapeService.saveShape(any(ShapeInputDto.class))).thenReturn(null); // Mock Service

        // Act & Assert
        mockMvc.perform(post("/api/shapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}
