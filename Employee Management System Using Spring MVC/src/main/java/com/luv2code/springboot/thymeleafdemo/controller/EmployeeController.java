package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //add for list of employees
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get the list from the db
        List<Employee> theEmployees = employeeService.findAll();

        //add to the model
        theModel.addAttribute("employees",theEmployees);
        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //add model attribute for binding the data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        //get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        //set employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        //send over to our form
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){
        //Add services for delete the employee
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}
