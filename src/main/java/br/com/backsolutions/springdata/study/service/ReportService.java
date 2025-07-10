package br.com.backsolutions.springdata.study.service;

import br.com.backsolutions.springdata.study.orm.Employee;
import br.com.backsolutions.springdata.study.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void initial(Scanner scanner) {
        while (system) {
            System.out.println("Choose the action");
            System.out.println("0 - Quit");
            System.out.println("1 - Search employee by name");
            System.out.println("2 - Search employee by name, entry date and greater salary");
            System.out.println("3 - Search employee entry date");
            System.out.print("Choice: ");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    searchEmployeeName(scanner);
                    system = false;
                    break;
                case 2:
                    searchEmployeeNameSalaryGreaterThanDate(scanner);
                    system = false;
                    break;
                case 3:
                    searchEmployeeEntryDate(scanner);
                    system = false;
                    break;
                default:
                    System.out.println("The number submitted is not related to any option. Try again.");
                    break;
            }
        }
    }

    private void searchEmployeeName(Scanner scanner) {
        System.out.print("Input the name you want to search for: ");
        String name = scanner.next();

        List<Employee> list = employeeRepository.findByEmployeeName(name);
        list.forEach(System.out::println);
    }

    private void searchEmployeeNameSalaryGreaterThanDate(Scanner scanner) {
        System.out.print("Input the employee name: ");
        String name = scanner.next();

        System.out.print("Input the employee salary: ");
        Double salary = scanner.nextDouble();

        System.out.print("Input the employee entry date: ");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<Employee> list = employeeRepository.findNameEntryDateSalaryGreater(name, salary, localDate);

        System.out.println(list);
    }

    private void searchEmployeeEntryDate(Scanner scanner) {
        System.out.print("Input the employee entry date: ");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<Employee> list = employeeRepository.findEntryDateGreater(localDate);

        System.out.println(list);
    }
}