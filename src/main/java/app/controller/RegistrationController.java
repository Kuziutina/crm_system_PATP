package app.controller;

import app.dn.UserRegisterDto;
import app.model.User;
import app.service.UserService;
import app.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRegistrationValidator userRegistrationValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder
    public void initUserRegistrationValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userRegistrationValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, ModelMap model) {
        UserRegisterDto userDto = new UserRegisterDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserRegisterDto accountDto,
            BindingResult result,
            ModelMap model) {

        model.addAttribute("user", accountDto);
        User registered = new User();
        if (!result.hasErrors()) {
            accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            registered = createUserAccount(accountDto);
        }
        else {
            System.out.println("we has error: " + result.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("regSuc", false);
        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
        if (result.hasErrors()) {
            return "registration";
        }
        else {
            return "redirect: /main";
        }
    }
    private User createUserAccount(UserRegisterDto accountDto) {
        User registered = userService.registerNewUserAccount(accountDto);
        return registered;
    }
}
