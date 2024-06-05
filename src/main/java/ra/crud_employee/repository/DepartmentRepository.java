package ra.crud_employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.crud_employee.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
