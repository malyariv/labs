package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.LoginForm;
import com.malyariv.library_site.controller.forms.RegistrationForm;
import com.malyariv.library_site.controller.forms.SearchForm;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.repository.*;
import com.malyariv.library_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@EnableGlobalMethodSecurity
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ClientRepository clientRepository;

    private List<Book> books=new ArrayList<>();
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
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");
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
            userRepository.save(user);
            return "redirect:/";
        } else {
            return "redirect:/registration";
        }
    }



    @GetMapping("/all/bookSearch")
    public String bookSearch(){
        return "/all/bookSearch";
    }

    @PostMapping("/all/bookSearch")
    public String bookSearch(@ModelAttribute SearchForm form){
        System.out.println(form);
        books.clear();
        Iterable<Book> iterable=null;
        try {
            switch (form.getType()) {
                case "Title":
                    iterable = bookRepository.findByTitle(form.getSearchField());
                    break;
                case "Author":
                    iterable = authorRepository.findByFullname(form.getSearchField()).getBooks();
                    break;
                default:
                    iterable = genreRepository.findByName(form.getSearchField()).getBooksByGenres();
            }
        } catch (Exception e){
            return "redirect:/all/bookList";
        }

        for (Book b:iterable) {
            if (!b.isReserved()&&!b.isReady()&&b.isAvailable()) {
                books.add(b);
            }
        }
        return "redirect:/all/bookList";
    }

    @GetMapping("/all/bookList")
    public String bookList(Model model){
        model.addAttribute("size",books.size());
        model.addAttribute("bookList", books);
        model.addAttribute("role", userService.getRole());
        return "/all/bookList";
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

//
//    @GetMapping("/admin")
//    public String homeAdmin(){
//        return "admin/index";
//    }
//
//    @GetMapping("/user")
//    public String homeUser(){
//        return "user/index";
//    }



}
