package ru.itis.mapper;

import org.mapstruct.*;
import ru.itis.dto.BookDto;
import ru.itis.model.Book;
import ru.itis.repositorie.AuthorsRepository;
import ru.itis.service.AuthorsService;


import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author.id", source = "authorId")
     Book bookDtoToBook(BookDto bookDto);

    @Mappings({@Mapping(target = "authorName", expression = "java(book.getAuthor().getName())"),
            @Mapping(target = "authorId", expression = "java(book.getAuthor().getId())")})
     BookDto bookToBookDto(Book book);

    List<Book> bookDtosToBooks(List<BookDto> bookDtos);

    List<BookDto> booksToBookDtos(List<Book> books);




}
