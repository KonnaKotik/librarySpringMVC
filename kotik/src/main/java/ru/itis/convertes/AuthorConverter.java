package ru.itis.convertes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dto.AuthorDto;
import ru.itis.forms.AuthorCreateForm;
import ru.itis.forms.AuthorUpdateForm;
import ru.itis.models.Author;
import sun.net.www.protocol.http.AuthenticationHeader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthorConverter {

    private final BookConverter bookConverter;

    @Autowired
    public AuthorConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }


    public AuthorDto convertModelToDto(Author author) {
        return AuthorDto.builder()
                .name(author.getName())
                .books(bookConverter.convertModelsToDtos(author.getBooks()))
                .build();
    }

    private Stream<AuthorDto> modelsToDtos(List<Author> authors) {
        return authors.stream().map(this::convertModelToDto);
    }

    public List<AuthorDto> convertModelsToDtos(List<Author> authors) {
        return modelsToDtos(authors).collect(Collectors.toList());
    }

    public Author convertFormToModel(AuthorCreateForm authorCreateForm) {
        return Author.builder()
                .name(authorCreateForm.getName())
                .books(bookConverter.convertFormsToModels(authorCreateForm.getBooks()))
                .build();
    }

    public Author covertUpdateFormToModel(AuthorUpdateForm authorUpdateForm) {
        return Author.builder()
                .name(authorUpdateForm.getName())
                .books(bookConverter.convertUpdateFormsToModels(authorUpdateForm.getBooks()))
                .build();
    }
}
