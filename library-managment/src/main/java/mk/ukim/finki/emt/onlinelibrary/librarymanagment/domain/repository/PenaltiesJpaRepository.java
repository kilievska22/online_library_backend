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

public interface PenaltiesJpaRepository extends JpaRepository<Penalty, PenaltyId> {
    Page<Penalty> findAllByOrderByGivenAtDesc(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Penalty p set p.paid=:paid where p.id=:penaltyId")
    void updatePenalty(@Param("penaltyId") PenaltyId penaltyId, @Param("paid") Boolean paid);
    @Query("SELECT p FROM Penalty  p where p.dueDate=:today")
    Page<Penalty> getUnpaidTodayPenalties(Pageable pageable, @Param("today") LocalDate today);
    @Query("select p from Penalty p inner join p.borrowing b WHERE b.id= :parseInt or p.id=:parseInt ")
    List<Penalty> searchPenalty(@Param("parseInt") String parseInt);
}
