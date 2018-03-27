package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.service.PositionService;

@Controller
@RequestMapping("/main")
public class MainController {
    private PositionService positionService;

    public MainController(PositionService positionService) {
        this.positionService = positionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMain(){
        positionService.addNewPosition("vodit");
        return "main";
    }
}
