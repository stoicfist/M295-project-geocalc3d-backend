package peter.ngo.helloworld;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import peter.ngo.helloworld.Department.Department;
import peter.ngo.helloworld.Department.DepartmentRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTest {

    @Autowired
    private MockMvc api;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeAll
    void setup() {
        this.departmentRepository.save(new Department("Abteilung A"));
        this.departmentRepository.save(new Department("Abteilung B"));
    }

    @Test
    @Order(1)
    void testGetDepartments() throws Exception {
        String accessToken = obtainAccessToken();
        api.perform(get("/api/department")
                .header("Authorization", "Bearer " + accessToken)
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Abteilung A")));
    }

    private String obtainAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=demoapp&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=user&" +
                "password=user";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:8080/realms/ILV/protocol/openid-connect/token",
                entity,
                String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response.getBody()).get("access_token").toString();
    }
}