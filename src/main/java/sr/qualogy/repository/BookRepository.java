package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sr.qualogy.entity.Book;

import java.util.List;

public class BookRepository {

    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book createBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();

        return book;
    }

    public List<Book> getBooks() {
        String sql = "select b from Book b";
        TypedQuery<Book> typedQuery = entityManager.createQuery(sql, Book.class);
        List<Book> books = typedQuery.getResultList();

        return books;
    }

    public Book updateBook(Book book) {
        try {
            entityManager.getTransaction().begin();
            book = entityManager.merge(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        return book;
    }

    public Boolean deleteBook(int id) {
        Boolean result = false;
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
                result = true;
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return result;
    }
}