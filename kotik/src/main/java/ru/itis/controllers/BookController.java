package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.forms.BookCreateForm;
import ru.itis.forms.BookUpdateForm;
import ru.itis.services.BooksService;

@Controller
public class BookController {

    private final BooksService booksService;

    @Autowired
    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public ModelAndView getAllBooks() {
        ModelAndView model = new ModelAndView();
        model.addObject("books", booksService.getAll());
        model.setViewName("books");
        return model;
    }

    @GetMapping("/books/{id}")
    public ModelAndView getBookPage(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("book", booksService.getById(id));
        model.setViewName("book");
        return model;
    }

    @PostMapping("/books/add")
    public String addNewBook(@RequestBody BookCreateForm bookCreateForm) {
        booksService.save(bookCreateForm);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/edit")
    public String editBook(@PathVariable Long id,  @RequestBody BookUpdateForm bookUpdateForm) {
        booksService.update(id, bookUpdateForm);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
