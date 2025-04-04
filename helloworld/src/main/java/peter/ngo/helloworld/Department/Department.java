package peter.ngo.helloworld.Department;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Department {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
}
