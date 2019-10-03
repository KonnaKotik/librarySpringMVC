package ru.itis.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.AuthorDto;
import ru.itis.service.AuthorsService;

import javax.annotation.Resource;

@Controller
@Resource(name = "AuthorService")
public class AuthorController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authorsList",authorsService.getAll());
        return "authors_page";
    }

    @GetMapping("/authors/{id}")
    public ModelAndView getAuthor(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("author", authorsService.getById(id));
        model.setViewName("author");
        return model;
    }


    @GetMapping("/authors/add")
    public String addAuthorPage() {
        return "authorAdd";
    }

    @PostMapping("/authors/add")
    public String addNewAuthor(@ModelAttribute("authorDto") AuthorDto authorCreateForm) {
        System.out.println(authorCreateForm.getDescription());
        System.out.println(authorCreateForm.getName());
        System.out.println(authorCreateForm.getUrlImg());
        authorsService.save(authorCreateForm);
        return "redirect:/authors";
    }

    @PostMapping("/authors/edit/{id}")
    public String updateAuthors(@PathVariable Long id,@ModelAttribute("authorDto") AuthorDto authorUpdateForm) {
        AuthorDto newAuthorDto = authorsService.getById(id);
        newAuthorDto.setName(authorUpdateForm.getName());
        newAuthorDto.setDescription(authorUpdateForm.getDescription());
        authorsService.update(id, authorUpdateForm);
        return "authorEdit";
    }

    @GetMapping("authors/edit/{id}")
    public ModelAndView getAuthorEditPage(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("author", authorsService.getById(id));
        model.setViewName("authorEdit");
        return model;
    }

    @PostMapping("/authors/edit/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorsService.delete(id);
        return "redirect:/authors";
    }
}
