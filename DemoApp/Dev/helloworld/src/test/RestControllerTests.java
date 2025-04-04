@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestControllerTests {

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
        api.perform(get("/api/department").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Abteilung A")));
    }

    private String obtainAccessToken() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "client_id=demoapp&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=admin&" +
                "password=admin";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}