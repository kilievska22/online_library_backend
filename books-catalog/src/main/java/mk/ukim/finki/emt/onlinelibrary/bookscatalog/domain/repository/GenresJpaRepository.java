package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.repository;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.Genre;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.GenreId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface GenresJpaRepository extends JpaRepository<Genre, GenreId> {
    Page<Genre> findAllByOrderByTitle(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Genre g set g.title=:title, g.description=:description, g.period=:period where g.id=:genre_id")
    void updateGenre(@Param("genre_id") GenreId genre_id, @Param("title") String title, @Param("description") String description, @Param("period") Long period);

    @Query("select g from Genre g WHERE g.title like :term ")
    List<Genre> searchGenres(@Param("term") String term);
    @Query("select g from Genre g  WHERE g.period like :parseLong ")
    List<Genre> searchGenresDate(long parseLong);
    /*@Query("select g.title, count(b) from Borrowing b inner join b.edition e inner join e.book bo inner join bo.genre g group by g.title")
    List<Object[]> getBorrowings();*/

}

