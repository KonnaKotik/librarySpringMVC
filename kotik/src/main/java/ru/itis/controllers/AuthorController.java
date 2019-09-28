package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.forms.AuthorCreateForm;
import ru.itis.forms.AuthorUpdateForm;
import ru.itis.services.AuthorsService;

@Controller
public class AuthorController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("authors")
    public ModelAndView getAllAuthors() {
        ModelAndView model = new ModelAndView();
        model.addObject("authors", authorsService.getAll());
        model.setViewName("authors");
        return model;
    }

    @GetMapping("/authors/{id}")
    public ModelAndView getAuthor(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("author", authorsService.getById(id));
        model.setViewName("author");
        return model;
    }

    @PostMapping("/authors/add")
    public String addNewAuthor(@RequestBody AuthorCreateForm authorCreateForm) {
        authorsService.save(authorCreateForm);
        return "redirect:/authors";
    }

    @PostMapping("/authors/{id}/edit")
    public String updateAuthors(@PathVariable Long id, AuthorUpdateForm authorUpdateForm) {
        authorsService.update(id, authorUpdateForm);
        return "redirect:/authors";
    }

    @PostMapping("/authors/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorsService.delete(id);
        return "redirect:/authors";
    }
}
