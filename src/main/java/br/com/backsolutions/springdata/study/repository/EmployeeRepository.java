package br.com.backsolutions.springdata.study.repository;

import br.com.backsolutions.springdata.study.orm.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
