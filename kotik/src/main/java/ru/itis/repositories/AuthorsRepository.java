package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
    List<Author> findAll();
    Optional<Author> findById(Long id);
    boolean existsByName(String name);
    void deleteById(Long id);
}
