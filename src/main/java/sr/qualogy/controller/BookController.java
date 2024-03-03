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


    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld(){
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        book = bookService.createBook(book);
        return Response.ok(book, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        book = bookService.updateBook(book);

        if (book != null) {
            return Response.ok(book, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

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
}
