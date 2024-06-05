package ra.crud_employee.service;

import ra.crud_employee.model.entity.Department;

import java.util.List;

public interface DepartmentService{
    List<Department> findAllDepartments();
    Department findDepartmentById(Integer id);
    Department addDepartment(Department department);
    Department updateDepartment(Department department, Integer id);
    void deleteDepartment(Integer id);
}
