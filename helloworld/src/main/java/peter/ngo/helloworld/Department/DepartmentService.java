package peter.ngo.helloworld.Department;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getDepartments() {
        return repository.findByOrderByNameAsc();
    }

    public Department getDepartment(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
    }

    public Department insertDepartment(Department department) {
        return repository.save(department);
    }

    public Department updateDepartment(Department department, Long id) {
        return repository.findById(id)
                .map(departmentOrig -> {
                    departmentOrig.setName(department.getName());
                    return repository.save(departmentOrig);
                })
                .orElseGet(() -> {
                    department.setId(id);
                    return repository.save(department);
                });
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}
