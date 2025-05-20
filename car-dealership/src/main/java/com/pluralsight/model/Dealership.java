package com.pluralsight.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public Dealership(String[] dealershipDetails) {
        if (dealershipDetails == null || dealershipDetails.length != 3) {
            throw new IllegalArgumentException("Invalid dealership details: " + (dealershipDetails == null ? "null" : String.join(", ", dealershipDetails)));
        }
        this.name = dealershipDetails[0].trim();
        this.address = dealershipDetails[1].trim();
        this.phone = dealershipDetails[2].trim();
        this.inventory = new LinkedList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        return inventory.stream()
                .filter(v -> v.getPrice() >= minPrice &&
                        v.getPrice() <= maxPrice &&
                        v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) &&
                        v.getModel().equalsIgnoreCase(model) &&
                        v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        return inventory.stream()
                .filter(v -> v.getYear() >= minYear &&
                        v.getYear() <= maxYear
                        && v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color)
                        && v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        return inventory.stream()
                .filter(v -> v.getOdometer() >= minMileage && v.getOdometer() <= maxMileage
                        && v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType)
                        && v.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return List.copyOf(inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            this.inventory.add(vehicle);
        }
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        if (vehicleToRemove != null) {
            inventory.removeIf(v -> v.getVin() == vehicleToRemove.getVin());
        }
    }

    public Vehicle findVehicleByVin(int vin) {
        return inventory.stream()
                .filter(v -> v.getVin() == vin)
                .findFirst()
                .orElse(null);
    }

    public String getCsvRepresentation(String outputDelimiter) {
        //dealer|address|phone;
       return String.join(outputDelimiter,
                name,
                address,
                phone);
    }

    public void changeVehicleStatus(Vehicle vehicle, String s) {
        if (vehicle != null) {
            inventory.stream()
                    .filter(v -> v.getVin() == vehicle.getVin())
                    .findFirst()
                    .ifPresent(v -> v.setStatus(s));
        }
    }
}