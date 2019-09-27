package ru.itis.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Book;


import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long id);
}
