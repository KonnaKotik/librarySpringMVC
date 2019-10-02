package ru.itis.service;

import ru.itis.dto.AuthorDto;

import ru.itis.model.Author;

import java.util.List;

public interface AuthorsService {

    List<AuthorDto> getAll();
    AuthorDto getById(Long id);
    void save(AuthorDto authorDto);
    void update(Long id, AuthorDto authorDto);
    void delete(Long id);
}
