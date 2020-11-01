package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.GenreId;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.repository.GenresJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.GenresRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//import com.example.demo.model.countBorrowingsGenres;

@Service
public class GenresRestServiceImpl implements GenresRestService {
    private final GenresJpaRepository repository;


    public GenresRestServiceImpl(GenresJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Genre> getAllGenres(int page, int size) {
        return this.repository.findAllByOrderByTitle(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Genre(String id){
        this.repository.deleteById(new GenreId(id));

    }

    @Override
    public Genre editGenre( String id, String title, String description, Long period) { ;
        this.repository.updateGenre(new GenreId(id), title, description, period);
        return this.repository.findById(new GenreId(id)).get();

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @Override
    public Optional<Genre> findGenreById(String id) {
        return this.repository.findById(new GenreId(id));
    }

    @Override
    public Genre createGenre(Genre genre) {
        return this.repository.save(genre);
    }

    @Override
    public List<Genre> searchAllGenres(String term) {
        if(isNumeric(term)==true){
        return this.repository.searchGenresDate(Long.parseLong(term));}
        else
            return
            this.repository.searchGenres(term);
    }




}
