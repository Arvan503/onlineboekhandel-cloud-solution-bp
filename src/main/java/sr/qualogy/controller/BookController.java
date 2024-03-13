package sr.qualogy.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.Book;
import sr.qualogy.service.BookService;

import java.util.List;

@Path("/book")
public class BookController {

    private final BookService bookService;

    public BookController() {
        this.bookService = new BookService();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld(){
        return "Hello World";
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        book = bookService.createBook(book);
        return Response.ok(book, MediaType.APPLICATION_JSON_TYPE).build();
    }

//    @Path("/update")
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBook(Book book) {
//        book = bookService.updateBook(book);
//
//        if (book != null) {
//            return Response.ok(book, MediaType.APPLICATION_JSON_TYPE).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    @DELETE
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") int bookId) {
        boolean deleted = bookService.deleteBook(bookId);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        Book updatedBook = bookService.updateBook(book);
        return Response.ok(updatedBook, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookById(@PathParam("id") Long bookId, Book updatedBook) {
        Book result = bookService.updateBookById(bookId, updatedBook);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }
}


