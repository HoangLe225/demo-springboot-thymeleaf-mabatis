package com.example.demo_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo_spring_boot.entity.Employee;
import com.example.demo_spring_boot.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
//	@GetMapping("/list")
//	public String listUsers(Model model) {
//
//		List<Employee> employeeList = employeeService.findAll();
//				
//		model.addAttribute("employeeList", employeeList);
//
//		return "employees/list-employees";
//	}
	
	@GetMapping("/list")
	public String listUsers(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
		
		Page<Employee> employeeList = employeeService.getPaginated(page, size);
		
		model.addAttribute("employeeList", employeeList);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") long theId, Model theModel) {

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		//set employee in the model to repopulate the form
		theModel.addAttribute("employee", theEmployee);

		// send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		System.out.println(theEmployee);
		// save the employee
		employeeService.save(theEmployee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") long theId) {

		// delete the employee
		employeeService.deleteById(theId);
		// redirect to the /employees/list
		return "redirect:/employees/list";
	}
}
