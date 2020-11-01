package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends AbstractEntity<EmployeeId> {
    @Version
    private Long version;
    private String name;
    private int working_time;
    //private int salary;
    @Embedded
    private Money salary;
    private String position;
    private String email;
    private String phone;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
@JsonIgnore
    private List<Borrowing> borrowings;

    public Employee( String name, Integer working_time, Money salary, String position, String phone, String email) {
   this.name=name;

   this.working_time=working_time;
   this.salary=salary;
   this.position=position;
   this.phone=phone;
   this.email=email;


    }



    public Employee(EmployeeId randomId, String name, Integer working_time, Money salary, String position, String phone, String email) {
        super(randomId);
        this.working_time=working_time;
        this.salary=salary;
        this.position=position;
        this.phone=phone;
        this.email=email;
    }
}
