package ra.crud_employee.service;

import org.springframework.web.multipart.MultipartFile;
import ra.crud_employee.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee getEmployeeById(Integer empId);
    Employee insertEmployee(Employee employee, MultipartFile file);
    Employee updateEmployee(Employee employee,Integer employeeId, MultipartFile file);
    void deleteEmployee(Integer empId);
    List<Employee> getEmployeeByName(String proName);
}
