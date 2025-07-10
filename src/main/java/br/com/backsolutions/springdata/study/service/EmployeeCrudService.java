package br.com.backsolutions.springdata.study.service;

import br.com.backsolutions.springdata.study.orm.Employee;
import br.com.backsolutions.springdata.study.orm.Role;
import br.com.backsolutions.springdata.study.orm.WorkUnit;
import br.com.backsolutions.springdata.study.repository.EmployeeRepository;
import br.com.backsolutions.springdata.study.repository.RoleRepository;
import br.com.backsolutions.springdata.study.repository.WorkUnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeCrudService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final WorkUnitRepository workUnitRepository;

    public EmployeeCrudService(EmployeeRepository employeeRepository, RoleRepository roleRepository, WorkUnitRepository workUnitRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.workUnitRepository = workUnitRepository;
    }

    public void initial(Scanner scanner) {

        System.out.println("1 - Add");
        System.out.println("2 - Update");
        System.out.println("3 - Visualize");
        System.out.println("4 - Delete");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                saveEmployee(scanner);
                break;
            case 2:
                updateEmployee(scanner);
                break;
            case 3:
                viewEmployee(scanner);
                break;
            case 4:
                deleteEmployee(scanner);
                break;
            default:
                System.out.println("There is not option corresponding to the number you input, try again");
                break;
        }
    }

    private void saveEmployee(Scanner scanner) {
        Employee employee = new Employee();

        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Cpf: ");
        long cpf = scanner.nextLong();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Entry Date: ");
        String entryDate = scanner.next();

        System.out.print("Role id: ");
        int roleId = scanner.nextInt();

        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isEmpty()) {
            System.out.println("Role not found!");
            return;
        }

        System.out.print("Work unit id: ");
        int workUnitId = scanner.nextInt();

        Optional<WorkUnit> workUnit = workUnitRepository.findById(workUnitId);
        if (role.isEmpty()) {
            System.out.println("Work unit not found!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateToString = LocalDate.parse(entryDate, formatter);

        employee.setEmployeeName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        employee.setEntryDate(dateToString);
        employee.setRole(role.get());
        employee.setWorkUnit(workUnit.get());

        employeeRepository.save(employee);

        System.out.println("Saved");
    }

    private void updateEmployee(Scanner scanner) {
        try {
            System.out.print("Employee id:");
            Integer employeeId = scanner.nextInt();

            var employeeFound = employeeRepository.findById(employeeId);

            if (employeeFound.isPresent()) {
                Employee employeeToUpdate = employeeFound.get();

                System.out.print("New employee role id:");
                int newRoleId = scanner.nextInt();

                Optional<Role> roleFound = roleRepository.findById(newRoleId);
                if (roleFound.isEmpty()) {
                    System.out.println("Role not found!");
                    return;
                }

                employeeToUpdate.setRole(roleFound.get());

                employeeRepository.save(employeeToUpdate);

                System.out.println("Updated!");
            }
            else
            {
                System.out.println("Role not found.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void viewEmployee(Scanner scanner) {
        System.out.println("Which page do you want to view?");
        System.out.print("Page: ");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 6, Sort.by(Sort.Direction.DESC,"employeeName"));
        Page<Employee> employees = employeeRepository.findAll(pageable);

        System.out.println(employees);
        System.out.println("Current page: " + employees.getNumber());
        System.out.println("Total elements : " + employees.getTotalElements());
        employees.forEach(System.out::println);
    }

    private void deleteEmployee(Scanner scanner) {
        System.out.print("Id to be deleted: ");
        int idToBeDeleted = scanner.nextInt();

        employeeRepository.deleteById(idToBeDeleted);
        System.out.println("Employee deleted");
    }
}