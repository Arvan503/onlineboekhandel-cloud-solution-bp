package sr.qualogy.service;

import jakarta.persistence.EntityManager;
import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.Book;
import sr.qualogy.repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository(JPAConfiguration.getEntityManager());
    }

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public Book createBook(Book book) {
        return bookRepository.createBook(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.updateBook(book);
    }


    public Boolean deleteBook(int id) {
        return bookRepository.deleteBook(id);
    }

}