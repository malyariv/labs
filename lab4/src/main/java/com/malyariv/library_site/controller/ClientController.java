package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.RegistrationForm;
import com.malyariv.library_site.entity.Book;
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

import java.sql.Date;
import java.util.Set;

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

    @PreAuthorize("hasRole('ROLE_USER')")
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
        Set<Book> books=userService.getCurrentUser().getClientData().getBookSet();
        model.addAttribute("books", books);
        model.addAttribute("size2",books.size());
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

    @GetMapping("/user/cancel/{id}")
    public String cancelReservation(@PathVariable int id) {
        Book book=bookRepository.findOne(id);
        book.setReserved(false);
        book.setClient(null);
        bookRepository.save(book);
        return "redirect:/user";
    }

    @GetMapping("/user/take/{id}")
    public String takeBook(@PathVariable int id) {
        Book book=bookRepository.findOne(id);
        book.setAvailable(false);
        book.setDeadline(new Date(System.currentTimeMillis()));
        bookRepository.save(book);
        return "redirect:/user";
    }
    @GetMapping("/user/return/{id}")
    public String returnBook(@PathVariable int id) {
        Book book=bookRepository.findOne(id);
        book.setAvailable(true);
        book.setReserved(false);
        book.setClient(null);
        book.setDeadline(null);
        bookRepository.save(book);
        return "redirect:/user";
    }


}
