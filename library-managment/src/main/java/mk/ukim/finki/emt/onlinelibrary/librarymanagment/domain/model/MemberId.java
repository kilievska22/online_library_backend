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
public class MemberId extends DomainObjectId {
    public MemberId(String id) {
        super(id);
    }
}
