package mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.web;

import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.model.*;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.AuthorsRestService;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.BooksRestService;
import mk.ukim.finki.emt.onlinelibrary.bookscatalog.domain.service.GenresRestService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/books", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class BooksApi {

    private final BooksRestService booksRestService;
    private final GenresRestService genresRestService;
    private final AuthorsRestService authorsRestService;



    public BooksApi(BooksRestService booksRestService, GenresRestService genresRestService, AuthorsRestService authorsRestService) {

        this.booksRestService = booksRestService;
        this.genresRestService = genresRestService;

        this.authorsRestService = authorsRestService;
    }

    @GetMapping
    public Page<Book> getAllBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.booksRestService.getAllBooks(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Book> getBookById(@PathVariable("Id")String id){
        return this.booksRestService.findBookById(id);
    }
    @DeleteMapping("/{Id}")
    public void deletePizza(@PathVariable("Id")String id){
        this.booksRestService.delete_Book(id);
    }
    @PatchMapping("/{Id}")
    public Book editBook(@PathVariable("Id") String id, @RequestParam("title") String title,
                         @RequestParam("plot") String plot, @RequestParam("genre") String  genre, @RequestParam("authors")List<String>authors, @RequestParam(value = "bookid", defaultValue = "0", required = false)Integer bookid){

        Genre genreAdd=this.genresRestService.findGenreById(genre).get();
        List<Author>authorsAdd=new ArrayList<>();
        for(String i:authors){
            System.out.println(i);
            Author find=this.authorsRestService.findAuthorById(i).get();
            authorsAdd.add(find);
        }

        return this.booksRestService.editBook(id, title, plot, genreAdd,authorsAdd);
    }
    @GetMapping("/search")
    public List<Book> search(@RequestParam("term") String term){

        return this.booksRestService.searchAllBooks(term);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestParam("title") String title, @RequestParam("plot") String plot, @RequestParam("genre") String genre,
                           @RequestParam("authors")  List<String>  authors, HttpServletResponse response, UriComponentsBuilder builder){

        Genre genreAdd=this.genresRestService.findGenreById(genre).get();
List<Author>authorsAdd=new ArrayList<>();
for(String i:authors){
    System.out.println(i);
    Author find=this.authorsRestService.findAuthorById(i).get();
    authorsAdd.add(find);
}
        Book result=this.booksRestService.createBook(new Book(BookId.randomId(BookId.class),title,plot,genreAdd, authorsAdd));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
