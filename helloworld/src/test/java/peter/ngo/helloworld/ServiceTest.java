package peter.ngo.helloworld;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import peter.ngo.helloworld.Department.Department;
import peter.ngo.helloworld.Department.DepartmentRepository;
import peter.ngo.helloworld.Department.DepartmentService;

public class ServiceTest {

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
