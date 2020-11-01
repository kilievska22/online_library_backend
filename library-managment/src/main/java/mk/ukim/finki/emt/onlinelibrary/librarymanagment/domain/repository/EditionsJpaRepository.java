package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Edition;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EditionsJpaRepository extends JpaRepository<Edition, EditionId> {
    @Modifying
    @Transactional
    @Query("UPDATE Edition e set e.free=:free where e.id=:edition_id")
    void updateEdition(@Param("edition_id") EditionId edition_id, @Param("free") Boolean free);
    @Query("select e from Edition e  WHERE e.book = :bookId ")
    List<Edition> searchBookEdiitons(BookId bookId);

}
