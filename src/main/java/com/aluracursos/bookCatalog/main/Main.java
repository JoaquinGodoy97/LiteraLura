package com.aluracursos.bookCatalog.main;

import com.aluracursos.bookCatalog.model.Author;
import com.aluracursos.bookCatalog.model.Book;
import com.aluracursos.bookCatalog.model.BookData;
import com.aluracursos.bookCatalog.repository.AuthorRepository;
import com.aluracursos.bookCatalog.repository.BookRepository;
import com.aluracursos.bookCatalog.service.ConvertData;
import com.aluracursos.bookCatalog.service.ConvertDataImpl;
import com.aluracursos.bookCatalog.service.GetAPI;
import com.aluracursos.bookCatalog.service.GutendexResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Main {
    private final Scanner keyboard = new Scanner(System.in);

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GetAPI getApi = new GetAPI();
    private final ConvertData convertData = new ConvertDataImpl();
    private List<Book> books;
    private List<Book> authors;

    public Main(BookRepository repository, AuthorRepository authorRepository){
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {

        var option = -1;

        while (option != 0) {
            var menu = """
                    \n
                    -------------------------------
                    1 - Look for books
                    2 - Show all the books you saved
                    3 - Look up books by title
                    4 - Look up books if author alived in a specific year
                    5 - Look up books by language
                    6 - Show saved authors
                    
                    0 - Salir
                    ----------------------------------------
                    \n
                    """;

            System.out.println(menu);
            option = keyboard.nextInt();
            keyboard.nextLine();

            switch (option) {
                case 1:
                    searchBooks();
                    break;
                case 2:
                    bringBooks();
                    break;
                case 3:
                    bringBooksByTitle();
                    break;
                case 4:
                    bringBooksIfAuthorAlive();
                    break;
                case 5:
                    bringBooksByLanguage();
                    break;
                case 6:
                    showSavedAuthors();
                    break;
                case 0:
                    System.out.println("Closing application...");
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void showSavedAuthors() {
        repository.findAll()
                .stream()
                .flatMap(book -> book.getAutores().stream())
                .distinct()
                .forEach(System.out::println);
    }

    private void bringBooksByLanguage() {
        System.out.println("Write the language of your books: ");
        String language = keyboard.nextLine();
        repository.findByLenguaje(language).stream().forEach(book -> System.out.println(book.getTitulo()));
    }

    private void bringBooksIfAuthorAlive() {
        System.out.println("Find books in the year the author was alive: ");
        var year = keyboard.nextInt();
        repository.findBooksByAuthorAliveInYear(year).stream().forEach(book -> System.out.println(book.getTitulo()));
    }

    private void bringBooksByTitle() {
        System.out.println("Write the name of the book you want to look for: ");
        String title = keyboard.nextLine();
        repository.findByTituloContainsIgnoreCase(title).stream().forEach(System.out::println);
    }

    private void bringBooks() {
        books = repository.findAll();
        books.stream().forEach(System.out::println);
    }

    private void searchBooks() {

        while (true) {
            BookData bookData = getBookData();

            try {

                List<Author> autores = bookData.autores()
                        .stream()
                        .map(authorData ->
                                authorRepository.findByNombre(authorData.nombre())
                                        .orElseGet(() -> authorRepository.save(new Author(authorData)))
                        )
                        .toList();

                Book book = new Book(
                        bookData.titulo(),
                        autores,
                        bookData.lenguajes()
                );

                repository.save(book);
                System.out.println(bookData);
                break;
            }
            catch(DataIntegrityViolationException e) {
                System.out.println("Book already saved.");
            }
        }

    }

    private BookData getBookData() {

        System.out.println("Write the name of the book: ");
        String inputText = keyboard.nextLine();

        var json = getApi.getAPIDataByUrl("https://gutendex.com/books?search=" + inputText.replace(" ", "%").trim());
        GutendexResponse response = convertData.getData(json, GutendexResponse.class);

        AtomicInteger index = new AtomicInteger(1);
        System.out.println("Select the book you want: ");
        response.getResults().stream().forEach((bookData ) -> {
            System.out.println(index + ". " + bookData.titulo());
            index.getAndIncrement();
        });

        var bookSelected = keyboard.nextInt();
        keyboard.nextLine();

        return response.getResults().get(bookSelected - 1);
    }
}
