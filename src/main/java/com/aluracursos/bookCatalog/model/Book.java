package com.aluracursos.bookCatalog.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "lenguaje")
    private List<String> lenguajes;

    @Override
    public String toString() {
        return  "Titulo del libro: '" + titulo + '\'' +
                " \nautores: " + autores +
                ", lenguajes: " + lenguajes;
    }

    public Book(String titulo, List<Author> autores, List<String> lenguajes) {
        this.titulo = titulo;
        this.autores = autores;
        this.lenguajes = lenguajes.stream()
                .map(code -> new Locale(code).getDisplayLanguage(Locale.ENGLISH))
                .toList();
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }

    public Book() {}

}

