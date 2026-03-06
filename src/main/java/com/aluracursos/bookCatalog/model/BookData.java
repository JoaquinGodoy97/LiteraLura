package com.aluracursos.bookCatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(@JsonAlias("title") String titulo, @JsonAlias("authors") List<AuthorData> autores, @JsonAlias("languages")  List<String> lenguajes) {
}
