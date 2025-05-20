package com.pluralsight.model;

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
    private String status = "available";

    public Vehicle(String[] csvData) {
        if (csvData == null || csvData.length != 9) {
            throw new IllegalArgumentException("Invalid CSV data for Vehicle: " + (csvData == null ? "null" : String.join(", ", csvData)));
        }
        this.vin = Integer.parseInt(csvData[0].trim());
        this.year = Integer.parseInt(csvData[1].trim());
        this.make = csvData[2].trim();
        this.model = csvData[3].trim();
        this.vehicleType = csvData[4].trim();
        this.color = csvData[5].trim();
        this.odometer = Integer.parseInt(csvData[6].trim());
        this.price = Double.parseDouble(csvData[7].trim());
        this.status = csvData[8].trim();
    }

    @Override
    public String toString() {
        return String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }

    public String toDisplayString() {
        return String.format("VIN: %d, Year: %d, Make: %s, Model: %s, Type: %s, Color: %s, Mileage: %d, Price: $%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }

    public String getCvsRepresentation(String outputDelimiter) {
        //id|year|make|model|type|color|mileage|price|availability
        return String.join(outputDelimiter,
                String.valueOf(vin),
                String.valueOf(year),
                make,
                model,
                vehicleType,
                color,
                String.valueOf(odometer),
                String.format("%.2f", price),
                status);
    }

    public boolean isAvailable() {
        return "available".equalsIgnoreCase(status);
    }
}