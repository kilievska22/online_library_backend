package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface BorrowingsJpaRepository extends JpaRepository<Borrowing, BorrowingId> {
    Page<Borrowing> findAllByOrderByFrom(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Borrowing b set b.from=:from, b.to=:to, b.member=:member, b.edition=:edition, b.employee=:employee,b.active=:active where b.id=:borrowingId")
    void updateBorrowing(@Param("borrowingId") BorrowingId borrowingId, @Param("from") LocalDate from, @Param("to") LocalDate to, @Param("member") Member member, @Param("edition") Edition edition,
                         @Param("employee") Employee employee, @Param("active") Boolean active);



    @Modifying
    @Transactional
    @Query("UPDATE Borrowing b set b.active=:active where b.id=:borrowingId")
    void endBorrowing(@Param("borrowingId") BorrowingId borrowingId, @Param("active") Boolean active);
    @Query("select b from Borrowing b inner join  b.member m inner join b.employee e WHERE  m.id = :parseInt or e.id=:parseInt")
    List<Borrowing> searchBorrowings(@Param("parseInt") String parseInt);

}
