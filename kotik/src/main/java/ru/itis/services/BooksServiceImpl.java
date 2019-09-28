package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.itis.convertes.BookConverter;
import ru.itis.dto.BookDto;
import ru.itis.forms.BookCreateForm;
import ru.itis.forms.BookUpdateForm;
import ru.itis.models.Book;
import ru.itis.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    private final BookConverter bookConverter;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository, BookConverter bookConverter) {
        this.booksRepository = booksRepository;
        this.bookConverter = bookConverter;
    }


    @Override
    public List<BookDto> getAll() {
        return bookConverter.convertModelsToDtos(booksRepository.findAll());
    }

    @Override
    public void save(BookCreateForm bookCreateForm) {
        if(!booksRepository.existsByTitleAndAuthor_Name(bookCreateForm.getTitle(), bookCreateForm.getAuthorName())) {
            booksRepository.save(bookConverter.convertFormToModel(bookCreateForm));
        } throw new BadCredentialsException("This book is exists");

    }

    @Override
    public void save(List<BookCreateForm> books) {
        for(BookCreateForm book : books) {
            save(book);
        }
    }

    @Override
    public void update(Long id, BookUpdateForm bookUpdateForm) {
        Book book = bookConverter.convertUpdateFormToModel(bookUpdateForm);
        book.setId(id);
        booksRepository.save(book);

    }

    @Override
    public void delete(Long id) {
        Optional<Book> bookCandidate = booksRepository.findById(id);
        if(bookCandidate.isPresent()) {
            booksRepository.deleteById(id);
        }
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> bookCandidate = booksRepository.findById(id);
        if(bookCandidate.isPresent()) {
            return bookConverter.convertModelToDto(bookCandidate.get());
        }throw new BadCredentialsException("Not find book");

    }
}
