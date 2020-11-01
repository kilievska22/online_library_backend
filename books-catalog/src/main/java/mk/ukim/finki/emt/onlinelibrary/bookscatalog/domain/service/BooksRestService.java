package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Author;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Book;
//import com.example.demo.model.CountBorrowings;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BooksRestService {
    public Page<Book> getAllBooks(int page, int size);
    public List<Book> getAllBooks();
    public void delete_Book(String id);
    public Book editBook(String id, String title, String plot, Genre genre, List<Author> authors);
    public Optional<Book>findBookById(String id);
    public Book createBook(Book book);
    List<Book> searchAllBooks(String term);
    //List<CountBorrowings> getBorrowings();




}
