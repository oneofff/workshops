package com.pluralsight;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        return inventory.stream()
                .filter(v -> v.getPrice() >= minPrice && v.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        return inventory.stream()
                .filter(v -> v.getYear() >= minYear && v.getYear() <= maxYear)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(v -> v.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        return inventory.stream()
                .filter(v -> v.getOdometer() >= minMileage && v.getOdometer() <= maxMileage)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return inventory.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(this.inventory);
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
}