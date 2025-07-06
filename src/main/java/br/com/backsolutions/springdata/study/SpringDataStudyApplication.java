package br.com.backsolutions.springdata.study;

import br.com.backsolutions.springdata.study.service.RoleCrudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataStudyApplication implements CommandLineRunner {

//	private final RoleRepository repository;
	private final RoleCrudService roleService;

	private Boolean system = true;

	public SpringDataStudyApplication(RoleCrudService roleCrudService) {
		this.roleService = roleCrudService;
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
			System.out.print("Choice: ");

			Integer action = scanner.nextInt();

			if(action == 1){
				roleService.initial(scanner);
			} else {
				system = false;
			}
		}
	}
}
