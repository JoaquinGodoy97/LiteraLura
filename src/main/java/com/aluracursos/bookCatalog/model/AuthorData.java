package com.aluracursos.bookCatalog.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Date;

public record AuthorData(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        @JsonAlias("death_year") Integer fechaDeFallecimiento
) {}
