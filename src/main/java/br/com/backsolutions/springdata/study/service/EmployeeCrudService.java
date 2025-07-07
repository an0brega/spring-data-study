package br.com.backsolutions.springdata.study.service;

import br.com.backsolutions.springdata.study.orm.Employee;
import br.com.backsolutions.springdata.study.orm.Role;
import br.com.backsolutions.springdata.study.repository.EmployeeRepository;
import br.com.backsolutions.springdata.study.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmployeeCrudService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    public EmployeeCrudService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
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
                viewEmployee();
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

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isEmpty()) {
            System.out.println("Role not found!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateToString = LocalDate.parse(entryDate, formatter);

        employee.setEmployeeName(name);
        employee.setCpf(cpf);
        employee.setSalary(salary);
        employee.setEntryDate(dateToString);
        employee.setRole(optionalRole.get());

        employeeRepository.save(employee);

        System.out.println("Saved");
    }

    private void updateEmployee(Scanner scanner) {
    }

    private void viewEmployee() {
    }

    private void deleteEmployee(Scanner scanner) {
    }
}