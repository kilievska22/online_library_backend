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
public class Genre extends AbstractEntity<GenreId> {
    @Version
    private Long version;
    private String title;
    private String description;
    private Long period;
    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Book>books;
    @JsonIgnore
    @OneToMany (mappedBy = "genre")
    private List<Author>authors;

    public Genre(String title, String description, Long period) {
        this.title=title;
        this.period=period;
        this.description=description;
    }

    public <ID extends DomainObjectId> Genre(ID randomId, String title, String description, Long period) {
        super((GenreId) randomId);
        this.title=title;
        this.period=period;
        this.description=description;
    }
}
