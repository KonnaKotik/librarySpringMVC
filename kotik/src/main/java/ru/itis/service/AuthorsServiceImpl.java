package ru.itis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mapper.AuthorMapper;
import ru.itis.dto.AuthorDto;
import ru.itis.model.Author;
import ru.itis.model.Book;
import ru.itis.repositorie.AuthorsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("AuthorService")
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;
    @Autowired
    private AuthorMapper authorConverter;


    private final BooksService booksService;

    @Autowired
    public AuthorsServiceImpl(AuthorsRepository authorsRepository, BooksService booksService) {
        this.authorsRepository = authorsRepository;
        this.booksService = booksService;
    }


    @Override
    public void save(AuthorDto authorDto) {
        if (authorsRepository.existsByName(authorDto.getName())) {
            // booksService.save(authorDto.getBooks());
            authorsRepository.save(authorConverter.authorDtoToAuthor(authorDto));
            System.out.println(authorConverter.authorDtoToAuthor(authorDto).getName());
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Author> authorCandidate = authorsRepository.findById(id);
        if (authorCandidate.isPresent()) {
            for (Book book : authorCandidate.get().getBooks()) {
                booksService.delete(book.getId());
            }
            authorsRepository.delete(authorCandidate.get());
        }
    }

    @Override
    public void update(Long id, AuthorDto authorDto) {
        Author author = authorConverter.authorDtoToAuthor(authorDto);
        authorsRepository.save(author);
    }

    @Override
    public AuthorDto getById(Long id) {
        Author author = authorsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return authorConverter.authorToAuthorDto(author);

    }

    @Override
    public List<AuthorDto> getAll() {
        return authorConverter.authorsToAuthorDtos(authorsRepository.findAll());
    }
}
