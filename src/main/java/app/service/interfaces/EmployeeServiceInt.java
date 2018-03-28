package app.service.interfaces;

import app.model.Employee;

import java.util.List;

public interface EmployeeServiceInt {
    void addNewEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee find(long id);
    void delete(long id);
}
