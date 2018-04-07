package com.malyariv.library_site.controllers.staff;

import com.malyariv.library_site.controllers.forms.SearchForm;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.repository.*;
import com.malyariv.library_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class StaffController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ClientRepository clientRepository;

    private Iterable<User> clients;

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff")
    public String home(Model model){
        model.addAttribute("employee", userService.getCurrentUser().getEmployeeData());
        return "/staff/index";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/clients")
    public String clients(){
        return "/staff/clients";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/showClients")
    public String showClients(Model model){
        if (clients==null) {
            model.addAttribute("users", userRepository.findByRoleName("ROLE_USER"));
        }
        else {
            model.addAttribute("users", clients);
            clients=null;
        }
        return "/staff/showClients";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/clientSearch")
    public String employeeSearch(){
        return "/staff/clientSearch";
    }

    @PostMapping("/staff/clientSearch")
    public String employeeSearch(@ModelAttribute SearchForm form){
        System.out.println(form);
        Iterable<User> iterable=null;
        try {
            switch (form.getType()) {
                case "Name":
                    iterable = userRepository.findByClientDataFirstnameContains(form.getSearchField());
                    break;
                case "Surname":
                    iterable = userRepository.findByClientDataLastnameContains(form.getSearchField());
                    break;
                case "Email":
                    iterable = userRepository.findByClientDataEmailContains(form.getSearchField());
                    break;
                default:
                    iterable = userRepository.findByClientData(form.getSearchField());
            }
        } catch (Exception e){
            return "redirect:/staff";
        }
        clients=iterable;
        return "redirect:/staff/showClients";
    }


    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/disable/{id}")
    public String disable(@PathVariable("id") int id){
        User user=userRepository.findOne(id);
        user.setEnabled(false);
        userRepository.save(user);
        return "redirect:/staff";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/activate/{id}")
    public String activate(@PathVariable("id") int id){
        User user=userRepository.findOne(id);
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/staff";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/stats/clients")
    public String statsBooks(Model model){
        Client client=clientRepository.findTop(1).iterator().next();
        model.addAttribute("client", client);
        return "/stats/clients";
    }

}
