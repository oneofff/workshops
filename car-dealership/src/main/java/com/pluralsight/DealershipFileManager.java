package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {

    private static final String DEALERSHIP_FILE = "data/inventory.cvs";
    private static final String DELIMITER = "\\|";
    private static final String OUTPUT_DELIMITER = "|";

    public Dealership getDealership() {
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(DEALERSHIP_FILE))) {
            String dealershipInfoLine = reader.readLine();
            if (dealershipInfoLine != null) {
                String[] dealershipDetails = dealershipInfoLine.split(DELIMITER);
                if (dealershipDetails.length == 3) {
                    String name = dealershipDetails[0];
                    String address = dealershipDetails[1];
                    String phone = dealershipDetails[2];
                    dealership = new Dealership(name, address, phone);
                } else {
                    System.err.println("Error: Dealership info line is malformed. Expected 3 fields.");
                    return null;
                }

                String vehicleLine;
                while ((vehicleLine = reader.readLine()) != null) {
                    String[] vehicleDetails = vehicleLine.split(DELIMITER);
                    if (vehicleDetails.length == 8) {
                        try {
                            int vin = Integer.parseInt(vehicleDetails[0].trim());
                            int year = Integer.parseInt(vehicleDetails[1].trim());
                            String make = vehicleDetails[2].trim();
                            String model = vehicleDetails[3].trim();
                            String type = vehicleDetails[4].trim();
                            String color = vehicleDetails[5].trim();
                            int odometer = Integer.parseInt(vehicleDetails[6].trim());
                            double price = Double.parseDouble(vehicleDetails[7].trim());
                            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                            dealership.addVehicle(vehicle);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing vehicle data for line: " + vehicleLine + " - " + e.getMessage());
                        }
                    } else {
                        System.err.println("Error: Vehicle data line is malformed. Expected 8 fields. Line: " + vehicleLine);
                    }
                }
            } else {
                 System.err.println("Error: Dealership file is empty or dealership info line is missing.");
            }
        } catch (IOException e) {
            System.err.println("Error loading dealership data from file '" + DEALERSHIP_FILE + "': " + e.getMessage());
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        if (dealership == null) {
            System.err.println("Cannot save a null dealership.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEALERSHIP_FILE))) {
            writer.write(String.join(OUTPUT_DELIMITER,
                    dealership.getName(),
                    dealership.getAddress(),
                    dealership.getPhone()));
            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(String.join(OUTPUT_DELIMITER,
                        String.valueOf(vehicle.getVin()),
                        String.valueOf(vehicle.getYear()),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        String.valueOf(vehicle.getOdometer()),
                        String.format("%.2f", vehicle.getPrice())
                ));
                writer.newLine();
            }
            System.out.println("Dealership data saved successfully to " + DEALERSHIP_FILE);
        } catch (IOException e) {
            System.err.println("Error saving dealership data to file '" + DEALERSHIP_FILE + "': " + e.getMessage());
        }
    }
}