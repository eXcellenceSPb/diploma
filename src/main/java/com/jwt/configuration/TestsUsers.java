package com.jwt.configuration;

import com.jwt.model.Card;
import com.jwt.model.Medical;
import com.jwt.model.Personal.Employee;
import com.jwt.model.Personal.Role;
import com.jwt.service.CardService;
import com.jwt.service.EmployeeService;
import com.jwt.service.MedicalService;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestsUsers {

    private RoleService roleService;
    private EmployeeService employeeService;
    private CardService cardService;
    private MedicalService medicalService;


    @Autowired
    public TestsUsers(EmployeeService employeeService, RoleService roleService,CardService cardService,MedicalService medicalService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.cardService = cardService;
        this.medicalService = medicalService;
    }

    public void init() {
        Role adm = new Role("ROLE_ADMIN");
        Role usr = new Role("ROLE_USER");
        roleService.addRole(adm);
        roleService.addRole(usr);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        roles.add(roleService.getRoleByName("ROLE_USER"));
        Employee employee = new Employee();
        employee.setRole(roles);
        employee.setName("admin");
        employee.setPass("admin");
        employee.setLogin("admin");
        employee.setEnabled(true);
        employeeService.addEmployee(employee);

        Card card = new Card("organisation","10/10/10","name","lname","rank","unit","123");
        Medical medical = new Medical("10:00","ранение","антибиотик");
        medicalService.addMed(medical);
        List<Medical> med = new ArrayList<>();
        med.add(medical);
        card.setMedical(med);
        cardService.addCard(card);

    }
}
