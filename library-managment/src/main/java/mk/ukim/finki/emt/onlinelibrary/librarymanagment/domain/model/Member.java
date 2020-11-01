package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends AbstractEntity<MemberId> {
    @Version
    private Long version;
    private String name;
    private LocalDate membership_start;
    private LocalDate membership_expiration;
    private String email;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
    @JsonIgnore

    private List<Borrowing> borrowings;
    private boolean active_membership;

    public Member( String name, LocalDate membership_start, LocalDate membership_expiration, String email, String phone) {

        this.name=name;
        this.membership_start=membership_start;
        this.membership_expiration=membership_expiration;
        this.email=email;
        this.phone=phone;
    }

    public  Member(MemberId randomId, String name, LocalDate membership_start, LocalDate membership_expiration, String email, String phone) {
    super(randomId);
        this.name=name;
        this.membership_start=membership_start;
        this.membership_expiration=membership_expiration;
        this.email=email;
        this.phone=phone;
    }
}
