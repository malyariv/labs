package com.malyariv.library_site.repository;

import com.malyariv.library_site.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
