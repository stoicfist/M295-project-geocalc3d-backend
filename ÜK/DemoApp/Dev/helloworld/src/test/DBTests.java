@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void insertDepartment() {
        Department depA = this.departmentRepository.save(new Department("Abteilung A"));
        Assertions.assertNotNull(depA.getId());
        Department depB = this.departmentRepository.save(new Department("Abteilung B"));
        Assertions.assertNotNull(depB.getId());
    }
}