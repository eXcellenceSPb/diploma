package com.jwt.service;

import com.jwt.dao.EmployeeDAO;
import com.jwt.model.Personal.Employee;
import com.jwt.model.Personal.Role;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    public static final int APP_ID = 6738984;
    private final String CLIENT_SECRET = "MnV4CmtJTBOr5hmT5zK6";
    public static final String REDIRECT_URI = "http://localhost:8080/lk";
    private static final String ACCESS_TOKEN = "063ea728063ea728063ea72833065873000063e063ea7285de58c28cccef57131db5f5c";

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void addEmployee(Employee employee) {
        employee.setPass(passwordEncoder.encode(employee.getPassword()));
        employeeDAO.addEmployee(employee);
    }

    public Employee getEmployee(Integer employeeId) {
        return employeeDAO.getEmployee(employeeId);
    }

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee updateEmployee(Employee employee) {
        employee.setPass(passwordEncoder.encode(employee.getPassword()));
        return employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }

    public Employee findEmployeeByLogin(String login) {
        return employeeDAO.findEmployeeByLogin(login);
    }

    public Employee auth(String code) {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        Employee employee = null;
        UserAuthResponse authResponse;
        try {
            authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();

            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

            // Получаем информацию о пользователе
            List<UserXtrCounters> user = vk.users().get(actor).execute();
            String login = "vk" + user.get(0).getId();
            if(employeeDAO.isExist(login)) {
               Employee exist = findEmployeeByLogin(login);
               SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(exist,exist.getPass(),exist.getAuthorities()));
            }
            else{
                employee = new Employee("vk" + user.get(0).getId(), user.get(0).getFirstName());
                employee.setPass("qwe");
                employee.setEnabled(true);
                Set<Role> roles = new HashSet<>();
                roles.add(roleService.getRoleByName("ROLE_USER"));
                employee.setRole(roles);
                addEmployee(employee);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(employee, employee.getPass(), employee.getAuthorities()));
            }

        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
