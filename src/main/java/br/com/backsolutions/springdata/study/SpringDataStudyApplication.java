package br.com.backsolutions.springdata.study;

import br.com.backsolutions.springdata.study.service.RoleCrudService;
import br.com.backsolutions.springdata.study.service.EmployeeCrudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataStudyApplication implements CommandLineRunner {

	private final RoleCrudService roleService;
	private final EmployeeCrudService employeeService;

	private Boolean system = true;

	public SpringDataStudyApplication(RoleCrudService roleCrudService, EmployeeCrudService employeeService) {
		this.roleService = roleCrudService;
		this.employeeService = employeeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataStudyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while(system){
			System.out.println("Choose the action");
			System.out.println("0 - Quit");
			System.out.println("1 - Role");
			System.out.println("2 - Employee");
			System.out.print("Choice: ");

			int action = scanner.nextInt();

			switch(action){
				case 0:
					system = false;
					break;
				case 1:
					roleService.initial(scanner);
					break;
				case 2:
					employeeService.initial(scanner);
					break;
				default:
					System.out.println("The number submitted is not related to any option. Try again.");
					break;
			}
		}
	}
}