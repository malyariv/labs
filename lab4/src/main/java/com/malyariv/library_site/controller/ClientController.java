package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.RegistrationForm;
import com.malyariv.library_site.entity.Book;
import com.malyariv.library_site.entity.Client;
import com.malyariv.library_site.entity.User;
import com.malyariv.library_site.repository.BookRepository;
import com.malyariv.library_site.repository.ClientRepository;
import com.malyariv.library_site.repository.UserRepository;
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
public class ClientController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientRepository clientRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public String home(Model model){
        model.addAttribute("client", userService.getCurrentUser().getClientData());
        return "/user/index";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/editProfile")
    public String editProfile(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "/user/editProfile";
    }

    @PostMapping("/user/editProfile")
    public String editProfile(@ModelAttribute("update")RegistrationForm registrationForm){
        User user=userService.getCurrentUser();
        if (user.update(registrationForm)) {
            userRepository.save(user);
        }
        return "redirect:/user";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/showBooks")
    public String showBooks(Model model){
        model.addAttribute("books", userService.getCurrentUser().getClientData().getBookSet());
        return "/user/showBooks";
    }

    @GetMapping("/user/reserve/{id}")
    public String reserve(@PathVariable int id) {
        Book book=bookRepository.findOne(id);
        book.setReserved(true);
        book.setClient(userService.getCurrentUser().getClientData());
        bookRepository.save(book);
        return "redirect:/user";
    }

}
