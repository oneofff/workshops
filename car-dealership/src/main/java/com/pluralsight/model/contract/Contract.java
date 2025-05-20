package com.pluralsight.model.contract;

import com.pluralsight.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Contract {
    private LocalDate date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
    public abstract String getSummary();

    public abstract String getCsvRepresentation(String delimiter);
}