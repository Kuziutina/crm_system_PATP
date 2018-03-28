package app.controller;

import app.service.CarService;
import app.service.CarStatusService;
import app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    public CarService carService;

    @Autowired
    public CarStatusService carStatusService;

    @Autowired
    public EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllCars(ModelMap map) {
        map.addAttribute("items", carService.getAllCars());
        return "list_of_car";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String getCreate(ModelMap modelMap) {
        modelMap.addAttribute("statuses", carStatusService.getAllCarStatuses());
        modelMap.addAttribute("drivers", employeeService.getAllDrivers());
        return "create_car";

    }


}
