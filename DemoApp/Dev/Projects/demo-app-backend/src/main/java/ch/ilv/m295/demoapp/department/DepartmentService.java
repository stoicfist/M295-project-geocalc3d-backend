package ch.ilv.m295.demoapp.department;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException(id, Department.class));
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
                .orElseGet(() -> repository.save(department));
    }

    public MessageResponse deleteDepartment(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Department " + id + " deleted");
    }
}
