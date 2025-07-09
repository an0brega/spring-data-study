package br.com.backsolutions.springdata.study.service;


import br.com.backsolutions.springdata.study.orm.Employee;
import br.com.backsolutions.springdata.study.orm.WorkUnit;
import br.com.backsolutions.springdata.study.repository.WorkUnitRepository;
import org.springframework.stereotype.Service;


import java.util.Scanner;

@Service
public class WorkUnitCrudService {

    private final WorkUnitRepository workUnitRepository;

    public WorkUnitCrudService(WorkUnitRepository workUnitRepository, WorkUnitRepository workUnitRepository1) {
        this.workUnitRepository = workUnitRepository1;
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
                saveWorkUnit(scanner);
                break;
            case 2:
                updateWorkUnit(scanner);
                break;
            case 3:
                viewWorkUnit();
                break;
            case 4:
                deleteWorkUnit(scanner);
                break;
            default:
                System.out.println("There is not option corresponding to the number you input, try again");
                break;
        }
    }

    private void saveWorkUnit(Scanner scanner) {
        WorkUnit workUnit = new WorkUnit();

        System.out.print("Unit description: ");
        scanner.nextLine();
        String unitDescription = scanner.nextLine();

        System.out.print("Unit address: ");
        scanner.nextLine();
        String unitAddress = scanner.next();

        workUnit.setUnitDescription(unitDescription);
        workUnit.setUnitAddress(unitAddress);

        workUnitRepository.save(workUnit);

        System.out.println("Saved");
    }

    private void updateWorkUnit(Scanner scanner) {
        try {
            System.out.print("Work unit id: ");
            Integer workUnitId = scanner.nextInt();

            var unitFound = workUnitRepository.findById(workUnitId);

            if (unitFound.isPresent()) {
                WorkUnit workUnitToUpdate = unitFound.get();

                System.out.print("New work unit description: ");
                String newDescription = scanner.next();

                workUnitToUpdate.setUnitDescription(newDescription);
                workUnitRepository.save(workUnitToUpdate);

                System.out.println("Updated!");
            }
            else
            {
                System.out.println("Work Unit not found.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void viewWorkUnit() {
        Iterable<WorkUnit> workUnits = workUnitRepository.findAll();
        workUnits.forEach(System.out::println);
    }

    private void deleteWorkUnit(Scanner scanner) {
        System.out.print("Work unit id: ");
        Integer workUnitId = scanner.nextInt();

        var unitFound = workUnitRepository.findById(workUnitId);

        if (unitFound.isPresent()) {
            WorkUnit workUnitToRemove = unitFound.get();
            workUnitRepository.delete(workUnitToRemove);
        }
        else{
            System.out.println("Work Unit not found, try again");
        }
    }
}