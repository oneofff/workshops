package com.pluralsight;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner;
    private final DealershipFileManager fileManager;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new DealershipFileManager();
    }

    private void init() {
        System.out.println("Loading dealership data...");
        this.dealership = fileManager.getDealership();
        if (this.dealership == null) {
            System.out.println("Could not load existing dealership data. Starting with a new/empty dealership.");
            System.out.print("Enter new Dealership Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Dealership Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter new Dealership Phone: ");
            String phone = scanner.nextLine();
            this.dealership = new Dealership(name, address, phone);
        } else {
            System.out.println("Dealership '" + dealership.getName() + "' loaded successfully.");
        }
    }

    public void display() {
        init();

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice (1-9, or 0 to Quit): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: processGetByPriceRequest(); break;
                    case 2: processGetByMakeModelRequest(); break;
                    case 3: processGetByYearRequest(); break;
                    case 4: processGetByColorRequest(); break;
                    case 5: processGetByMileageRequest(); break;
                    case 6: processGetByVehicleTypeRequest(); break;
                    case 7: processGetAllVehiclesRequest(); break;
                    case 8: processAddVehicleRequest(); break;
                    case 9: processRemoveVehicleRequest(); break;
                    case 0: System.out.println("Exiting application. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = 0;
            }
            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n===================================");
        System.out.println("       " + (dealership != null ? dealership.getName() : "Car Dealership") + " Menu");
        System.out.println("===================================");
        System.out.println("1 - Find vehicles by Price Range");
        System.out.println("2 - Find vehicles by Make/Model");
        System.out.println("3 - Find vehicles by Year Range");
        System.out.println("4 - Find vehicles by Color");
        System.out.println("5 - Find vehicles by Mileage Range");
        System.out.println("6 - Find vehicles by Type (Car, Truck, SUV, Van)");
        System.out.println("7 - List ALL Vehicles");
        System.out.println("8 - Add a Vehicle");
        System.out.println("9 - Remove a Vehicle");
        System.out.println("0 - Quit Application");
        System.out.println("-----------------------------------");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found matching your criteria.");
        } else {
            System.out.println("\n--- Found Vehicles ---");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), vehicles.get(i).toDisplayString());
            }
            System.out.println("----------------------");
        }
    }


    public void processGetByPriceRequest() {
        try {
            System.out.print("Enter minimum price: $");
            double min = scanner.nextDouble();
            System.out.print("Enter maximum price: $");
            double max = scanner.nextDouble();
            scanner.nextLine();
            if (min > max) {
                System.out.println("Minimum price cannot be greater than maximum price.");
                return;
            }
            displayVehicles(dealership.getVehiclesByPrice(min, max));
        } catch (InputMismatchException e) {
            System.out.println("Invalid price format. Please enter a number.");
            scanner.nextLine();
        }
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter vehicle make (e.g., Ford): ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter vehicle model (e.g., Explorer): ");
        String model = scanner.nextLine().trim();
        if (make.isEmpty() || model.isEmpty()) {
            System.out.println("Make and Model cannot be empty.");
            return;
        }
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        try {
            System.out.print("Enter minimum year: ");
            int min = scanner.nextInt();
            System.out.print("Enter maximum year: ");
            int max = scanner.nextInt();
            scanner.nextLine();
            if (min > max) {
                System.out.println("Minimum year cannot be greater than maximum year.");
                return;
            }
            displayVehicles(dealership.getVehiclesByYear(min, max));
        } catch (InputMismatchException e) {
            System.out.println("Invalid year format. Please enter a whole number.");
            scanner.nextLine();
        }
    }

    public void processGetByColorRequest() {
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine().trim();
        if (color.isEmpty()) {
            System.out.println("Color cannot be empty.");
            return;
        }
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        try {
            System.out.print("Enter minimum mileage: ");
            int min = scanner.nextInt();
            System.out.print("Enter maximum mileage: ");
            int max = scanner.nextInt();
            scanner.nextLine();
            if (min > max) {
                System.out.println("Minimum mileage cannot be greater than maximum mileage.");
                return;
            }
            displayVehicles(dealership.getVehiclesByMileage(min, max));
        } catch (InputMismatchException e) {
            System.out.println("Invalid mileage format. Please enter a whole number.");
            scanner.nextLine();
        }
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (e.g., Car, Truck, SUV): ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) {
            System.out.println("Vehicle type cannot be empty.");
            return;
        }
        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        try {
            System.out.println("\n--- Add New Vehicle ---");
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            if (dealership.findVehicleByVin(vin) != null) {
                System.out.println("Error: A vehicle with VIN " + vin + " already exists.");
                return;
            }
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Make: ");
            String make = scanner.nextLine().trim();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine().trim();
            System.out.print("Enter Vehicle Type (e.g., Car, Truck): ");
            String type = scanner.nextLine().trim();
            System.out.print("Enter Color: ");
            String color = scanner.nextLine().trim();
            System.out.print("Enter Odometer (miles): ");
            int odometer = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Price: $");
            double price = Double.parseDouble(scanner.nextLine());

            if (make.isEmpty() || model.isEmpty() || type.isEmpty() || color.isEmpty() || year <=1885 || price < 0 || odometer < 0) {
                System.out.println("Invalid input for one or more fields. Vehicle not added.");
                return;
            }

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle with VIN " + vin + " added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for one of the inputs. Vehicle not added. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void processRemoveVehicleRequest() {
        displayVehicles(dealership.getAllVehicles());
        try {
            System.out.print("Enter VIN of the vehicle to remove: ");
            int vinToRemove = Integer.parseInt(scanner.nextLine());
            Vehicle vehicleToRemove = dealership.findVehicleByVin(vinToRemove);

            if (vehicleToRemove != null) {
                System.out.println("Found vehicle: " + vehicleToRemove.toDisplayString());
                System.out.print("Are you sure you want to remove this vehicle? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();
                if (confirmation.equals("yes")) {
                    dealership.removeVehicle(vehicleToRemove);
                    fileManager.saveDealership(dealership);
                    System.out.println("Vehicle with VIN " + vinToRemove + " removed successfully.");
                } else {
                    System.out.println("Vehicle removal cancelled.");
                }
            } else {
                System.out.println("Vehicle with VIN " + vinToRemove + " not found in inventory.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN format. Please enter a whole number.");
        }
    }
}