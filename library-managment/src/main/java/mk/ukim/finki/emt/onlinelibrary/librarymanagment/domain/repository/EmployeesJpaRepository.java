package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Employee;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EmployeeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeesJpaRepository extends JpaRepository<Employee, EmployeeId> {
    Page<Employee> findAllByOrderByName(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Employee e set e.name=:name,  e.working_time=:working_time,  e.position=:position,e.phone=:phone, e.email=:email where e.id=:id")
    //@Query(value = "UPDATE employee e set e.name=:emp_name/*, e.working_time=:working_time, e.amount=:salary,  e.position=:emp_position, e.phone=:phone, e.email=:email*/ where e.id=:Emp_id", nativeQuery = true)

    void updateEmployee(@Param("id") EmployeeId id, @Param("name") String name,
                        @Param("working_time") Integer working_time, @Param("position") String position, @Param("phone") String phone, @Param("email") String email);
    @Query("select e from Employee e WHERE e.name like :term ")
    List<Employee> searchEmployee(@Param("term") String term);
    @Query("select e from Employee e  WHERE e.id like :parseInt ")
    List<Employee> searchEmployeeEssn(String parseInt);

}

