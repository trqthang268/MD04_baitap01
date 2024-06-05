package ra.crud_employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.crud_employee.model.entity.Department;
import ra.crud_employee.model.entity.Employee;
import ra.crud_employee.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String showAllDepartment(Model model) {
        List<Department> departmentList = departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);
        return "department/list";
    }

    @GetMapping("/add")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "department/add";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.addDepartment(department);
        return "redirect:/department";
    }
    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Integer id, Model model) {
        Department department = departmentService.findDepartmentById(id);
        model.addAttribute("department", department);
        return "department/edit";
    }
    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Integer id, @ModelAttribute Department department) {
        departmentService.updateDepartment(department,id);
        return "redirect:/department";
    }
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department";
    }
}
