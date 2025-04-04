package ch.ilv.m295.demoapp.employee;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees() {
        return repository.findByOrderByNameAscFirstnameAsc();
    }

    public Employee getEmployee(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Employee.class));
    }

    public Employee insertEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        return repository.findById(id)
                .map(employeeOrig -> {
                    employeeOrig.setName(employee.getName());
                    employeeOrig.setFirstname(employee.getFirstname());
                    employeeOrig.setBadge(employee.getBadge());
                    employeeOrig.setDepartment(employee.getDepartment());
                    return repository.save(employeeOrig);
                })
                .orElseGet(() -> repository.save(employee));
    }

    public MessageResponse deleteEmployee(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Emplyee " + id + " deleted");
    }
}
