package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.RegistrationForm;
import com.malyariv.library_site.entity.Client;
import com.malyariv.library_site.entity.Employee;
import com.malyariv.library_site.entity.User;
import com.malyariv.library_site.repository.BookRepository;
import com.malyariv.library_site.repository.EmployeeRepository;
import com.malyariv.library_site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private String msg;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String home(){
        return "/admin/index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/registration")
    public String registrationEmployee(Model model){
        model.addAttribute("error", msg);
        return "/admin/registration";
    }

    @PostMapping("/admin/registration")
    public String registrationEmployee(@ModelAttribute("registration") RegistrationForm registrationForm){
        if (checkRegistrationForm(registrationForm)) {
            User user = new User(registrationForm, new Employee(), false);
            userRepository.save(user);
            return "redirect:/admin";
        } else {
            return "redirect:/admin/registration";
        }
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/showEmployees")
    public String registration(Model model){
        model.addAttribute("emps", userRepository.findByRole("ROLE_STAFF"));
        return "/admin/showEmployees";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable int id){
        userRepository.delete(id);
        return "/admin";
    }

    private boolean checkRegistrationForm(RegistrationForm registrationForm) {
        msg=null;
        User user=userRepository.findByUsername(registrationForm.getUsername());
        if (user!=null) {
            msg="This username is already exist in database!";
            return false;
        }
        Employee employee=employeeRepository.findByEmail(registrationForm.getEmail());
        if (employee!=null) {
            msg="This email is already exist in database!";
            return false;
        }
        return true;
    }
}