package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.web;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.BookId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Edition;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EditionId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.EditionsRestService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/editions", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class EditionsApi {
   // private final BooksRestService booksRestService;
    private final EditionsRestService editionsRestService;

    public EditionsApi(EditionsRestService editionsRestService) {

        //this.booksRestService = booksRestService;
        this.editionsRestService = editionsRestService;

    }
    @GetMapping
    public List<Edition> getAllEditions(){
        return this.editionsRestService.getAllEditions();

    }
    @GetMapping("/{Id}")
    public List<Edition> getBookEditions(@PathVariable("Id")String id){
        return this.editionsRestService.getBookEditions(new BookId(id));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Edition createEditon(@RequestParam("Id") String Id, @RequestParam("num_pages") Integer num_pages,
                                HttpServletResponse response, UriComponentsBuilder builder){

        //Book book=this.booksRestService.findBookById(Id).get();
        //BookId id=book.id();

        Edition result=this.editionsRestService.createEdition(new Edition(EditionId.randomId(EditionId.class),num_pages,new BookId(Id),true));
        BookId b=result.getBook();
        System.out.println(b.toString());
        Instant instant=Instant.now();
        //this.applicationEventPublisher.publishEvent(new EditionCreated(b, instant));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }





}
