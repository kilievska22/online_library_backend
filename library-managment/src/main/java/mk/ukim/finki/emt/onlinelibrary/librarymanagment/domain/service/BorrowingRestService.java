package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowingRestService {
    public Page<Borrowing> getAllBorrowings(int page, int size);

    public void delete_Borrowing(BorrowingId id);
    public Borrowing editBorrowing(String borrowingId, LocalDate from, LocalDate to, Member member, Edition edition,
                                   Employee employee, Boolean active);
    public Optional<Borrowing>findBorrowingById(String id);
    public Borrowing createBorrowing(Borrowing borrowing);
    public void endBorrowing(String id);
    List<Borrowing> searchBorrowings(String parseInt);



}
