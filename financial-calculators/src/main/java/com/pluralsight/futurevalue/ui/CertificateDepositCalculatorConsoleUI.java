package com.pluralsight.futurevalue.ui;

import com.pluralsight.futurevalue.domain.CertificateOfDeposit;
import com.pluralsight.utils.ConsoleStringReader;

public class CertificateDepositCalculatorConsoleUI {


    public static CertificateOfDeposit getCertificateDepositInfo() {
        CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit();
        System.out.println("Please enter the deposit:");
        certificateOfDeposit.setDeposit(ConsoleStringReader.getPositiveDouble());
        System.out.println("Please enter the interest rate:");
        certificateOfDeposit.setAnnualInterestRate(ConsoleStringReader.getPositiveDouble());
        System.out.println("Please enter the number of years:");
        certificateOfDeposit.setTermInYears(ConsoleStringReader.getPositiveInt());
        return certificateOfDeposit;
    }

    public static void printCertificateDepositInfo(CertificateOfDeposit certificateOfDeposit) {
        System.out.printf("If you deposit $%.2f in a CD that earns %.2f%% interest and matures in %d years," +
                        " your CD's ending balance will be $%.2f and you would have earned $%.2f in interest%n",
                certificateOfDeposit.getDeposit().getDoubleValue(),
                certificateOfDeposit.getAnnualInterestRate().getDoubleValue(),
                certificateOfDeposit.getTermInYears(),
                certificateOfDeposit.calculateFutureValue().getDoubleValue(),
                certificateOfDeposit.calculateInterestEarned().getDoubleValue());
    }

}
