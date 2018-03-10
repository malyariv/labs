package com.malyariv.library_site.controller;

import com.malyariv.library_site.controller.forms.Checkbox;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.controller.forms.BookForm;
import com.malyariv.library_site.repository.*;
import com.malyariv.library_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class StaffController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff")
    public String home(Model model){
        model.addAttribute("employee", userService.getCurrentUser().getEmployeeData());
        return "/staff/index";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/addBook")
    public String addBook(){
        return "/staff/addBook";
    }

    @PostMapping("/staff/addBook")
    public String addBook (@ModelAttribute("book") BookForm bookForm){
        Location location=new Location(bookForm);
        Book book = new Book(bookForm);
        book.setBookLocation(location);
        System.out.println(book);
        addAuthor(book);
        addGenre(bookForm);
        return "redirect:/staff";
    }




    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/showClients")
    public String showClients(Model model){
        model.addAttribute("users", userRepository.findByRole("ROLE_USER"));
        return "/staff/showClients";
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
    @GetMapping("/staff/showBooks")
    public String showBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "/staff/showBooks";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/showOrders")
    public String showOrders(Model model){
        Iterable<Book> books=bookRepository.findAllByReservedIsTrue();
        model.addAttribute("books", books);
        return "/staff/showOrders";
    }

    @PostMapping("/staff/showOrders")
    public String showOrders(@ModelAttribute("checkbox") Checkbox checkbox, Model model){

//        if (checkbox.isReady()) {
//
//            return "redirect:/staff/showOrders";
//        }
        return "redirect:/staff";
    }


    private void addGenre(BookForm bookForm) {
        Book book = null;
        for (Book book1 : bookRepository.findByTitle(bookForm.getTitle())) {
            if (book == null || book1.getId() > book.getId()) {
                book = book1;
            }
        }

        for (Genre genre : bookForm.getSetOfGenres()) {
            Genre g = genreRepository.findByName(genre.getName());
            if (g != null) {
                g.getBooksByGenres().add(book);
                genreRepository.save(g);
            } else {
                g = genre;
                HashSet<Book> books = new HashSet<>();
                books.add(book);
                g.setBooksByGenres(books);
                genreRepository.save(g);
            }
        }
    }

    private void addAuthor(Book book) {
        for (Author author:book.getAuthors()) {
            Author auth=authorRepository.findByFullname(author.getFullname());
            if (auth!=null) {
                auth.getBooks().add(book);
                authorRepository.save(auth);
            }
            else {
                HashSet<Book> books = new HashSet<>();
                books.add(book);
                author.setBooks(books);
                authorRepository.save(author);
            }
        }
    }

}
