package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.web;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Employee;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EmployeeId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.EmployeesRestService;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.onlinelibrary.sharedkernel.domain.financial.Money;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class EmployeesApi {

    private final EmployeesRestService employeesRestService;

    public EmployeesApi(EmployeesRestService authorsRestService) {

        this.employeesRestService = authorsRestService;
    }

    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.employeesRestService.getAllEmployees(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Employee> getEmployeeById(@PathVariable("Id")String id){
        return this.employeesRestService.findEmployeeById(id);
    }
    @DeleteMapping("/{Id}")
    public void deleteEmployee(@PathVariable("Id")String id){
        this.employeesRestService.delete_Employee(id);
    }
    @PatchMapping("/{Id}")
    public Employee editEmployee(@PathVariable("Id") String id, @RequestParam("name") String name,
                                 @RequestParam("working_time")Integer working_time, @RequestParam("salary")Integer salary, @RequestParam("position") String position, @RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam(value = "ESSN", defaultValue = "0", required = false) Integer ESSN){



        return this.employeesRestService.editEmployee(id, name, working_time,salary,position,phone,email);
    }
    @GetMapping("/search")
    public List<Employee> search(@RequestParam("term") String term){

        return this.employeesRestService.searchAllEmployees(term);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestParam("name") String name,
                                   @RequestParam("working_time")Integer working_time, @RequestParam("salary")Integer salary, @RequestParam("position") String position, @RequestParam("phone") String phone, @RequestParam("email") String email){



        Employee result=this.employeesRestService.createEmployee(new Employee (EmployeeId.randomId(EmployeeId.class), name,working_time,new Money(Currency.MKD,salary),position,phone,email));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
