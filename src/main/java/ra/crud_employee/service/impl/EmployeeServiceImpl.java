package ra.crud_employee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.crud_employee.model.entity.Employee;
import ra.crud_employee.repository.EmployeeRepository;
import ra.crud_employee.service.EmployeeService;
import ra.crud_employee.service.UploadFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UploadFile uploadFile;
    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer empId) {
        return employeeRepository.findById(empId).orElseThrow(()->new NoSuchElementException("Employee Not Found"));
    }

    @Override
    public Employee insertEmployee(Employee employee, MultipartFile file) {
        if (file!=null && file.getSize()>0) {
            employee.setAvatar(uploadFile.uploadToLocal(file));
        }else {
            employee.setAvatar("https://media.istockphoto.com/id/1196083861/vi/vec-to/b%E1%BB%99-bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-%C4%91%E1%BA%A7u-ng%C6%B0%E1%BB%9Di-%C4%91%C3%A0n-%C3%B4ng-%C4%91%C6%A1n-gi%E1%BA%A3n.jpg?s=612x612&w=0&k=20&c=7juGotIovn0c2KFGhZ_DcEqpfiSyYl-zz2ty9XYnYNs=");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee,Integer employeeId,MultipartFile file) {
        employee.setEmplId(employeeId);
        if (file!=null && file.getSize()>0) {
            employee.setAvatar(uploadFile.uploadToLocal(file));
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public List<Employee> getEmployeeByName(String employeeName) {
        return employeeRepository.findEmployeesByEmplName(employeeName);
    }
}
