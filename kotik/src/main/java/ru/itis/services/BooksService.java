package ru.itis.services;

import ru.itis.dto.BookDto;
import ru.itis.forms.BookCreateForm;
import ru.itis.forms.BookUpdateForm;

import java.util.List;

public interface BooksService {

    List<BookDto> getAll();
    void save(BookCreateForm bookCreateForm);
    void save(List<BookCreateForm> books);
    void update(Long id, BookUpdateForm bookUpdateForm);
    BookDto getById(Long id);
    void delete(Long id);
}
