package app.service;

import app.form.EmployeeForm;
import app.model.Employee;
import app.model.Position;
import app.repository.EmployeeRepository;
import app.service.interfaces.EmployeeServiceInt;
import app.service.interfaces.PositionServiceInt;
import app.service.interfaces.RouteServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInt {
    private EmployeeRepository employeeRepository;
    private PositionServiceInt positionService;
    private RouteServiceInt routeService;


    public EmployeeService(EmployeeRepository employeeRepository, PositionServiceInt positionService,
                        RouteServiceInt routeService) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
        this.routeService = routeService;
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
        addNewEmployee(employee1);
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

    public List<Employee> getAllDrivers() {
        //TODO убрать этот хардкодный костылище
        return employeeRepository.findByPosition(Position.builder().id(new Long(1)).name(new String("водитель")).build());
    }
}
