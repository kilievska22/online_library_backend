package mk.ukim.finki.emt.onlinelibrary.librarymanagment;

import mk.ukim.finki.emt.onlinelibrary.sharedkernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan("<mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model>")
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class LibraryManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagmentApplication.class, args);
    }

}
