package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.impl;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Employee;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.EmployeeId;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.repository.EmployeesJpaRepository;
import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service.EmployeesRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class EmployeesRestServiceImpl implements EmployeesRestService {
    private final EmployeesJpaRepository repository;


    public EmployeesRestServiceImpl(EmployeesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Employee(String id){
        this.repository.deleteById(new EmployeeId(id));

    }

    @Override
    public Employee editEmployee(String ESSN, String name,  Integer working_time, Integer salary, String position, String phone,
                                 String email) { ;
        this.repository.updateEmployee(new EmployeeId(ESSN), name ,working_time,/*, working_time/*new Money(Currency.MKD,salary),*//*,salary,*/position,phone, email);
        return this.repository.findById(new EmployeeId(ESSN)).get();

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
    public Optional<Employee> findEmployeeById(String id) {
        return this.repository.findById(new EmployeeId(id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return this.repository.save(employee);
    }

    @Override
    public List<Employee> searchAllEmployees(String term) {
        if(isNumeric(term)==true){
         return   this.repository.searchEmployeeEssn(term);}
        else {return this.repository.searchEmployee(term);}
    }




}
