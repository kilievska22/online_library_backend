package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.web;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.*;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.*;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/borrowings", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class BorrowingsApi {

    private final BorrowingRestService borrowingRestService;
    private final EditionsRestService editionsRestService;
    private final EmployeesRestService employeesRestService;
    private final MembersRestService membersRestService;




    public BorrowingsApi(BorrowingRestService borrowingRestService, EditionsRestService editionsRestService, EmployeesRestService employeesRestService, MembersRestService membersRestService) {

        this.borrowingRestService = borrowingRestService;
        this.membersRestService = membersRestService;
        this.employeesRestService = employeesRestService;
        this.editionsRestService = editionsRestService;
    }

    @GetMapping
    public Page<Borrowing> getAllBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                       @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.borrowingRestService.getAllBorrowings(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Borrowing> getBorrowingById(@PathVariable("Id")String id){
        return this.borrowingRestService.findBorrowingById(id);
    }
    @GetMapping("/return/{Id}")
    public void returnBorrowing(@PathVariable("Id")String id){
        this.borrowingRestService.endBorrowing(id);
        Edition edition=this.borrowingRestService.findBorrowingById(id).get().getEdition();
       // System.out.println(edition);
        this.editionsRestService.updateEdition(edition.id(), true);
    }
    @GetMapping("/search")
    public List<Borrowing> search(@RequestParam("term") String term){

        return this.borrowingRestService.searchBorrowings(term);
    }
    @DeleteMapping("/{Id}")
    public void deleteBorrowing(@PathVariable("Id")String id){
        this.borrowingRestService.delete_Borrowing(new BorrowingId(id));
    }
    @PatchMapping("/{Id}")
    public Borrowing editBorrowing(@PathVariable("Id") String borrowingId, @RequestParam("from")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate from, @RequestParam("to")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to, @RequestParam("memberId")  String memberId, @RequestParam("editionId") String editionId,
                                   @RequestParam("employeeId")    String employeeId, @RequestParam("active") Boolean active){

         Edition editionAdd=this.editionsRestService.findEditionById(editionId).get();
        Member memberAdd=this.membersRestService.findMemberById(memberId).get();

Employee employeeAdd=this.employeesRestService.findEmployeeById(employeeId).get();
        return this.borrowingRestService.editBorrowing(borrowingId, from, to, memberAdd,editionAdd,employeeAdd,active);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Borrowing createBorrowing(@RequestParam("from")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate from, @RequestParam("to")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to, @RequestParam("memberId")  String memberId, @RequestParam("editionId")  String editionId,
                                     @RequestParam("employeeId")    String employeeId){
        Edition editionAdd=this.editionsRestService.findEditionById(editionId).get();
        this.editionsRestService.updateEdition(new EditionId(editionId), false);
        Member memberAdd=this.membersRestService.findMemberById(memberId).get();

        Employee employeeAdd=this.employeesRestService.findEmployeeById(employeeId).get();

        Borrowing result=this.borrowingRestService.createBorrowing(new Borrowing(BorrowingId.randomId(BorrowingId.class),from,to,memberAdd, editionAdd, employeeAdd));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
