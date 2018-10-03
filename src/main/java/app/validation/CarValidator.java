package app.validation;

import app.form.CarForm;
import app.service.interfaces.CarServiceInt;
import app.service.interfaces.CarStatusServiceInt;
import app.service.interfaces.RouteServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {
    @Autowired
    private CarStatusServiceInt carStatusService;
    @Autowired
    private RouteServiceInt routeService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.getName().equals(CarForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("i start validate CAR");
        CarForm carForm = (CarForm) target;
        if (carStatusService.find(carForm.getCarStatusName()) == null) {
            errors.reject("not found", "car status not found");
        }
        if (routeService.find(carForm.getRouteName()) == null) {
            errors.reject("not found", "route not found");
        }
    }
}
