package com.pluralsight.dao;

import com.pluralsight.model.contract.Contract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ContractFileManager {
    private static final String CONTRACTS_FILE = "data/contracts.csv";
    private static final String DELIMITER = "|";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    public void saveContract(Contract contract) {
        if (contract == null) {
            System.err.println("Cannot save a null contract.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE, true))) { // true for append mode [cite: 52]
            writer.write(contract.getCsvRepresentation(DELIMITER));
            writer.newLine();
            System.out.println("Contract saved successfully to " + CONTRACTS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving contract to file '" + CONTRACTS_FILE + "': " + e.getMessage());
        }
    }
}