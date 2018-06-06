package app.controller;

import app.form.JSONObjectForm;
import app.form.TicketFeedbackForm;
import app.service.interfaces.TicketServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/feedback")
public class TicketController {
    @Autowired
    private TicketServiceInt ticketServiceInt;

    @GetMapping(value = "/create")
    public String createFeedback() {
        return "create_feedback";
    }


    @PostMapping(value = "/add", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObjectForm addFeedback(@RequestBody TicketFeedbackForm ticketFeedbackForm) {
        ticketServiceInt.addNewTicket(ticketFeedbackForm);
        return new JSONObjectForm();
    }
}


//
//    JSONObject jsonObject = new JSONObject();
//        jsonObject.put("has", has);
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("text/json");
//                response.getWriter().print(jsonObject.toString());
//                response.getWriter().close();