package ru.itis.service;

import ru.itis.dto.BookDto;


import java.util.List;

public interface BooksService {

    List<BookDto> getAll();
    void save(BookDto bookDto);
   // void save(List<BookDto> books);
    void update(Long id, BookDto bookDto);
    BookDto getById(Long id);
    void delete(Long id);
    List<BookDto> getBooksByAuthorId(Long authorId);

}
