package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class GenreId extends DomainObjectId {
    public GenreId(String id) {
        super(id);
    }
}
