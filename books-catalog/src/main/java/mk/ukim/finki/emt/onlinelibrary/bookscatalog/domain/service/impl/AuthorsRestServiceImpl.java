package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Author;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.AuthorId;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.repository.AuthorsJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.AuthorsRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsRestServiceImpl implements AuthorsRestService {
    private final AuthorsJpaRepository repository;


    public AuthorsRestServiceImpl(AuthorsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Author> getAllAuthors(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Author(String id){
        this.repository.deleteById(new AuthorId(id));

    }

    @Override
    public Author editAuthor(String id, String name_sname, String date_of_birth, String biography, Genre genre) { ;
        this.repository.updateAuthor(new AuthorId(id), name_sname, date_of_birth, biography,genre);
        return this.repository.findById(new AuthorId(id)).get();

    }

    @Override
    public Optional<Author> findAuthorById(String id) {
        return this.repository.findById(new AuthorId(id));
    }

    @Override
    public Author createAuthor(Author author) {
        return this.repository.save(author);
    }

    @Override
    public List<Author> searchAllAuthors(String term) {
        return this.repository.searchAuthors(term);
    }

   /* @Override
    public List<countBorrowingsAuthors> getBorrowings() {
        List<Object[]>pom=this.repository.getBorrowings();
        List<countBorrowingsAuthors>res=new ArrayList<>();
        for(Object [] o:pom){
            res.add(new countBorrowingsAuthors((String)o[0], (Long)o[1]));
        }
        return res;
    }*/


}
