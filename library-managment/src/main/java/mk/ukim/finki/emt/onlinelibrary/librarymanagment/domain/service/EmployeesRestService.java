package mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.service;

import mk.ukim.finki.emt.onlinelibrary.librarymanagment.domain.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeesRestService {
    public Page<Employee> getAllEmployees(int page, int size);
    public void delete_Employee(String id);
   public Employee editEmployee(String ESSN, String name, Integer working_time, Integer salary, String position, String phone,
                                String email);
    public Optional<Employee>findEmployeeById(String id);
    public Employee createEmployee(Employee employee);
    List<Employee> searchAllEmployees(String term);



}
