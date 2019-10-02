package ru.itis.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Author;
import ru.itis.model.Book;


import java.util.List;
import java.util.Optional;




public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
    List<Author> findAll();
    Optional<Author> findById(Long id);
    boolean existsByName(String name);
    void deleteById(Long id);
}
