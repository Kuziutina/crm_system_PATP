package app.controller;

import app.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import app.service.NewsService;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    public NewsService newsService;

    @RequestMapping(method = RequestMethod.GET)
    public String getMain(ModelMap map){
        map.addAttribute("news", newsService.getAllNews());
        return "main";
    }
}
