package ru.itis.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Book;

import java.util.List;
import java.util.Optional;



public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    boolean existsByTitleAndAuthor_Name(String title, String authorName);
    List<Book> findAllByAuthor_Id(Long authorId);
}
