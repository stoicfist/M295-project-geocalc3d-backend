package peter.ngo.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.Assertions;

import peter.ngo.helloworld.Department.Department;
import peter.ngo.helloworld.Department.DepartmentRepository;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DbTest {

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
