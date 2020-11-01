package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.impl;

//import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.integration.BorrowingCreatedEvent;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.event.BorrowingCreated;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.event.BorrowingReturned;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.*;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository.BorrowingsJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.BorrowingRestService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class BorrowingRestServiceImpl implements BorrowingRestService {
    private final BorrowingsJpaRepository repository;

    private final ApplicationEventPublisher applicationEventPublisher;


    public BorrowingRestServiceImpl(BorrowingsJpaRepository repository, ApplicationEventPublisher applicationEventPublisher) {
        this.repository = repository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Page<Borrowing> getAllBorrowings(int page, int size) {
        return this.repository.findAllByOrderByFrom(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Borrowing(BorrowingId id){
        this.repository.deleteById(id);

    }

    @Override
    public Borrowing editBorrowing(String borrowingId, LocalDate from, LocalDate to, Member member, Edition edition,
                                    Employee employee, Boolean active) { ;
        this.repository.updateBorrowing(new BorrowingId(borrowingId), from, to, member,edition,employee,active);
        return this.repository.findById(new BorrowingId(borrowingId)).get();

    }

    @Override
    public Optional<Borrowing> findBorrowingById(String id) {
        return this.repository.findById(new BorrowingId(id));
    }

    @Override
    public Borrowing createBorrowing(Borrowing borrowing) {
        BookId b=borrowing.getEdition().getBook();
        Instant instant=Instant.now();
        System.out.println(b.toString());

        this.applicationEventPublisher.publishEvent(new BorrowingCreated(b, instant));
        return this.repository.save(borrowing);
    }

    @Override
    public void endBorrowing(String id) {
        Borrowing borrowing=repository.findById(new BorrowingId(id)).get();
        BookId b=borrowing.getEdition().getBook();
        Instant instant=Instant.now();
        this.applicationEventPublisher.publishEvent(new BorrowingReturned(b, instant));
         this.repository.endBorrowing(new BorrowingId(id), false);

    }

    @Override
    public List<Borrowing> searchBorrowings(String parseInt) {
        return this.repository.searchBorrowings(parseInt);
    }


}
