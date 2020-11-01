package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Member;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MembersRestService {
    public Page<Member> getAllMembers(int page, int size);
    public void delete_Member(String id);
    public Member editMember(String ESSN, String name,
                             LocalDate membership_start, LocalDate membership_expiration, String email, String phone);

    public Optional<Member>findMemberById(String id);
    public Member createMember(Member member);

    List<Member> searchAllMembers(String term);


}
