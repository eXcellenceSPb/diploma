package com.jwt.controller;

import com.jwt.model.Personal.Employee;
import com.jwt.model.Personal.Role;
import com.jwt.service.EmployeeService;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/emp")
public class UsualController {

    private final EmployeeService employeeService;
    private final RoleService roleService;

    @Autowired
    public UsualController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String home(Model model, Principal principal){
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }

//    @GetMapping(value = "/login")
//    public String login() {
//        return "login";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        model.addAttribute("employeeAttribute", new Employee());
        return "registration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("employeeAttribute") Employee employee) {
        Set<Role> usr = new HashSet<>();
        usr.add(roleService.getRoleByName("ROLE_USER"));
        employee.setRole(usr);
        employeeService.addEmployee(employee);
        return "index";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }

    @GetMapping(value = "403")
    public String error(){
        return "error/403";
    }

}
