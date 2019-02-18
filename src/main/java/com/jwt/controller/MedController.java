package com.jwt.controller;

import com.jwt.model.Medical;
import com.jwt.service.MedicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/medical")
public class MedController {
    private final MedicalService medicalService;

    public MedController(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @GetMapping
    public String getMedical(Model model) {
        List<Medical> medicals = medicalService.getAll();
        model.addAttribute("medical", medicals);
        model.addAttribute("medAttribute", new Medical());
        return "cardmedical";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id) {
        medicalService.deleteMed(id);
        return "redirect:/medical";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        List<Medical> medicals = medicalService.getAll();
        model.addAttribute("medical", medicals);
        model.addAttribute("medAttribute", new Medical());
        return "/medical/addpage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Medical medical) {
        medicalService.addMed(medical);
        return "redirect:/medical";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(Medical medical) {
        medicalService.updateMed(medical);
        return "redirect:/medical";
    }
}
