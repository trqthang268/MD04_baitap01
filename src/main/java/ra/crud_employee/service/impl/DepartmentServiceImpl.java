package ra.crud_employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.crud_employee.model.entity.Department;
import ra.crud_employee.repository.DepartmentRepository;
import ra.crud_employee.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department, Integer id) {
        department.setDepartId(id);
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            if (!department.getEmployees().isEmpty()){
                department.setStatus(false);
            }else {
                departmentRepository.delete(findDepartmentById(id));
            }
        }
    }
}
