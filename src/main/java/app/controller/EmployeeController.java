package app.controller;

import app.service.interfaces.EmployeeServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeServiceInt employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceInt employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAllEmployee(ModelMap model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }


}
