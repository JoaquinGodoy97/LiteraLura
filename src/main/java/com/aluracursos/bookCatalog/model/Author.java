package com.aluracursos.bookCatalog.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    public Author(AuthorData data) {
        this.nombre = data.nombre();
        this.fechaDeNacimiento = data.fechaDeNacimiento();
        this.fechaDeFallecimiento = data.fechaDeFallecimiento();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  "nombre: '" + nombre + '\'' +
                ", fecha de nacimiento: " + fechaDeNacimiento +
                ", fallecimiento: " + fechaDeFallecimiento +
                '.';
    }

    public Author() {}
}
