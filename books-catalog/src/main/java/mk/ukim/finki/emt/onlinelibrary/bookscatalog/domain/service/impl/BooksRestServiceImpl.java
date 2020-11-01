package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.integration.EditionCreatedEvent;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Author;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Book;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.repository.BooksJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.BooksRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//import com.example.demo.model.CountBorrowings;

@Service
@Transactional
public class BooksRestServiceImpl implements BooksRestService {
    private final BooksJpaRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksRestServiceImpl.class);

    public BooksRestServiceImpl(BooksJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Book> getAllBooks(int page, int size) {
        return this.repository.findAllByOrderByTitle(PageRequest.of(page, size

        ));
    }
@Override
public void delete_Book(String id){
       this.repository.deleteById(new BookId(id));

}

    @Override
    public Book editBook(String id, String title, String plot, Genre genre, List<Author> authors) { ;
         this.repository.updateBook(new BookId(id), title, plot, genre);
         return this.repository.findById(new BookId(id)).get();

    }

    @Override
    public Optional<Book> findBookById(String id) {
        return this.repository.findById(new BookId(id));
    }

    @Override
    public Book createBook(Book book) {
        return this.repository.save(book);
    }

    @Override
    public List<Book> searchAllBooks(String term) {
        return this.repository.searchBooks(term);
    }



    @Override
    public List<Book> getAllBooks() {
        return this.repository.findAllByOrderByTitle();
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onEditionCreatedEvent(EditionCreatedEvent event) {
        LOGGER.info("editionAdded event occured");

        Book b = this.repository.findById(event.bookId()).orElseThrow(RuntimeException::new);
        b.setNum_editions(b.getNum_editions()+1);
        this.repository.save(b);
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBorrowingCreatedEvent(EditionCreatedEvent event) {
        LOGGER.info("borrowingCreated event occured");

        Book b = this.repository.findById(event.bookId()).orElseThrow(RuntimeException::new);
        b.setNum_editions(b.getNum_editions()-1);
        this.repository.save(b);
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBorrowingReturnedEvent(EditionCreatedEvent event) {
        LOGGER.info("borrowingReturned event occured");

        Book b = this.repository.findById(event.bookId()).orElseThrow(RuntimeException::new);
        b.setNum_editions(b.getNum_editions()+1);
        this.repository.save(b);
    }
}
