package br.com.backsolutions.springdata.study.repository;

import br.com.backsolutions.springdata.study.orm.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Employee entity operations.
 * <p>
 * This interface extends CrudRepository, providing basic CRUD operations. Additionally, it defines a derived query method:
 * <p>
 * 1. "findBy" - Spring Data interprets this as a select query.
 * <p>
 * 2. "EmployeeName" - matches the entity's attribute name to filter on.
 * <p>
 * The parameter "employeeName" is used as the value for the attribute "employeeName"
 * in the query.
 * <p>
 * Thus, when you call findByEmployeeName("John"), Spring Data generates a query similar to:
 * SELECT e FROM Employee e WHERE e.employeeName = John
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByEmployeeName(String employeeName);

    /**
     * Filters an Employee inside the repository by the name, salary and entry date using JPQL.
     * @param name
     * @param salary
     * @param entryDate
     * @returns The tree values that matches the conditions
     */
    @Query("SELECT e FROM Employee e WHERE e.employeeName = :name AND " +
            "e.salary >= :salary AND e.entryDate = :entryDate")
    List<Employee> findNameEntryDateSalaryGreater(String name, Double salary, LocalDate entryDate);

    /**
     * Filters an Employee inside the repository by the entry date using a Native Query.
     * @param date
     * @return The value that matches the condition
     */
    @Query(value = "SELECT * FROM employees e WHERE e.entry_date >= :date", nativeQuery = true)
    List<Employee> findEntryDateGreater(LocalDate date);

}
