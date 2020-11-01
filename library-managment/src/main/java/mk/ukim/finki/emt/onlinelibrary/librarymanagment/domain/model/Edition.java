package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Edition extends AbstractEntity<EditionId> {
    @Version
    private Long version;
    private int num_pages;
    private boolean free;

    //@ManyToOne (fetch = FetchType.EAGER)
    @Embedded
    @AttributeOverride(name="id",column = @Column(name="book_id",nullable = false))
    private BookId book;

    //@OneToMany (fetch = FetchType.EAGER, mappedBy = "edition")
    //@JsonIgnore

    //private List<Borrowing> borrowings;

    public Edition(EditionId editionId, Integer num_pages, BookId book, boolean b) {
        super(editionId);
        this.num_pages=num_pages;
        this.book=book;
        this.free=b;
    }
}
