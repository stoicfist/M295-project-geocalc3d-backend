package celia.domine.helloworld.Department;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import celia.domine.helloworld.security.Roles;
import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("api/department")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Department>> all() {
        List<Department> result = departmentService.getDepartments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("api/department/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Department> one(@PathVariable @NonNull Long id) {
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("api/department")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Department> newDepartment(@Valid @RequestBody @NonNull Department department) {
        Department savedDepartment = departmentService.insertDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @PutMapping("api/department/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Department> updateDepartment(@Valid @RequestBody @NonNull Department department, @PathVariable @NonNull Long id) {
        Department savedDepartment = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("api/department/{id}")
    @RolesAllowed(Roles.Admin)
    public void deleteDepartment(@PathVariable @NonNull Long id) {
        departmentService.deleteDepartment(id);
    }
}