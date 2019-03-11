package com.jwt.controller.rest;

import com.jwt.model.Card;
import com.jwt.model.Medical;
import com.jwt.model.Personal.Employee;
import com.jwt.service.CardService;
import com.jwt.service.EmployeeService;
import com.jwt.service.MedicalService;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class MyRestController {
    private final PasswordEncoder passwordEncoder;
    private final EmployeeService employeeService;
    private final CardService cardService;
    private final MedicalService medicalService;

    @Autowired
    public MyRestController(EmployeeService employeeService,
                            PasswordEncoder passwordEncoder,
                            CardService cardService,
                            MedicalService medicalService) {
        this.employeeService = employeeService;
        this.cardService = cardService;
        this.medicalService = medicalService;
        this.passwordEncoder = passwordEncoder;

    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public void createUser(@RequestBody Employee employee) {
        Employee add = new Employee();
        add.setName(employee.getName());
        add.setLogin(employee.getLogin());
        add.setPass(passwordEncoder.encode(employee.getPass()));
        add.setEnabled(employee.isEnabled());
        add.setRole(employee.getRole());
        employeeService.addEmployee(add);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Employee updateUser(@PathVariable("id") Integer id,
                               @RequestBody Employee employee) {
        Employee currentEmployee = employeeService.getEmployee(id);
        currentEmployee.setName(employee.getName());
        currentEmployee.setLogin(employee.getLogin());
        currentEmployee.setPass(passwordEncoder.encode(employee.getPass()));
        currentEmployee.setRole(employee.getRole());
        currentEmployee.setEnabled(employee.isEnabled());
        employeeService.updateEmployee(currentEmployee);
        return currentEmployee;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public List<Card> listAllCards() {
        return cardService.getAll();
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
    public Card getCard(@PathVariable("id") Integer id) {
        return cardService.getCard(id);
    }

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public void createCard(@RequestBody Card card) {
        Card add = new Card();
        add.setOrganisation(card.getOrganisation());
        add.setDate(card.getDate());
        add.setFirstname(card.getFirstname());
        add.setLastname(card.getLastname());
        add.setRank(card.getRank());
        add.setUnit(card.getUnit());
        add.setNumber(card.getNumber());
        cardService.addCard(add);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable("id") Integer id) {
        cardService.deleteCard(id);
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.PUT)
    public Card updateCard(@PathVariable("id") Integer id,
                           @RequestBody Card card) {
        Card updateCard = cardService.getCard(id);
        updateCard.setDate(card.getDate());
        updateCard.setFirstname(card.getFirstname());
        updateCard.setLastname(card.getLastname());
        updateCard.setOrganisation(card.getOrganisation());
        updateCard.setRank(card.getRank());
        updateCard.setUnit(card.getUnit());
        updateCard.setNumber(card.getNumber());
        if (card.getMedical() != null) {
            updateCard.setMedical(card.getMedical());
        }
        return updateCard;
    }

    @RequestMapping(value = "/meds", method = RequestMethod.GET)
    public List<Medical> getMedicals() {
        return medicalService.getAll();
    }

    @RequestMapping(value = "/med/{id}", method = RequestMethod.GET)
    public Medical getMedical(@PathVariable("id") Integer id) {
        return medicalService.getMed(id);
    }

    @RequestMapping(value = "/med", method = RequestMethod.POST)
    public void createMed(@RequestBody Medical med) {
        Medical medical = new Medical();
        medical.setWound_time(med.getWound_time());
        medical.setAnaesthetic(med.getAnaesthetic());
        medical.setAnatoxin(med.getAnatoxin());
        medical.setAntibiotic(med.getAntibiotic());
        medical.setAntidot(med.getAntidot());
        medical.setSerum(med.getSerum());
        medical.setCommit(med.getCommit());
        medical.setWound(med.getWound());
        medical.setDiagnosis(med.getDiagnosis());
        medical.setEvacuation(med.getEvacuation());
        medical.setInfo(med.getInfo());
        medical.setLocation(med.getLocation());
        medical.setPlace(med.getPlace());
        medical.setQueue(med.getQueue());
        medical.setTransport(med.getTransport());
        medicalService.addMed(medical);
    }

    @RequestMapping(value = "/medToCard/{id}", method = RequestMethod.PUT)
    public void addMedicalToCard(@PathVariable("id") Integer id,
                                 @RequestBody Medical med) {
        Card card = cardService.getCard(id);
        Medical medical = new Medical();
        medical.setWound_time(med.getWound_time());
        medical.setAnaesthetic(med.getAnaesthetic());
        medical.setAnatoxin(med.getAnatoxin());
        medical.setAntibiotic(med.getAntibiotic());
        medical.setAntidot(med.getAntidot());
        medical.setSerum(med.getSerum());
        medical.setCommit(med.getCommit());
        medical.setWound(med.getWound());
        medical.setDiagnosis(med.getDiagnosis());
        medical.setEvacuation(med.getEvacuation());
        medical.setInfo(med.getInfo());
        medical.setLocation(med.getLocation());
        medical.setPlace(med.getPlace());
        medical.setQueue(med.getQueue());
        medical.setTransport(med.getTransport());

        if (card.getMedical() == null) {
            List<Medical> meds = new ArrayList<>();
            meds.add(medical);
            card.setMedical(meds);
        } else {
            medicalService.addMed(medical);
            card.addMedical(medical);
        }

        cardService.updateCard(card);
    }

    @RequestMapping(value = "/med/{id}", method = RequestMethod.DELETE)
    public void deleteMedical(@PathVariable("id") Integer id) {
        medicalService.deleteMed(id);
    }
}
