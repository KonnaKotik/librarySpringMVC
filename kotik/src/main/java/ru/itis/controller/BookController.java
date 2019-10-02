package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.BookDto;
import ru.itis.service.BooksService;

import javax.annotation.Resource;

@Controller
@Resource(name = "BookService")
public class BookController {

    private final BooksService booksService;

    @Autowired
    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", booksService.getAll());
        return "books";
    }

    @GetMapping("/books/{id}")
    public ModelAndView getBookPage(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("book", booksService.getById(id));
        model.setViewName("book");
        return model;
    }
    @GetMapping("/authors/{id}/books")
    public ModelAndView getAuthorBook(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("books",booksService.getBooksByAuthorId(id) );
        model.setViewName("authorBook");
        return model;
    }

    @PostMapping("/books/add")
    public String addNewBook(@ModelAttribute BookDto bookCreateForm) {
        booksService.save(bookCreateForm);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String addBookPAge(){
        return "bookAdd";
    }

    @GetMapping("/books/edit/{id}")
    public String getEditBookPage(@PathVariable Long id, Model model) {
        model.addAttribute("book", booksService.getById(id));
        return "bookEdit";
    }

    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id,  @ModelAttribute BookDto bookUpdateForm) {
        BookDto newBookDto = booksService.getById(id);
        newBookDto.setDescription(bookUpdateForm.getDescription());
        newBookDto.setTitle(bookUpdateForm.getDescription());
        booksService.update(id, newBookDto);
        return "redirect:/books";
    }

    @PostMapping("/books/edit/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
