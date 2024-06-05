package ra.crud_employee.controller;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.crud_employee.model.entity.Department;
import ra.crud_employee.model.entity.Employee;
import ra.crud_employee.service.DepartmentService;
import ra.crud_employee.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Department> departmentList = departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);
        List<Employee> employeeList = employeeService.getEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        List<Department> departmentList = departmentService.findAllDepartments();
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departmentList", departmentList);
        return "employee/add";
    }

    @PostMapping("/add")
    public String handleAddEmployee(@ModelAttribute("employee") Employee employee,@RequestParam("departmentId") Integer departmentId,@RequestParam("file") MultipartFile file) {
//        Employee emp = employeeService.insertEmployee(employee);
//        if(emp != null) {
//            return "redirect:/employee";
//        }else {
//            List<Department> departmentList = departmentService.findAllDepartments();
//            model.addAttribute("departmentList", departmentList);
//            model.addAttribute("employee", employee);
//            return "employee/add";
//        }
        employee.setDepartment(departmentService.findDepartmentById(departmentId));
        employeeService.insertEmployee(employee,file);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departmentList", departmentService.findAllDepartments());
        return "employee/edit";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@ModelAttribute Employee employee,@PathVariable("id") Integer id,@RequestParam("departmentId") Integer departmentId, @RequestParam("file") MultipartFile file) {
        employee.setDepartment(departmentService.findDepartmentById(departmentId));
        employeeService.updateEmployee(employee,id,file);
        return "redirect:/employee";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }

}
