package app.controller;


import app.dto.RouteDTO;
import app.form.*;
import app.model.Route;
import app.model.Station;
import app.service.interfaces.*;
import app.validation.CarValidator;
import app.validation.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/crm")
public class RestController {
    @Autowired
    private EmployeeServiceInt employeeService;
    @Autowired
    private PositionServiceInt positionService;
    @Autowired
    private CarServiceInt carService;
    @Autowired
    private DriverLicenseServiceInt driverLicenseService;
    @Autowired
    private RouteServiceInt routeService;
    @Autowired
    private StationServiceInt stationService;
    @Autowired
    private TicketServiceInt ticketService;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private CarValidator carValidator;


    @InitBinder({"addEmployee", "changeEmployee"})
    public void initAddEmployeeValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(employeeValidator);
    }
    @InitBinder({"addCar", "changeCar"})
    public void initAddCarValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(carValidator);
    }

    @GetMapping(value = "/employees")
    public String getAllEmployees(ModelMap modelMap) {
        modelMap.addAttribute("employees", employeeService.getAllEmployees());
        return "crm/employees";
    }

    @GetMapping(value = "/employees/{id}/delete")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.delete(id);
        return "redirect: /crm/employees";
    }

    @GetMapping(value = "/employees/{id}/change")
    public String changeEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeService.find(id));
        return "crm/updateEmployee";
    }
    @PostMapping(value = "/employees/{id}/update")
    public RedirectView changeEmployeePost(@PathVariable("id") long id, @ModelAttribute("changeEmployee") @Valid EmployeeForm employee, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("lastEmployee", employee);
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
        }
        else {
            redirectAttributes.addFlashAttribute("error", "suc");
            employeeService.update(id, employee);
        }
        return new RedirectView("/crm/employees/"+id+"/change");
    }

    @PostMapping(value = "/employees/add")
    public RedirectView addEmployee(@ModelAttribute("addEmployee") @Valid EmployeeForm employeeForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            employeeService.addNewEmployee(employeeForm);
            redirectAttributes.addFlashAttribute("error", "suc");
        }
        else if(employeeService.find(employeeForm.getLastName(), employeeForm.getName(), employeeForm.getFatherName()) != null) {
            redirectAttributes.addFlashAttribute("lastEmployee", employeeForm);
            redirectAttributes.addFlashAttribute("error", "we has same");
        }
        else {
            redirectAttributes.addFlashAttribute("lastEmployee",employeeForm);
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
        }
        return new RedirectView("/crm/employees");

    }


    @GetMapping(value = "/cars")
    public String getAllCars(ModelMap modelMap) {
        modelMap.addAttribute("cars", carService.getAllCars());
        return "crm/cars";
    }

    @GetMapping(value = "/cars/{id}/delete")
    public String deleteCar(@PathVariable("id") long id) {
        carService.delete(id);
        return "redirect: /crm/cars";
    }

    @GetMapping(value = "/cars/{id}/change")
    public String changeCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carService.find(id));
        return "crm/updateCar";
    }
    @PostMapping(value = "/cars/{id}/update")
    public RedirectView changeCarPost(@PathVariable("id") long id, @ModelAttribute("changeCar") @Valid CarForm carForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("lastCar", carForm);
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
        }
        else {
            redirectAttributes.addFlashAttribute("error", "suc");
            carService.update(id, carForm);
        }
        return new RedirectView("/crm/cars/"+id+"/change");
    }

    @PostMapping(value = "/cars/add")
    public RedirectView addCar(@ModelAttribute("addCar") @Valid CarForm carForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (carService.find(carForm.getNumber()) != null) {
            redirectAttributes.addFlashAttribute("error", "Такая машина уже есть");
            redirectAttributes.addFlashAttribute("lastCar", carForm);
        }
        else if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            redirectAttributes.addFlashAttribute("lastCar", carForm);
        }
        else {
            carService.addNewCar(carForm);
            redirectAttributes.addFlashAttribute("error", "Машина успешно добавлена");
        }
        return new RedirectView("/crm/cars");
    }

    @GetMapping(value = "/driver_licenses")
    public String getAllDriverLicenses(Model model) {
        model.addAttribute("driver_licenses", driverLicenseService.getAllDriverLicenses());
        return "crm/driver_licenses";
    }

    @GetMapping(value = "/driver_licenses/{id}/delete")
    public String deleteDriverLicenses(@PathVariable("id") long id) {
        driverLicenseService.delete(id);
        return "redirect: /crm/driver_licenses";
    }

    @GetMapping(value = "/driver_licenses/{id}/change")
    public String changeDriverLicense(@PathVariable("id") long id, Model model) {
        model.addAttribute("driverLicense", driverLicenseService.find(id));
        return "crm/updateDriverLicense";
    }
    @PostMapping(value = "/driver_licenses/{id}/update")
    public RedirectView changeDriverLicensesPost(@PathVariable("id") long id, @ModelAttribute("changeDriverLicense") @Valid DriverLicenseForm driverLicenseForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("lastDriverLicense", driverLicenseForm);
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
        }
        else {
            redirectAttributes.addFlashAttribute("error", "suc");
            driverLicenseService.update(id, driverLicenseForm);
        }
        return new RedirectView("/crm/driver_licenses/"+id+"/change");
    }


    @PostMapping(value = "/driver_licenses/add")
    public RedirectView addDriverLicense(@ModelAttribute("addDriverLicense") DriverLicenseForm driverLicenseForm, RedirectAttributes attributes) {

        if (employeeService.find(driverLicenseForm.getLastName(), driverLicenseForm.getName(), driverLicenseForm.getFatherName()) != null) {
            driverLicenseService.addNewDriverLicense(driverLicenseForm);
            attributes.addFlashAttribute("error", "suc");
        }
        else {
            attributes.addFlashAttribute("lastDriverLicense",driverLicenseForm);
            attributes.addFlashAttribute("error", "employee not find");
        }
        return new RedirectView("/crm/driver_licenses");
    }


    @GetMapping(value = "/routes/{id}/change")
    public String changeRoute(@PathVariable("id") long id, Model model) {
        model.addAttribute("route", routeService.find(id));
        return "crm/updateRoute";
    }
    @PostMapping(value = "/routes/{id}/update")
    public RedirectView changeRoutePost(@PathVariable("id") long id, @ModelAttribute("changeRoute") RouteForm routeForm, RedirectAttributes redirectAttributes) {
        if(routeService.find(routeForm.getName(), routeForm.getType()) != null) {
            redirectAttributes.addFlashAttribute("lastRoute", routeForm);
            redirectAttributes.addFlashAttribute("id", id);
            redirectAttributes.addFlashAttribute("error", "has same");
        }
        else {
            redirectAttributes.addFlashAttribute("error", "suc");
            routeService.update(id, routeForm);
        }
        return new RedirectView("/crm/routes/"+id+"/change");
    }
    @GetMapping(value = "/routes/station/{id}/change")
    public String changeStation(@PathVariable("id") long id, Model model) {
        model.addAttribute("station", stationService.find(id));
        return "crm/updateStation";
    }
    @PostMapping(value = "/routes/station/{id}/update")
    public RedirectView changeStationPost(@PathVariable("id") long id, @ModelAttribute("changeStation") StationForm stationForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "suc");
        stationService.update(id, stationForm);
        return new RedirectView("/crm/routes/station/"+id+"/change");
    }

    @GetMapping(value = "routes/station/filter/{name}")
    public @ResponseBody List<RouteDTO> getRouteByFilter(@PathVariable("name") String name) {
        return routeService.findByStationFilter(name);
    }



    @GetMapping(value = "/routes")
    public String getAllRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "crm/routes";
    }

    @GetMapping(value = "/routes/{id}/delete")
    public String deleteRoutes(@PathVariable("id") long id) {
        routeService.delete(id);
        return "redirect: /crm/routes";
    }

    @GetMapping(value = "/routes/{id}/station/{id_station}/delete")
    public String deleteStation(@PathVariable("id") long id, @PathVariable("id_station")long id_station) {
        routeService.deleteStationFromRoute(id, id_station);
        return "redirect: /crm/routes";
    }

    @PostMapping(value = "/routes/add")
    public RedirectView addRoutes(@ModelAttribute("addRoute")RouteForm routeForm, RedirectAttributes attributes) {

        if (routeService.find(routeForm.getName(), routeForm.getType()) == null) {
            System.out.println("i start add in controller");
            routeService.addNewRoute(routeForm);
            attributes.addFlashAttribute("error", "suc");
        }
        else {
            attributes.addFlashAttribute("lastRouteForm",routeForm);
            attributes.addFlashAttribute("error", "employee not find");
        }
        return new RedirectView("/crm/routes");
    }

    @PostMapping(value = "/routes/{id}/station/add")
    public RedirectView addRoutesStation(@ModelAttribute("addStation")StationForm stationForm, @PathVariable("id") long route_id, RedirectAttributes attributes) {
        Station station = stationService.find(stationForm.getName());
        if (station == null) {
            stationService.addNewStation(stationForm);
            station = stationService.find(stationForm.getName());
        }
        routeService.addStationOnRoute(route_id, station);
        return new RedirectView("/crm/routes");

    }




    @GetMapping(value = "/tickets")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "crm/tickets";
    }

    @PostMapping(value = "/tickets")
    public String getPartTickets(Model model, @RequestParam("status") String status) {
        System.out.println(status);
        if (status.equals("Любой")) {
            model.addAttribute("tickets", ticketService.getAllTickets());
        }
        else {
            model.addAttribute("tickets", ticketService.getAllTicketsByStatus(status));
        }
        return "crm/tickets";
    }

}
