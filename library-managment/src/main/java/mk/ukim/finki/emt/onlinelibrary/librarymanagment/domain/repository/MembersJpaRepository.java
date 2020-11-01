package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Member;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.MemberId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface MembersJpaRepository extends JpaRepository<Member, MemberId> {
    Page<Member> findAllByOrderByName(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Member m set m.name=:name,  m.membership_start=:membership_start, m.membership_expiration=:membership_expiration, m.email=:email,m.phone=:phone where m.id=:ESSN")
    void updateMember(@Param("ESSN") MemberId ESSN, @Param("name") String name,
                      @Param("membership_start") LocalDate membership_start, @Param("membership_expiration") LocalDate membership_expiration, @Param("email") String email, @Param("phone") String phone);

    @Query("select m from Member m WHERE m.name like :term ")
    List<Member> searchMember(@Param("term") String term);
    @Query("select m from Member m  WHERE m.id like :parseInt ")
    List<Member> searchMemberEssn(String parseInt);
}

