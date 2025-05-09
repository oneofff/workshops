package com.pluralsight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    @Override
    public String toString() {
        return String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }

    public String toDisplayString() {
        return String.format("VIN: %d, Year: %d, Make: %s, Model: %s, Type: %s, Color: %s, Mileage: %d, Price: $%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }
}