package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class BorrowingId extends DomainObjectId {
    public BorrowingId(String id) {
        super(id);
    }
}
