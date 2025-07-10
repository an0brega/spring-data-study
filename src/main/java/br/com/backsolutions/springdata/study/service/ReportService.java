package br.com.backsolutions.springdata.study.service;

import br.com.backsolutions.springdata.study.orm.Employee;
import br.com.backsolutions.springdata.study.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private final EmployeeRepository employeeRepository;
    private Boolean system = true;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void initial(Scanner scanner) {
        while (system) {
            System.out.println("Choose the action");
            System.out.println("0 - Quit");
            System.out.println("1 - Search employee by name");
            System.out.print("Choice: ");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    searchEmployeeName(scanner);
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
}
