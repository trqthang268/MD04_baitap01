package ra.crud_employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.crud_employee.model.entity.Department;
import ra.crud_employee.model.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeesByDepartment(Department department);
    List<Employee> findEmployeesByEmplName(String name);
}
