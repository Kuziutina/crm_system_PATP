package app.controller;

import app.form.JSONObjectForm;
import app.form.TicketFeedbackForm;
import app.form.TicketForm;
import app.model.Ticket;
import app.model.User;
import app.service.AuthenticationService;
import app.service.interfaces.TicketServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    private TicketServiceInt ticketServiceInt;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(value = "/create")
    public String createFeedback(ModelMap model, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        return "create_ticket";
    }


    @PostMapping(value = "/add", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObjectForm addFeedback(@RequestBody TicketForm ticketForm) {
        Ticket ticket = ticketServiceInt.addNewTicket(ticketForm);
        return new JSONObjectForm(ticket.getId().toString());
    }

    @GetMapping(value = "/{id}/load")
    public void getTicketDoc(@PathVariable("id") long id, HttpServletResponse response) {
        ticketServiceInt.loadTicketDoc(id, response);
    }

}


//
//    JSONObject jsonObject = new JSONObject();
//        jsonObject.put("has", has);
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("text/json");
//                response.getWriter().print(jsonObject.toString());
//                response.getWriter().close();