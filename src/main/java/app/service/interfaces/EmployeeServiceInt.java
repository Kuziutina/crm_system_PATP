package app.service.interfaces;

import app.form.EmployeeForm;
import app.model.Employee;

import java.util.List;

public interface EmployeeServiceInt {
    void addNewEmployee(Employee employee);
    void addNewEmployee(EmployeeForm employee);
    List<Employee> getAllEmployees();
    Employee find(long id);
    Employee find(String lastName, String name, String fatherName);
    void delete(long id);
    void update(long id, EmployeeForm employeeForm);
    void update(long id, Employee employee);

}
