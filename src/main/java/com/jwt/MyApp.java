package com.jwt;

import com.jwt.configuration.TestsUsers;
import com.jwt.service.CardService;
import com.jwt.service.EmployeeService;
import com.jwt.service.MedicalService;
import com.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Bean(initMethod = "init")
    @Autowired
    public TestsUsers tests(EmployeeService employeeService, RoleService roleService, CardService cardService, MedicalService medicalService) {
        return new TestsUsers(employeeService, roleService,cardService,medicalService);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128MB");
        factory.setMaxRequestSize("128MB");
        return factory.createMultipartConfig();
    }
}
