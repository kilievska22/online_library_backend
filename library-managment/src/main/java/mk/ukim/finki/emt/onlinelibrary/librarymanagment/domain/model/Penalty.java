package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Penalty extends AbstractEntity<PenaltyId> {
    @Version
    private Long version;
    //private int price;
    @Embedded
    private Money price;
    private LocalDate givenAt;
    private LocalDate dueDate;
    private boolean paid;

    @ManyToOne(fetch = FetchType.EAGER)
    private Borrowing borrowing;

    public Penalty(Borrowing borrowingAdd, LocalDate givenAt, LocalDate dueDate, Money price) {
        this.borrowing=borrowingAdd;
        this.givenAt=givenAt;
        this.dueDate=dueDate;
        this.price=price;
        this.paid=false;
    }

    public  Penalty(PenaltyId randomId, Borrowing borrowingAdd, LocalDate givenAt, LocalDate dueDate, Integer price) {
        super(randomId);
        this.borrowing=borrowingAdd;
        this.givenAt=givenAt;
        this.dueDate=dueDate;
        this.price=new Money(Currency.MKD, price);
        this.paid=false;
    }
}
