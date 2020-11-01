package mk.ukim.finki.emt.onlinelibrary.sharedkernel.infra.eventlog;


import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);
}
