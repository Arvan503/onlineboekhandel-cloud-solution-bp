package sr.qualogy.service;

import sr.qualogy.entity.Book;

import java.util.List;

public interface BookServiceInterface {


    List<Book> getBooks();

     Book createBook(Book book);

     Book updateBook(Book book);

     Boolean deleteBook(int id);

     Book updateBookById(Long bookId, Book updatedBook);

     Book getBookById(Long bookId);
}
