package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeWebController {

    private final EmployeeService service;

    public EmployeeWebController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("employees", service.getAll());
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        service.save(employee);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeePage(@PathVariable Long id, Model model) {
        Employee employee = service.getAll().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, Employee employee) {
        service.update(id, employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
