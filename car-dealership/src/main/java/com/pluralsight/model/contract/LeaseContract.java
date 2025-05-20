package com.pluralsight.model.contract;

import com.pluralsight.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.pluralsight.dao.ContractFileManager.DATE_FORMATTER;

@Getter
@Setter
public class LeaseContract extends Contract {
    private static final double EXPECTED_ENDING_VALUE_RATE = 0.50;
    private static final double LEASE_FEE_RATE = 0.07;

    private static final double LEASE_INTEREST_RATE = 0.04;
    private static final int LEASE_TERM_MONTHS = 36;

    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * EXPECTED_ENDING_VALUE_RATE;
    }

    public double getLeaseFee() {
        return getVehicleSold().getPrice() * LEASE_FEE_RATE;
    }

    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() - getExpectedEndingValue()) + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double principalToFinance = getTotalPrice();
        double monthlyRate = LEASE_INTEREST_RATE / 12;
        int numberOfPayments = LEASE_TERM_MONTHS;

        return principalToFinance * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    @Override
    public String getSummary() {
        return String.format("""
                        Lease Contract Summary:
                        Vehicle Price: $%.2f
                        Expected Ending Value (50%%): $%.2f
                        Lease Fee (7%%): $%.2f
                        Total Lease Cost: $%.2f
                        Monthly Payment: $%.2f""",
                getVehicleSold().getPrice(),
                getExpectedEndingValue(),
                getLeaseFee(),
                getTotalPrice(),
                getMonthlyPayment());
    }

    @Override
    public String getCsvRepresentation(String delimiter) {
        String commonData = String.join(delimiter,
                getDate().format(DATE_FORMATTER),
                getCustomerName(),
                getCustomerEmail(),
                String.valueOf(getVehicleSold().getVin()),
                String.valueOf(getVehicleSold().getYear()),
                getVehicleSold().getMake(),
                getVehicleSold().getModel(),
                getVehicleSold().getVehicleType(),
                getVehicleSold().getColor(),
                String.valueOf(getVehicleSold().getOdometer()),
                String.format("%.2f", getVehicleSold().getPrice())
        );
        return String.join(delimiter,
                "LEASE",
                commonData,
                String.format("%.2f", getExpectedEndingValue()),
                String.format("%.2f", getLeaseFee()),
                String.format("%.2f", getTotalPrice()),
                String.format("%.2f", getMonthlyPayment())
        );
    }
}