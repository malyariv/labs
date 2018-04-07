package com.malyariv.library_site.controllers.staff;

import com.malyariv.library_site.controllers.forms.BookForm;
import com.malyariv.library_site.controllers.forms.Checkbox;
import com.malyariv.library_site.entity.*;
import com.malyariv.library_site.repository.AuthorRepository;
import com.malyariv.library_site.repository.BookRepository;
import com.malyariv.library_site.repository.ClientRepository;
import com.malyariv.library_site.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class StaffBookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/books")
    public String books(){
        return "/staff/books";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/addBook")
    public String addBook(){
        return "/staff/addBook";
    }

    @PostMapping(value = "/staff/addBook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addBook (@RequestParam("file") MultipartFile file, @ModelAttribute("book") BookForm bookForm){
        String filename=file.getOriginalFilename();
        if (filename.equals("")) {
            filename="default.jpg";
        }
        else {
            File f = new File("/home/ivan/NetCracker/library_site/src/main/resources/static/covers/" + file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(file.getBytes());
            } catch (IOException e) {                }
        }

        Location location=new Location(bookForm);
        Book book = new Book(bookForm);
        book.setCover("/covers/"+filename);
        book.setBookLocation(location);
        System.out.println(book);
        addAuthors(book);
        addGenres(book);
        bookRepository.save(book);
        return "redirect:/staff/books";
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
        return "redirect:/staff/books";
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
        return "redirect:/staff/books";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/issue")
    public String issue(Model model){
        Iterable<Book> books=bookRepository.findAllByReservedIsTrueAndReadyIsTrueAndAvailableIsTrue();
        model.addAttribute("flag", books.iterator().hasNext());
        model.addAttribute("books", books);
        return "/staff/issue";
    }

    @PostMapping("/staff/issue")
    public String issue(@ModelAttribute("checkbox") Checkbox checkbox){
        for (int val:checkbox.isReady()) {
            Book book=bookRepository.findOne(val);
            book.setAvailable(false);
            book.setDeadline(new Date(System.currentTimeMillis()));
            bookRepository.save(book);
        }
        return "redirect:/staff/books";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/return")
    public String returned(Model model){
        Iterable<Book> books=bookRepository.findAllByAvailableIsFalse();
        model.addAttribute("flag", books.iterator().hasNext());
        model.addAttribute("books", books);
        return "/staff/return";
    }

    @PostMapping("/staff/return")
    public String returned(@ModelAttribute("checkbox") Checkbox checkbox){
        for (int val:checkbox.isReady()) {
            Book book=bookRepository.findOne(val);
            book.setAvailable(true);
            book.setReserved(false);
            book.setClient(null);
            book.setDeadline(null);
            bookRepository.save(book);
        }
        return "redirect:/staff/books";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/staff/delete/{id}")
    public String delete(@PathVariable int id){
        Book book=bookRepository.findOne(id);
        File file=new File("/home/ivan/NetCracker/library_site/src/main/resources/static"+book.getCover());
        file.delete();
        bookRepository.delete(book);
        return "/staff/books";
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @GetMapping("/stats/books")
    public String statsBooks(Model model){
        Map<String, Integer> counter=new HashMap<>();
        Map<String, Integer> counterForPlot=new HashMap<>();
        Map<String, Double> counterFractions=new HashMap<>();
        Map<String, Integer> demand=new HashMap<>();
        Map<String, Integer> demandForPlot=new HashMap<>();
        Map<String, Double> demandFractions=new HashMap<>();
        Iterator<Genre> iterator=genreRepository.findAll().iterator();
        List<Genre> list = new ArrayList<>();
        int counterSum=0;
        int demandSum=0;
        while (iterator.hasNext()) {
            Genre g=iterator.next();
            list.add(g);
            counterSum+=g.getCounter();
            demandSum+=g.getDemand();
        }
        for (Genre g : list) {
            counter.put(g.getName(),g.getCounter());
            counterForPlot.put(g.getName(),(int)Math.round(g.getCounter()*100.0/counterSum));
            counterFractions.put(g.getName(),g.getCounter()*100.0/counterSum);

            demand.put(g.getName(),g.getDemand());
            demandForPlot.put(g.getName(),(int)Math.round(g.getDemand()*100.0/demandSum));
            demandFractions.put(g.getName(),g.getDemand()*100.0/demandSum);
        }
        model.addAttribute("quantityCounter", counter);
        model.addAttribute("plotCounter", counterForPlot);
        model.addAttribute("statsCounter", counterFractions);
        model.addAttribute("quantityDemand", demand);
        model.addAttribute("plotDemand", demandForPlot);
        model.addAttribute("statsDemand", demandFractions);
        model.addAttribute("books", bookRepository.findTop(5));
        return "/stats/books";
    }


    private void addGenres(Book book) {
        Set<Genre> checked=new HashSet<>();
        for (Genre g:book.getGenres()) {
            Genre genre=genreRepository.findByName(g.getName());
            if (genre!=null) {
                genre.setCounter(genre.getCounter()+1);
                genreRepository.save(genre);
                checked.add(genre);
            }
            else {
                g.setCounter(1);
                checked.add(g);
            }
        }
        book.setGenres(checked);
    }

    private void addAuthors(Book book) {
        Set<Author> checked=new HashSet<>();
        for (Author author:book.getAuthors()) {
            Author auth=authorRepository.findByFullnameContains(author.getFullname());
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
