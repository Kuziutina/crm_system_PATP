package app.validation;

import app.form.EmployeeForm;
import app.service.interfaces.EmployeeServiceInt;
import app.service.interfaces.PositionServiceInt;
import app.service.interfaces.RouteServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidator implements Validator{

    @Autowired
    private EmployeeServiceInt employeeService;
    @Autowired
    private RouteServiceInt routeService;
    @Autowired
    private PositionServiceInt positionService;

    private final List<String> positionsForRoute= new ArrayList<String>(){{
        add("driver");
        add("conductor");
    }};



    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.getName().equals(EmployeeForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(supports(target.getClass())) {
            System.out.println("i start validate EMPLOYEE");
            EmployeeForm employeeForm = (EmployeeForm) target;
            System.out.println(employeeForm.getPositionName());
            if(positionsForRoute.contains(employeeForm.getPositionName())) {
                if (routeService.find(employeeForm.getRouteName()) == null) {
                    errors.reject("not found", "Данный маршрут не найден");
                }
            }
            else {
                if(employeeForm.getRouteName() != null && !employeeForm.getRouteName().equals("")) {
                    errors.reject("not able", "Маршрут можно указать только водителям и кондукторам");
                }
            }
            if (positionService.find(employeeForm.getPositionName()) == null) {
                errors.reject("not found", "Данная должность не найдена, проверьте данные или обратитесь к администратору");
            }
        }

    }
}
