package com.aluracursos.bookCatalog.service;

import com.aluracursos.bookCatalog.model.Book;
import com.aluracursos.bookCatalog.model.BookData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<BookData> results;

    public List<BookData> getResults() {
        return results;
    }

    public void setResults(List<BookData> results) {
        this.results = results;
    }
}
