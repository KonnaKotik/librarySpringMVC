package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.itis.convertes.AuthorConverter;
import ru.itis.dto.AuthorDto;
import ru.itis.forms.AuthorCreateForm;
import ru.itis.forms.AuthorUpdateForm;
import ru.itis.models.Author;
import ru.itis.models.Book;
import ru.itis.repositories.AuthorsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;
    private final AuthorConverter authorConverter;
    private final BooksService booksService;

    @Autowired
    public AuthorsServiceImpl(AuthorsRepository authorsRepository, AuthorConverter authorConverter, BooksService booksService) {
        this.authorsRepository = authorsRepository;
        this.authorConverter = authorConverter;
        this.booksService = booksService;
    }

  /*  @Autowired
    public AuthorsServiceImpl(AuthorsRepository authorsRepository, AuthorConverter authorConverter, BooksService booksService) {
        this.authorsRepository = authorsRepository;
        this.authorConverter = authorConverter;
        this.booksService = booksService;
    }*/

    @Override
    public Author getByName(String name) {
        Optional<Author> authorCandidate = authorsRepository.findByName(name);
        if(authorCandidate.isPresent()) {
            return authorCandidate.get();
        } throw new BadCredentialsException("Not find author");

    }


    @Override
    public void save(AuthorCreateForm authorCreateForm) {
        if(authorsRepository.existsByName(authorCreateForm.getName())) {
            booksService.save(authorCreateForm.getBooks());
            authorsRepository.save(authorConverter.convertFormToModel(authorCreateForm));
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Author> authorCandidate = authorsRepository.findById(id);
        if(authorCandidate.isPresent()) {
            for (Book book: authorCandidate.get().getBooks()) {
                booksService.delete(book.getId());
            }
            authorsRepository.deleteById(id);
        }
    }

    @Override
    public void update(Long id, AuthorUpdateForm authorUpdateForm) {
        Author author = authorConverter.covertUpdateFormToModel(authorUpdateForm);
        author.setId(id);
        authorsRepository.save(author);
    }

    @Override
    public AuthorDto getById(Long id) {
        Optional<Author> authorCandidate = authorsRepository.findById(id);
        if(authorCandidate.isPresent()) {
            return authorConverter.convertModelToDto( authorCandidate.get());
        } throw new BadCredentialsException("Not find author");
    }

    @Override
    public List<AuthorDto> getAll() {
        return authorConverter.convertModelsToDtos(authorsRepository.findAll());
    }
}
