package com.aluracursos.bookCatalog.repository;

import com.aluracursos.bookCatalog.model.Author;
import com.aluracursos.bookCatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTituloContainsIgnoreCase(String nombreLibro);
    List<Book> findDistinctByAutoresNombreContainsIgnoreCase(String nombreAutor);

    @Query("""
    SELECT DISTINCT b
    FROM Book b
    JOIN b.lenguajes l
    WHERE LOWER(l) LIKE LOWER(CONCAT('%', :lenguaje, '%'))
""")
    List<Book> findByLenguaje(@Param("lenguaje") String lenguaje);

    @Query("""
       SELECT b
       FROM Book b
       JOIN b.autores a
       WHERE a.fechaDeNacimiento <= :year
       AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :year)
       """)
    List<Book> findBooksByAuthorAliveInYear(@Param("year") Integer year);
}
