package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Edition;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EditionId;

import java.util.List;
import java.util.Optional;

public interface EditionsRestService {
    public Edition createEdition(Edition edition);
    public List<Edition> getAllEditions();
    public Optional<Edition> findEditionById(String id);
    void updateEdition(EditionId edition_id, Boolean free);
    public List<Edition>getBookEditions(BookId bookId);

}
