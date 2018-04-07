package com.malyariv.library_site.controllers;

import com.malyariv.library_site.controllers.forms.SearchForm;
import com.malyariv.library_site.entity.Book;
import com.malyariv.library_site.repository.AuthorRepository;
import com.malyariv.library_site.repository.BookRepository;
import com.malyariv.library_site.repository.GenreRepository;
import com.malyariv.library_site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Iterable<Book> books;

    @GetMapping("/all/bookSearch")
    public String bookSearch(){
        return "/all/bookSearch";
    }

    @PostMapping("/all/bookSearch")
    public String bookSearch(@ModelAttribute SearchForm form){
        System.out.println(form);
        Iterable<Book> iterable=null;
        try {
            switch (form.getType()) {
                case "Title":
                    iterable = bookRepository.findByTitleContains(form.getSearchField());
                    break;
                case "Author":
                    iterable = authorRepository.findByFullnameContains(form.getSearchField()).getBooks();
                    break;
                case "Genre":
                    iterable = genreRepository.findByName(form.getSearchField()).getBooksByGenres();
                    break;
                default:
                    iterable=bookRepository.findBy(form.getSearchField());
            }
        } catch (Exception e){
            return "redirect:/all/bookList";
        }
        books=iterable;
        return "redirect:/all/bookList";
    }

    @GetMapping("/all/bookList")
    public String bookList(Model model){
        model.addAttribute("flag",books.iterator().hasNext());
        model.addAttribute("bookList", books);
        model.addAttribute("role", userService.getRole());
        return "/all/bookList";
    }

    @GetMapping("/all/showBook/{id}")
    public String showBook(@PathVariable int id, Model model){
        model.addAttribute("book",bookRepository.findOne(id));
        return "/all/showBook";
    }

}
