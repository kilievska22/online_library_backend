package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Author;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
//import com.example.demo.model.countBorrowingsAuthors;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AuthorsRestService {
    public Page<Author> getAllAuthors(int page, int size);
    public void delete_Author(String id);
    public Author editAuthor(String id, String name_sname, String date_of_birth, String biography, Genre genre);
    public Optional<Author>findAuthorById(String id);
    public Author createAuthor(Author author);

    List<Author> searchAllAuthors(String term);
   // List<countBorrowingsAuthors> getBorrowings();


}
