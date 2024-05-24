package RentalManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalManager {
    private List<Rentable> rentableVehicles = new ArrayList<>();
    private List<Rentable> rentedVehicles = new ArrayList<>();

    public void addVehicle(Rentable vehicle) {
        rentableVehicles.add(vehicle);
    }

    public void rentVehicle(Rentable vehicle) {
        vehicle.rent();
        rentedVehicles.add(vehicle);
        rentableVehicles.remove(vehicle);
    }

    public void returnVehicle(Rentable vehicle) {
        vehicle.returnVehicle();
        rentableVehicles.add(vehicle);
        rentedVehicles.remove(vehicle);
    }

    public double calculateCost(Rentable vehicle, int hours) {
        return vehicle.calculateRentalCost(hours);
    }

    public void displayMenu() {
        System.out.println("Vehicle Rental System Menu:");
        System.out.println("1. Rent a Car");
        System.out.println("2. Rent a Motorcycle");
        System.out.println("3. Rent a Bicycle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. Calculate Rental Cost");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");
    }

    public static void main(String[] args) {
        RentalManager manager = new RentalManager();
        Scanner scanner = new Scanner(System.in);

        // Adding vehicles to the system
        Car car = new Car();
        Motorcycle motorcycle = new Motorcycle();
        Bicycle bicycle = new Bicycle();

        manager.addVehicle(car);
        manager.addVehicle(motorcycle);
        manager.addVehicle(bicycle);

        boolean exit = false;
        while (!exit) {
            manager.displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Renting a Car:");
                    manager.rentVehicle(car);
                    break;
                case 2:
                    System.out.println("Renting a Motorcycle:");
                    manager.rentVehicle(motorcycle);
                    break;
                case 3:
                    System.out.println("Renting a Bicycle:");
                    manager.rentVehicle(bicycle);
                    break;
                case 4:
                    System.out.println("Return a Vehicle:");
                    System.out.println("1. Car");
                    System.out.println("2. Motorcycle");
                    System.out.println("3. Bicycle");
                    System.out.print("Select vehicle type to return: ");
                    int returnChoice = scanner.nextInt();
                    switch (returnChoice) {
                        case 1:
                            manager.returnVehicle(car);
                            break;
                        case 2:
                            manager.returnVehicle(motorcycle);
                            break;
                        case 3:
                            manager.returnVehicle(bicycle);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Calculate Rental Cost:");
                    System.out.println("1. Car");
                    System.out.println("2. Motorcycle");
                    System.out.println("3. Bicycle");
                    System.out.print("Select vehicle type: ");
                    int costChoice = scanner.nextInt();
                    System.out.print("Enter rental duration in hours: ");
                    int hours = scanner.nextInt();
                    switch (costChoice) {
                        case 1:
                            System.out.println("Car rental cost for " + hours + " hours: $" + manager.calculateCost(car, hours));
                            break;
                        case 2:
                            System.out.println("Motorcycle rental cost for " + hours + " hours: $" + manager.calculateCost(motorcycle, hours));
                            break;
                        case 3:
                            System.out.println("Bicycle rental cost for " + hours + " hours: $" + manager.calculateCost(bicycle, hours));
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
