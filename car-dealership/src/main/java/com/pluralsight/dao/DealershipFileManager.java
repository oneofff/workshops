package com.pluralsight.dao;

import com.pluralsight.model.Dealership;
import com.pluralsight.model.Vehicle;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DealershipFileManager {

    private static final String DEALERSHIP_FILE = "data/inventory.csv";
    private static final String DELIMITER = "\\|";
    private static final String OUTPUT_DELIMITER = "|";
    private static final String DEALERSHIP_HEADER = "dealer|address|phone";
    private static final String VEHICLE_HEADER = "id|year|make|model|type|color|mileage|price|availability";

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DEALERSHIP_FILE))) {
            Dealership dealership = createDealership(reader);
            List<Vehicle> vehicles = getVehicles(reader);
            dealership.setInventory(vehicles);
            return dealership;
        } catch (IOException e) {
            System.err.println("Error loading dealership data from file '" + DEALERSHIP_FILE + "': " + e.getMessage());
            throw new RuntimeException("Error loading dealership data", e);
        }
    }

    private static List<Vehicle> getVehicles(BufferedReader reader) throws IOException {
        String vehicleHeader = reader.readLine();
        if (vehicleHeader == null || !vehicleHeader.equals(VEHICLE_HEADER)) {
            System.err.println("Invalid vehicle header in file '" + DEALERSHIP_FILE + "'");
            return null;
        }
        List<Vehicle> vehicles = new LinkedList<>();
        String vehicleLine;
        while ((vehicleLine = reader.readLine()) != null) {
            String[] vehicleDetails = vehicleLine.split(DELIMITER);
            Vehicle vehicle = new Vehicle(vehicleDetails);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    private static Dealership createDealership(BufferedReader reader) throws IOException {
        String dealershipHeading = reader.readLine();
        if (dealershipHeading == null || !dealershipHeading.equals(DEALERSHIP_HEADER)) {
            System.err.println("Invalid dealership header in file '" + DEALERSHIP_FILE + "'");
            throw new IOException("Invalid dealership header");
        }

        String dealershipInfoLine = reader.readLine();
        String[] dealershipDetails = dealershipInfoLine.split(DELIMITER);
        return new Dealership(dealershipDetails);
    }

    public void saveDealership(Dealership dealership) {
        if (dealership == null) {
            System.err.println("Cannot save a null dealership.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEALERSHIP_FILE))) {
            // Write the dealership header
            writeLineToCsv(writer, DEALERSHIP_HEADER);

            // Write the dealership details
            writeLineToCsv(writer, dealership.getCsvRepresentation(OUTPUT_DELIMITER));

            // Write the header for vehicles
            writeLineToCsv(writer, VEHICLE_HEADER);

            // Write list of vehicles
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writeLineToCsv(writer, vehicle.getCvsRepresentation(OUTPUT_DELIMITER));
            }

            System.out.println("Dealership data saved successfully to " + DEALERSHIP_FILE);
        } catch (IOException e) {
            System.err.println("Error saving dealership data to file '" + DEALERSHIP_FILE + "': " + e.getMessage());
        }
    }

    private static void writeLineToCsv(BufferedWriter writer, String dealership) throws IOException {
        writer.write(dealership);
        writer.newLine();
    }
}