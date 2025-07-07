package br.com.backsolutions.springdata.study.service;

import br.com.backsolutions.springdata.study.orm.Role;
import br.com.backsolutions.springdata.study.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RoleCrudService {

    private final RoleRepository roleRepository;

    public RoleCrudService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void initial(Scanner scanner){

        System.out.println("1 - Add");
        System.out.println("2 - Update");
        System.out.println("3 - Visualize");
        System.out.println("4 - Delete");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                saveRegistry(scanner);
                break;
            case 2:
                updateRegistry(scanner);
                break;
            case 3:
                viewRegistry();
                break;
            case 4:
                deleteRegistry(scanner);
                break;
            default:
                System.out.println("There is not option corresponding to the number you input, try again");
                break;
        }
    }

    private void saveRegistry(Scanner scanner){
        Role role = new Role();

        System.out.print("Role description: ");
        String description = scanner.next();

        role.setRoleDescription(description);
        roleRepository.save(role);

        System.out.println("Saved");
    }

    private void updateRegistry(Scanner scanner){
        try {
        System.out.println("Role id");
        Integer roleId = scanner.nextInt();

        var roleFound = roleRepository.findById(roleId);

            if (roleFound.isPresent()) {
                Role roleToUpdate = roleFound.get();

                System.out.println("New role description: ");
                String newDescription = scanner.next();

                roleToUpdate.setRoleDescription(newDescription);
                roleRepository.save(roleToUpdate);

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

    private void viewRegistry(){
        Iterable<Role> roles = roleRepository.findAll();
        roles.forEach(System.out::println);
    }

    private void deleteRegistry(Scanner scanner){
        System.out.print("Id to be deleted: ");
        int id = scanner.nextInt();

        roleRepository.deleteById(id);
        System.out.println("Role deleted");
    }
}