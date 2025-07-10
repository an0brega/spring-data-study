package br.com.backsolutions.springdata.study.repository;

import br.com.backsolutions.springdata.study.orm.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for Employee entity operations.
 *
 * This interface extends CrudRepository, providing basic CRUD operations. Additionally, it defines a derived query method:
 *
 * 1. "findBy" - Spring Data interprets this as a select query.
 * 2. "EmployeeName" - matches the entity's attribute name to filter on.
 *
 * The parameter "employeeName" is used as the value for the attribute "employeeName"
 * in the query.
 *
 * Thus, when you call findByEmployeeName("John"), Spring Data generates a query similar to:
 * SELECT e FROM Employee e WHERE e.employeeName = John
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByEmployeeName(String employeeName);
}
