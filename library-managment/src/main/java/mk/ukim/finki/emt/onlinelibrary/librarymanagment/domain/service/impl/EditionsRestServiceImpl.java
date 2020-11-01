package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.event.EditionCreated;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Edition;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EditionId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository.EditionsJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.EditionsRestService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EditionsRestServiceImpl implements EditionsRestService {
    private final EditionsJpaRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public EditionsRestServiceImpl(EditionsJpaRepository repository, ApplicationEventPublisher applicationEventPublisher, ApplicationEventPublisher applicationEventPublisher1) {
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher1;
    }

    @Override
    public Edition createEdition(Edition edition) {


        BookId b=edition.getBook();
        System.out.println(b.toString());
        Instant instant=Instant.now();
        this.applicationEventPublisher.publishEvent(new EditionCreated(b, instant));
        return this.repository.save(edition);
    }

    @Override
    public List<Edition> getAllEditions() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Edition> findEditionById(String id) {
        return this.repository.findById(new EditionId(id));
    }

    @Override
    public void updateEdition(EditionId editionId, Boolean free) {
        this.repository.updateEdition(editionId ,free);
    }

    @Override
    public List<Edition> getBookEditions(BookId bookId) {
        return this.repository.searchBookEdiitons(bookId);
    }
}
