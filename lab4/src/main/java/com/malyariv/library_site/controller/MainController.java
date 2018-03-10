package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.LoginForm;
import com.malyariv.library_site.controller.forms.RegistrationForm;
import com.malyariv.library_site.controller.forms.SearchForm;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.repository.AuthorRepository;
import com.malyariv.library_site.repository.BookRepository;
import com.malyariv.library_site.repository.GenreRepository;
import com.malyariv.library_site.repository.UserRepository;
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

    private List<Book> books=new ArrayList<>();

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("role", userService.getRole());
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registration") RegistrationForm registrationForm){
        User user = new User(registrationForm, new Client(), true);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/all/bookSearch")
    public String bookSearch(){
        return "/all/bookSearch";
    }

    @PostMapping("/all/bookSearch")
    public String bookSearch(@ModelAttribute SearchForm form){
        System.out.println(form);
        Iterable<Book> iterable=null;
        switch (form.getType()){
            case "Title": iterable=bookRepository.findByTitle(form.getSearchField());
                          break;
            case "Author": iterable=authorRepository.findByFullname(form.getSearchField()).getBooks();
                          break;
            default: iterable=genreRepository.findByName(form.getSearchField()).getBooksByGenres();
        }
        books.clear();
        for (Book b:iterable) {
            books.add(b);
        }
        return "redirect:/all/bookList";
    }

    @GetMapping("/all/bookList")
    public String bookList(Model model){
        model.addAttribute("bookList", books);
        model.addAttribute("role", userService.getRole());
        return "/all/bookList";
    }

    @GetMapping("/all/hail")
    public String hail(){
        return "/all/index";
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
