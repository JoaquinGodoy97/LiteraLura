package com.aluracursos.bookCatalog.repository;

import com.aluracursos.bookCatalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNombre(String nombre);
}
