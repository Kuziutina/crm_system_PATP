package app.controller;

import app.dto.MessageDTO;
import app.form.AddMessageForm;
import app.form.JSONObjectForm;
import app.form.UserImageForm;
import app.form.UserUpdateForm;
import app.model.Message;
import app.model.User;
import app.service.AuthenticationService;
import app.service.interfaces.MessageServiceInt;
import app.service.interfaces.TicketServiceInt;
import app.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserServiceInt userService;
    @Autowired
    private TicketServiceInt ticketService;
    @Autowired
    private MessageServiceInt messageService;


    @GetMapping(value = "/profile")
    public String getUser(ModelMap modelMap, Authentication authentication){
        User user = authenticationService.getUserByAuthentication(authentication);
        modelMap.addAttribute("viewUser", user);
        return "profile";
    }

    @GetMapping(value = "/profile/change")
    public String changeUser(ModelMap modelMap, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        modelMap.addAttribute("viewUser", user);
        return "profile_change";
    }

    @GetMapping(value = "/profile/feedbacks")
    public String userFeedback(ModelMap modelMap, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        modelMap.addAttribute("viewUser", user);
        modelMap.addAttribute("feedbacks", ticketService.getAllUsersFeedback(user) );
        return "user_feedbacks";
    }

    @GetMapping(value = "/profile/tickets")
    public String userTicket(ModelMap modelMap, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        modelMap.addAttribute("viewUser", user);
        modelMap.addAttribute("tickets", ticketService.getAllUsersTicket(user) );
        return "user_tickets";
    }

    @PostMapping(value = "/profile/change")
    public String changeUserPost(@ModelAttribute(name = "changeUser") UserUpdateForm userUpdateForm, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        userService.updateUserData(user, userUpdateForm);
        return "redirect: /profile";
    }


    @PostMapping(value = "/profile/changeImage")
    public void changeUserImagePost(@ModelAttribute(name = "userImageForm") UserImageForm userImageForm) {
        System.out.println(userImageForm.toString());
        System.out.println(userImageForm.getImageUser());
        User user = userService.find(userImageForm.getUserId());
        userService.updateUserImage(user, userImageForm);
    }

    @GetMapping(value = "/userImage/load/{id}")
    public void loadUserImage(@PathVariable("id") long id, HttpServletResponse response) {
        userService.loadUserImage(id, response);
    }

    @GetMapping(value = "/profile/messages")
    public String getMessages(ModelMap model, Authentication authentication) {
        User user = authenticationService.getUserByAuthentication(authentication);
        model.addAttribute("recipients", messageService.getRecipients(user));
        return "user_messages";
    }

    @PostMapping(value = "/profile/messages", produces = "application/json")
    @ResponseBody
    public List<MessageDTO> getConcreteMessage(@RequestBody Long recipient_id, Authentication authentication) {
        User sender = authenticationService.getUserByAuthentication(authentication);
        User recipient = userService.find(recipient_id);
        List<MessageDTO> messages = messageService.getAllMessage(sender, recipient);
        System.out.println(messages.size());
        return messages;
    }


    @PostMapping(value = "/profile/messages/add",  headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObjectForm addMessage(@RequestBody AddMessageForm addMessageForm) {
        System.out.println(addMessageForm.getBody());
        messageService.addMessage(addMessageForm);
        return new JSONObjectForm();
    }


    @GetMapping(value = "/user/{id}")
    public String getAnotherUser(@PathVariable("id") long id, ModelMap modelMap, Authentication authentication){
        if (id == authenticationService.getUserByAuthentication(authentication).getId()) {
            return "redirect: /profile";
        }
        User user = userService.find(id);
        modelMap.addAttribute("viewUser", user);
        return "another_profile";
    }



}
