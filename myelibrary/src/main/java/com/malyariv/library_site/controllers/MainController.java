package com.malyariv.library_site.controllers;

import com.malyariv.library_site.controllers.forms.RegistrationForm;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.repository.*;
import com.malyariv.library_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableGlobalMethodSecurity
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RoleRepository roleRepository;

    private String msg;

    @GetMapping("/")
    public String home(Model model){
        String role=userService.getRole();
        switch (role){
            case "ROLE_USER": return "/user";
            case "ROLE_ADMIN": return "/admin";
            case "ROLE_STAFF": return "/staff";
        }
        model.addAttribute("role", role);
        return "/index";
    }

    @GetMapping("/login")
    public String login(Model model, String error){
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid. Maybe, your account is disable.");
        }
        model.addAttribute("top", bookRepository.findTop(3));
        return "/login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("error", msg);
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registration") RegistrationForm registrationForm){
        if (checkRegistrationForm(registrationForm)) {
            User user = new User(registrationForm, new Client(), true);
            Role role=roleRepository.findByName(RolesEnum.ROLE_USER.name());
            if (role!=null) {
                user.setRole(role);
            }
            else {
                user.setRole(new Role(RolesEnum.ROLE_USER.name()));
            }
            userRepository.save(user);
            return "redirect:/";
        } else {
            return "redirect:/registration";
        }
    }

    private boolean checkRegistrationForm(RegistrationForm registrationForm) {
        msg=null;
        User user=userRepository.findByUsername(registrationForm.getUsername());
        if (user!=null) {
            msg="This username is already exist in database!";
            return false;
        }
        Client client=clientRepository.findClientByEmail(registrationForm.getEmail());
        if (client!=null) {
            msg="This email is already exist in database!";
            return false;
        }
        return true;
    }




}
