package com.jwt.controller;

import com.jwt.service.EmployeeService;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jwt.service.EmployeeService.APP_ID;
import static com.jwt.service.EmployeeService.REDIRECT_URI;

@Controller
@RequestMapping("/")
public class AuthController {

    private final EmployeeService service;
    private final RoleService roleService;

    @Autowired
    public AuthController(EmployeeService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @GetMapping("/lk")
    public String authVk(@RequestParam(name = "code") String code) {
        service.auth(code);
        return "redirect:/emp";
    }


    @GetMapping("/vk/login")
    public ModelAndView openAuth() {

        return new ModelAndView(new RedirectView("https://oauth.vk.com/authorize" +
                "?client_id=" + APP_ID +
                "&display=popup" +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=friends" +
                "&response_type=code" +
                "&v=5.87" +
                "&grant_type=client_credentials"));
    }

    @RequestMapping("/google/login")
    public String google() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "/index";
    }

    @RequestMapping("/callback")
    public String callback() {
        return "/home";
    }
}
