package app.service;

import app.dto.UserDTO;
import app.form.EmployeeForm;
import app.model.Employee;
import app.model.Position;
import app.model.User;
import app.repository.EmployeeRepository;
import app.service.interfaces.EmployeeServiceInt;
import app.service.interfaces.PositionServiceInt;
import app.service.interfaces.RouteServiceInt;
import app.service.interfaces.UserServiceInt;
import app.util.LoginPasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInt {
    private EmployeeRepository employeeRepository;
    private PositionServiceInt positionService;
    private UserServiceInt userService;
    private RouteServiceInt routeService;
    private LoginPasswordGenerator loginPasswordGenerator;
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PositionServiceInt positionService, UserServiceInt userService, RouteServiceInt routeService, LoginPasswordGenerator loginPasswordGenerator, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
        this.userService = userService;
        this.routeService = routeService;
        this.loginPasswordGenerator = loginPasswordGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void addNewEmployee(EmployeeForm employee) {
        Employee employee1 = Employee.builder()
                            .lastName(employee.getLastName())
                            .name(employee.getName())
                            .fatherName(employee.getFatherName())
                            .position(positionService.find(employee.getPositionName()))
                            .experience(employee.getExperience())
                            .salary(employee.getSalary())
                            .route(routeService.find(employee.getRouteName()))
                            .build();
        User user = User.builder()
                            .login(loginPasswordGenerator.generateLogin())
                            .password(loginPasswordGenerator.generatePassword())
                            .build();
        String role;
        switch (employee1.getPosition().getName()) {
            case "Менеджер": role = "MANAGER_ROLE";
                break;
            case "Водитель": role = "EMPLOYEE_ROLE";
                break;
            case "Кондуктор": role = "EMPLOYEE_ROLE";
                break;
            case "Администратор": role = "MANAGER_ROLE";
                break;
            default: role = "EMPLOYEE_ROLE";
        }
        System.out.println("Login: " + user.getLogin() + "\nPassword: " + user.getPassword());

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        user = userService.find(user);
        employee1.setUser(user);

        writeToFile(user, employee1.getLastName());

        addNewEmployee(employee1);
    }

    private void writeToFile(User user, String lastName) {
        try {
            PrintWriter printWriter = new PrintWriter(new File("empl.txt"));
            printWriter.write(lastName);
            printWriter.write(user.getLogin());
            printWriter.write(user.getPassword());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee find(long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee find(String lastName, String name, String fatherName) {
        return employeeRepository.getOneByLastNameAndNameAndFatherName(lastName, name, fatherName);
    }

    @Override
    public void delete(long id) {
        Employee employee = find(id);
        employeeRepository.delete(employee);
    }

    @Override
    public void update(long id, EmployeeForm employee) {
        Employee employee1 = Employee.builder()
                .lastName(employee.getLastName())
                .name(employee.getName())
                .fatherName(employee.getFatherName())
                .position(positionService.find(employee.getPositionName()))
                .experience(employee.getExperience())
                .salary(employee.getSalary())
                .route(routeService.find(employee.getRouteName()))
                .build();
        employeeRepository.update(id, employee1);
    }

    @Override
    public void update(long id, Employee employee) {
        employeeRepository.update(id, employee);
    }
//
//    public List<Employee> getAllDrivers() {
//        //TODO убрать этот хардкодный костылище
//        return employeeRepository.findByPosition(Position.builder().id(new Long(1)).name(new String("водитель")).build());
//    }
}
