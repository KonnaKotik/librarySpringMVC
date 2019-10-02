package ru.itis.service;


import org.mapstruct.AfterMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mapper.BookMapper;
import ru.itis.dto.BookDto;
import ru.itis.model.Book;
import ru.itis.repositorie.BooksRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service("BookService")
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    private BookMapper bookConverter;


    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    @Override
    public List<BookDto> getAll() {
        return bookConverter.booksToBookDtos(booksRepository.findAll());
    }

    @Override
    public void save(BookDto bookDto) {
        if(!booksRepository.existsByTitleAndAuthor_Name(bookDto.getTitle(), bookDto.getAuthorName())) {
            booksRepository.save(bookConverter.bookDtoToBook(bookDto));
        } throw new EntityExistsException("This book is exists");

    }

    /*@Override
    public void save(List<BookDto> books) {
        for(BookCreateForm book : books) {
            save(book);
        }
    }*/

    @Override
    public void update(Long id, BookDto bookDto) {
        Book book = bookConverter.bookDtoToBook(bookDto);
        booksRepository.save(book);

    }

    @Override
    public List<BookDto> getBooksByAuthorId(Long authorId) {
        return bookConverter.booksToBookDtos(booksRepository.findAllByAuthor_Id(authorId));
    }

    @Override
    public void delete(Long id) {
        Book book = booksRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        booksRepository.delete(book);
    }

    @Override
    public BookDto getById(Long id) {
        Book book = booksRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookConverter.bookToBookDto(book);

    }
}
