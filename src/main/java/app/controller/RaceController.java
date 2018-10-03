package app.controller;

import app.service.interfaces.RouteServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/races")
public class RaceController {
    @Autowired
    RouteServiceInt routeServiceInt;

    @GetMapping
    public String getAllRaces(ModelMap modelMap) {
        modelMap.addAttribute("races", routeServiceInt.getAllRoutes());
        return "routes";
    }

    @GetMapping(value = "/{id}")
    public String getAllRaces(@PathVariable(name = "id") long id, ModelMap modelMap) {
        modelMap.addAttribute("race", routeServiceInt.find(id));
        return "route";
    }
}
