package ru.itis.convertes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.BookDto;
import ru.itis.forms.BookCreateForm;
import ru.itis.forms.BookUpdateForm;
import ru.itis.models.Author;
import ru.itis.models.Book;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.services.AuthorsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookConverter {

    private final AuthorsService authorsService;

    @Autowired
    public BookConverter(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }


    public BookDto convertModelToDto(Book book) {
        return BookDto.builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .authorName(book.getAuthor().getName())
                .build();
    }

    private Stream<BookDto> modelsToDtos(List<Book> books) {
        return books.stream().map(this::convertModelToDto);
    }

    public List<BookDto> convertModelsToDtos(List<Book> books) {
        return modelsToDtos(books).collect(Collectors.toList());
    }

    public Book convertFormToModel(BookCreateForm bookCreateForm) {
        return Book.builder()
                .author(authorsService.getByName(bookCreateForm.getAuthorName()))
                .description(bookCreateForm.getDescription())
                .title(bookCreateForm.getTitle())
                .build();
    }

    private Stream<Book> formsToModels(List<BookCreateForm> books) {
        return books.stream().map(this::convertFormToModel);
    }

    public List<Book> convertFormsToModels(List<BookCreateForm> books) {
        return formsToModels(books).collect(Collectors.toList());
    }

    public Book convertUpdateFormToModel(BookUpdateForm bookUpdateForm) {
        return Book.builder()
                .title(bookUpdateForm.getTitle())
                .description(bookUpdateForm.getDescription())
                .author(authorsService.getByName(bookUpdateForm.getAuthorName()))
                .build();
    }
    private Stream<Book> updateFormsToModels(List<BookUpdateForm> books) {
        return books.stream().map(this::convertUpdateFormToModel);
    }

    public List<Book> convertUpdateFormsToModels(List<BookUpdateForm> books) {
        return updateFormsToModels(books).collect(Collectors.toList());
    }



}
