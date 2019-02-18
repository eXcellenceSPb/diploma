package com.jwt.service;

import com.jwt.model.Personal.Employee;

import java.util.List;

public interface EmployeeService {

     int APP_ID = 6738984;
     String CLIENT_SECRET = "MnV4CmtJTBOr5hmT5zK6";
     String REDIRECT_URI = "http://localhost:8080/lk";
     String ACCESS_TOKEN = "063ea728063ea728063ea72833065873000063e063ea7285de58c28cccef57131db5f5c";


    void addEmployee(Employee employee);

    List<Employee> getAll();

    void deleteEmployee(Integer employeeId);

    Employee getEmployee(Integer employeeId);

    Employee updateEmployee(Employee employee);

    Employee findEmployeeByLogin(String login);

    Employee auth(String code);
}