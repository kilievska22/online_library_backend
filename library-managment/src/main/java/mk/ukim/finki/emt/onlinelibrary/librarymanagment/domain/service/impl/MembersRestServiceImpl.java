package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Member;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.MemberId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository.MembersJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.MembersRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class MembersRestServiceImpl implements MembersRestService {
    private final MembersJpaRepository repository;


    public MembersRestServiceImpl(MembersJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Member> getAllMembers(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Member(String id){
        this.repository.deleteById(new MemberId(id));

    }

    @Override
    public Member editMember(String ESSN, String name,
                             LocalDate membership_start, LocalDate membership_expiration,String email, String phone) { ;
        this.repository.updateMember(new MemberId(ESSN), name, membership_start,membership_expiration,email,phone);
        return this.repository.findById(new MemberId(ESSN)).get();

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public Optional<Member> findMemberById(String id) {
        return this.repository.findById(new MemberId(id));
    }

    @Override
    public Member createMember(Member member) {
        return this.repository.save(member);
    }

    @Override
    public List<Member> searchAllMembers(String term) {
        if(isNumeric(term)==true){
            return   this.repository.searchMemberEssn(term);}
        else {return this.repository.searchMember(term);}
    }


}
