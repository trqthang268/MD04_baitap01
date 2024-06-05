package ra.crud_employee.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departId;
    private String departName;
    private Boolean status;

//    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
