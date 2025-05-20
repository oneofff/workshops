package com.pluralsight.model.contract;

import com.pluralsight.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.pluralsight.dao.ContractFileManager.DATE_FORMATTER;

@Getter
@Setter
public class SalesContract extends Contract {
    private static final double SALES_TAX_RATE = 0.05;
    public static final double RECORDING_FEE = 100.00;
    private static final double LOW_PRICE_PROCESSING_FEE = 295.00;
    private static final double HIGH_PRICE_PROCESSING_FEE = 495.00;
    private static final double HIGH_PRICE_THRESHOLD = 10000.00;

    private boolean financeOption;
    private static final double HIGH_PRICE_LOAN_RATE = 0.0425;
    private static final int HIGH_PRICE_LOAN_TERM = 48;
    private static final double LOW_PRICE_LOAN_RATE = 0.0525;
    private static final int LOW_PRICE_LOAN_TERM = 24;


    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financeOption = financeOption;
    }

    public double getSalesTaxAmount() {
        return getVehicleSold().getPrice() * SALES_TAX_RATE;
    }

    public double getProcessingFee() {
        if (getVehicleSold().getPrice() < HIGH_PRICE_THRESHOLD) {
            return LOW_PRICE_PROCESSING_FEE;
        } else {
            return HIGH_PRICE_PROCESSING_FEE;
        }
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSalesTaxAmount() + RECORDING_FEE + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (!financeOption) {
            return 0.00;
        }

        double principal = getTotalPrice(); //
        double monthlyRate;
        int numberOfPayments;

        if (getVehicleSold().getPrice() >= HIGH_PRICE_THRESHOLD) {
            monthlyRate = HIGH_PRICE_LOAN_RATE / 12;
            numberOfPayments = HIGH_PRICE_LOAN_TERM;
        } else {
            monthlyRate = LOW_PRICE_LOAN_RATE / 12;
            numberOfPayments = LOW_PRICE_LOAN_TERM;
        }

        return principal * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    public String getFinanceOptionString() {
        return financeOption ? "YES" : "NO";
    }

    @Override
    public String getSummary() {
        return String.format("""
                        Sales Contract Summary:
                        Vehicle Price: $%.2f
                        Sales Tax (5%%): $%.2f
                        Recording Fee: $%.2f
                        Processing Fee: $%.2f
                        Total Sales Price: $%.2f
                        Finance Option: %s
                        Monthly Payment: $%.2s""",
                getVehicleSold().getPrice(),
                getSalesTaxAmount(),
                RECORDING_FEE,
                getProcessingFee(),
                getTotalPrice(),
                getFinanceOptionString(),
                (financeOption ? getMonthlyPayment() : "cash (no financing)"));
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
                "SALE",
                commonData,
                String.format("%.2f", getSalesTaxAmount()),
                String.format("%.2f", RECORDING_FEE),
                String.format("%.2f", getProcessingFee()),
                String.format("%.2f", getTotalPrice()),
                getFinanceOptionString(),
                String.format("%.2f", getMonthlyPayment())
        );
    }
}