package app.controller;

import app.form.JSONObjectForm;
import app.form.TicketFeedbackForm;
import app.service.AuthenticationService;
import app.service.interfaces.TicketServiceInt;
import app.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/feedback")
public class FeedbackController {
    @Autowired
    private TicketServiceInt ticketServiceInt;
    @Autowired

    @GetMapping(value = "/create")
    public String createFeedback() {
        return "create_feedback";
    }


    @PostMapping(value = "/add", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObjectForm addFeedback(@RequestBody TicketFeedbackForm ticketFeedbackForm) {
        ticketServiceInt.addNewFeedback(ticketFeedbackForm);
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