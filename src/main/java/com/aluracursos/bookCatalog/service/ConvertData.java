package com.aluracursos.bookCatalog.service;

public interface ConvertData {
    <T> T getData(String json, Class<T> tClass);
}
