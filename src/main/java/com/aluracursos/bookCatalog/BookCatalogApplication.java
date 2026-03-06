package com.aluracursos.bookCatalog;
import com.aluracursos.bookCatalog.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookCatalogApplication implements CommandLineRunner {

    private final Main main;

    public BookCatalogApplication(Main main) {
        this.main = main;
    }

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        main.showMenu();

    }
}
