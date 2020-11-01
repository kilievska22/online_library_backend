package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;
@Getter
public class EditionCreatedEvent implements DomainEvent {
    @JsonProperty("bookId")
    private final BookId bookId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public EditionCreatedEvent(@JsonProperty("bookId") @NonNull BookId bookId,
                                @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.bookId = Objects.requireNonNull(bookId, "bookId must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public BookId bookId() {
        return bookId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
