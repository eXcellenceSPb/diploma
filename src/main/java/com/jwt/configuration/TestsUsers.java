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

import java.util.HashSet;
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
        employee.setName("qwe");
        employee.setPass("qwe");
        employee.setLogin("qwe");
        employee.setEnabled(true);
        employeeService.addEmployee(employee);

        Card card = new Card("organisation","date","name","lname","rank","unit","123");
        Medical medical = new Medical("wound_time","wound","antibiotic");
        medicalService.addMed(medical);
        Set<Medical> med = new HashSet<>();
        med.add(medical);
        card.setMedical(med);
        cardService.addCard(card);

    }
}
