
class DepartmentServiceTests {

    private DepartmentService departmentService;
    private final DepartmentRepository departmentRepositoryMock = mock(DepartmentRepository.class);

    private final Department departmentMock = mock(Department.class);

    @BeforeEach
    void setUp() {
        departmentService = new DepartmentService(departmentRepositoryMock);
    }

    @Test
    void createDepartment() {
        when(departmentRepositoryMock.save(departmentMock)).thenReturn(departmentMock);
        departmentService.insertDepartment(departmentMock);
        verify(departmentRepositoryMock, times(1)).save(any());
    }
}