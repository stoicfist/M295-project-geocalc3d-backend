package peter.ngo.helloworld.Department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.NonNull;
import peter.ngo.helloworld.security.Roles;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("api/department")
    public ResponseEntity<Department> newDepartment(@Valid @RequestBody @NonNull Department department) {
        Department savedDepartment = departmentService.insertDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @PutMapping("api/department/{id}")
    public ResponseEntity<Department> updateDepartment(@Valid @RequestBody @NonNull Department department,
            @PathVariable @NonNull Long id) {
        Department savedDepartment = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("api/department/{id}")
    public void deleteDepartment(@PathVariable @NonNull Long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("api/department/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable @NonNull Long id) {
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("api/department")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}