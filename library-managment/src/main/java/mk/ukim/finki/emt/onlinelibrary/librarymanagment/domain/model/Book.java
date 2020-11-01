package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends AbstractEntity<BookId> {
    @Version
    private Long version;
    private String title;
    private String plot;
    private int num_editions;
    //@JsonIgnore
    //@OneToMany( mappedBy = "book")
    //private List<Edition> editions;
    //@ManyToMany(fetch = FetchType.EAGER)
    //private List<Author> authors;
    //@ManyToOne
    //private Genre genre;




    public Book(String title, String plot) {

        this.title=title;
        this.plot=plot;
    }

   /* public Book(String title, String plot, Genre genre, List<Author>authors) {
        this.title=title;
        this.plot=plot;
        this.genre=genre;
        this.authors=authors;

    }*/

/*    public <ID extends DomainObjectId> Book(ID randomId, String title, String plot, Genre genreAdd, List<Author> authorsAdd) {
        super((BookId) randomId);
        this.title=title;
        this.plot=plot;
        this.genre=genreAdd;
        this.authors=authorsAdd;
    }*/
}
