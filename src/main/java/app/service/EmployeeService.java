package app.service;

import app.model.Employee;
import app.model.Position;
import app.repository.EmployeeRepository;
import app.service.interfaces.EmployeeServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInt {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
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
    public void delete(long id) {
        Employee employee = find(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> getAllDrivers() {
        //TODO убрать этот хардкодный костылище
        return employeeRepository.findByPosition(Position.builder().id(new Long(1)).name(new String("водитель")).build());
    }
}
