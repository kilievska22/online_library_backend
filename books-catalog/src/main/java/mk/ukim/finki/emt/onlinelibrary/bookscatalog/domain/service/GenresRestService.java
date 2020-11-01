package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
//import com.example.demo.model.countBorrowingsGenres;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GenresRestService {
    public Page<Genre> getAllGenres(int page, int size);
    public void delete_Genre(String id);
    public Genre editGenre(String id, String title, String description, Long period);
    public Optional<Genre>findGenreById(String id);
    public Genre createGenre(Genre genre);
    List<Genre> searchAllGenres(String term);

   // List<countBorrowingsGenres> getBorrowings();


}
