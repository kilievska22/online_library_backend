package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends AbstractEntity<AuthorId> {
    @Version
    private Long version;
    private String name;
    private String date_of_birth;
    private String biography;
    @JsonIgnore
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;
    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Author(String name, String date_of_birth, String biography, Genre genre) {
        this.name=name;
        this.date_of_birth=date_of_birth;
        this.biography=biography;
        this.genre=genre;
    }

    public <ID extends DomainObjectId> Author(ID randomId, String name, String date_of_birth, String biography, Genre genreAdd) {
        super((AuthorId) randomId);
        this.name=name;
        this.date_of_birth=date_of_birth;
        this.biography=biography;
        this.genre=genre;
    }
}
