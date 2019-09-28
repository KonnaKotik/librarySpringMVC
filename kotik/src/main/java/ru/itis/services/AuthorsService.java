package ru.itis.services;

import ru.itis.dto.AuthorDto;
import ru.itis.forms.AuthorCreateForm;
import ru.itis.forms.AuthorUpdateForm;
import ru.itis.models.Author;

import java.util.List;

public interface AuthorsService {

    Author getByName(String name);
    List<AuthorDto> getAll();
    AuthorDto getById(Long id);
    void save(AuthorCreateForm authorCreateForm);
    void update(Long id, AuthorUpdateForm authorUpdateForm);
    void delete(Long id);
}
