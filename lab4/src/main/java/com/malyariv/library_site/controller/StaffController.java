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

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
        addAuthors(book);
        addGenres(book);
        bookRepository.save(book);
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
    @GetMapping("/staff/overdue")
    public String overdue(Model model){
        Iterable<Book> books=bookRepository.findAllByDeadlineBefore(new Date(System.currentTimeMillis()));
        model.addAttribute("flag",books.iterator().hasNext());
        model.addAttribute("books", books);
        return "/staff/overdue";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/showOrders")
    public String showOrders(Model model){
        Iterable<Book> books=bookRepository.findAllByReservedIsTrueAndReadyIsFalse();
        model.addAttribute("flag2",books.iterator().hasNext());
        model.addAttribute("books", books);
        return "/staff/showOrders";
    }

    @PostMapping("/staff/showOrders")
    public String showOrders(@ModelAttribute("checkbox") Checkbox checkbox){
        for (int val:checkbox.isReady()) {
            Book book=bookRepository.findOne(val);
            book.setReady(true);
            bookRepository.save(book);
        }
        return "redirect:/staff";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/arrange")
    public String arrange(Model model){
        Iterable<Book> books=bookRepository.findAllByReservedIsFalseAndReadyIsTrue();
        model.addAttribute("flag", books.iterator().hasNext());
        model.addAttribute("books", books);
        return "/staff/arrangeList";
    }

    @PostMapping("/staff/arrange")
    public String arrange(@ModelAttribute("checkbox") Checkbox checkbox){
        for (int val:checkbox.isReady()) {
            Book book=bookRepository.findOne(val);
            book.setReady(false);
            bookRepository.save(book);
        }
        return "redirect:/staff";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/delete/{id}")
    public String delete(@PathVariable int id){
        Book book=bookRepository.findOne(id);
        bookRepository.delete(book);
        return "/staff";
    }


    private void addGenres(Book book) {
        Set<Genre> checked=new HashSet<>();
        for (Genre g:book.getGenres()) {
            Genre genre=genreRepository.findByName(g.getName());
            if (genre!=null) {
                checked.add(genre);
            }
            else {
                checked.add(g);
            }
        }
        book.setGenres(checked);
    }

    private void addAuthors(Book book) {
        Set<Author> checked=new HashSet<>();
        for (Author author:book.getAuthors()) {
            Author auth=authorRepository.findByFullname(author.getFullname());
            if (auth!=null) {
                checked.add(auth);
            }
            else {
               checked.add(author);
            }
        }
        book.setAuthors(checked);
    }

}
