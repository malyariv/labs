package com.malyariv.library_site.controllers.admin;

import com.malyariv.library_site.controllers.forms.RegistrationForm;
import com.malyariv.library_site.controllers.forms.SearchForm;
import com.malyariv.library_site.entity.Employee;
import com.malyariv.library_site.entity.Role;
import com.malyariv.library_site.entity.RolesEnum;
import com.malyariv.library_site.entity.User;
import com.malyariv.library_site.repository.EmployeeRepository;
import com.malyariv.library_site.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;

    private Iterable<User> emps;

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
            Role role=roleRepository.findByName(RolesEnum.ROLE_STAFF.name());
            if (role!=null) {
                user.setRole(role);
            }
            else {
                user.setRole(new Role(RolesEnum.ROLE_STAFF.name()));
            }
            userRepository.save(user);
            return "redirect:/admin";
        } else {
            return "redirect:/admin/registration";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/showEmployees")
    public String showEmployees(Model model){
        if (emps==null) {
            model.addAttribute("emps", userRepository.findByRoleName("ROLE_STAFF"));
        }
        else {
            model.addAttribute("emps", emps);
            emps=null;
        }
        return "/admin/showEmployees";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/employeeSearch")
    public String employeeSearch(){
        return "/admin/employeeSearch";
    }

    @PostMapping("/admin/employeeSearch")
    public String employeeSearch(@ModelAttribute SearchForm form){
        System.out.println(form);
        Iterable<User> iterable=null;
        try {
            switch (form.getType()) {
                case "Name":
                    iterable = userRepository.findByEmployeeDataFirstnameContains(form.getSearchField());
                    break;
                case "Surname":
                    iterable = userRepository.findByEmployeeDataLastnameContains(form.getSearchField());
                    break;
                case "Email":
                    iterable = userRepository.findByEmployeeDataEmailContains(form.getSearchField());
                    break;
                default:
                    iterable = userRepository.findByEmployeeData(form.getSearchField());
            }
        } catch (Exception e){
            return "redirect:/admin";
        }
        emps=iterable;
        return "redirect:/admin/showEmployees";
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
