package br.com.backsolutions.springdata.study;

import br.com.backsolutions.springdata.study.service.RoleCrudService;
import br.com.backsolutions.springdata.study.service.EmployeeCrudService;
import br.com.backsolutions.springdata.study.service.WorkUnitCrudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class SpringDataStudyApplication implements CommandLineRunner {

	private final RoleCrudService roleService;
	private final EmployeeCrudService employeeService;
	private final WorkUnitCrudService workUnitCrudService;

	private Boolean system = true;

	public SpringDataStudyApplication(RoleCrudService roleCrudService, EmployeeCrudService employeeService, WorkUnitCrudService workUnitCrudService) {
		this.roleService = roleCrudService;
		this.employeeService = employeeService;
        this.workUnitCrudService = workUnitCrudService;
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
			System.out.println("3 - Work Unit");

			int action = 0;

			try {
				System.out.print("Choice: ");
				action = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid imput.");
				continue;
			}

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
				case 3:
					workUnitCrudService.initial(scanner);
					break;
				default:
					System.out.println("The number submitted is not related to any option. Try again.");
					break;
			}
		}
	}
}