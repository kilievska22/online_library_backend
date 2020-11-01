package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Borrowing extends AbstractEntity<BorrowingId> {
    @Version
    private Long version;
    @Column(name = "from_time")
    private LocalDate from;

    @Column(name = "to_time")
    private LocalDate to;
    @ManyToOne (fetch = FetchType.EAGER)
    private Member member;
    @ManyToOne (fetch = FetchType.EAGER)
    private Edition edition;
    @ManyToOne (fetch = FetchType.EAGER)
    private Employee employee;
    @JsonIgnore
    @OneToMany (mappedBy = "borrowing", fetch =FetchType.EAGER)
    private List<Penalty>penalties;
    private boolean active;


    public Borrowing(LocalDate from, LocalDate to, Member memberAdd, Edition editionAdd, Employee employeeAdd) {
        this.from=from;
        this.to=to;
        this.member=memberAdd;
        this.edition=editionAdd;
        this.employee=employeeAdd;
        this.active=true;

    }

    public <ID extends DomainObjectId> Borrowing(ID randomId, LocalDate from, LocalDate to, Member memberAdd, Edition editionAdd, Employee employeeAdd) {
    super((BorrowingId) randomId);
        this.from=from;
        this.to=to;
        this.member=memberAdd;
        this.edition=editionAdd;
        this.employee=employeeAdd;
        this.active=true;
    }
}
