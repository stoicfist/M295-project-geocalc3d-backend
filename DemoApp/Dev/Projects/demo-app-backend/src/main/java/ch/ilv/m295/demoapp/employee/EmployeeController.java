package ch.ilv.m295.demoapp.employee;

import ch.ilv.m295.demoapp.base.MessageResponse;
import ch.ilv.m295.demoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("api/employee")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Employee>> all() {
        List<Employee> result = employeeService.getEmployees();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/employee/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Employee> one(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("api/employee")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Employee> newEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.insertEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @PutMapping("api/employee/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        Employee savedEmployee = employeeService.updateEmployee(employee, id);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("api/employee/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.deleteEmployee(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
