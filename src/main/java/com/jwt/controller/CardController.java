package com.jwt.controller;

import com.jwt.model.Card;
import com.jwt.model.Medical;
import com.jwt.service.CardService;
import com.jwt.service.MedicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    private final MedicalService medicalService;

    public CardController(CardService cardService, MedicalService medicalService) {
        this.cardService = cardService;
        this.medicalService = medicalService;
    }

    @GetMapping
    public String getCard(Model model) {
        List<Card> cards = cardService.getAll();
        List<Medical> medicals = medicalService.getAll();
        model.addAttribute("card", cards);
        model.addAttribute("medical", medicals);
        model.addAttribute("cardAttribute", new Card());
        model.addAttribute("medAttribute", new Medical());
        return "cardhome";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") Integer id) {
        cardService.deleteCard(id);
        return "redirect:/card";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        model.addAttribute("cardAttributes", new Card());
        return "/card/addpage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Card card) {
        cardService.addCard(card);
        return "redirect:/card";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(Card card) {
        cardService.updateCard(card);
        return "redirect:/card";
    }

    @RequestMapping(value = "/med/{id}", method = RequestMethod.GET)
    public String getMed(@PathVariable(value = "id") Integer id){
        medicalService.getMed(id);
        return "redirect:/card";
    }
}
