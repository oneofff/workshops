package com.pluralsight.mortgage.ui;

import com.pluralsight.mortgage.domain.MortgageLoan;
import com.pluralsight.utils.ConsoleStringReader;

public class MortgageCalculatorConsoleUI {

    public static MortgageLoan getMortgageInfo() {
        MortgageLoan mortgageLoan = new MortgageLoan();
        System.out.println("Please enter the principal:");
        mortgageLoan.setPrincipal(ConsoleStringReader.getPositiveDouble());
        System.out.println("Please enter the interest rate:");
        mortgageLoan.setAnnualInterestRate(ConsoleStringReader.getPositiveDouble());
        System.out.println("Please enter the loan length in years:");
        mortgageLoan.setLoanLength(ConsoleStringReader.getPositiveInt());
        return mortgageLoan;
    }

    public static void printMortgageInfo(MortgageLoan mortgageLoan) {
        System.out.printf("A $%.2f loan at %.3f%% interest for %d years would have a $%.2f/mo payment with a total interest of $%.2f%n",
                mortgageLoan.getPrincipal().getDoubleValue(),
                mortgageLoan.getAnnualInterestRate().getDoubleValue(),
                mortgageLoan.getLoanLength(),
                mortgageLoan.getMonthlyPayment().getDoubleValue(),
                mortgageLoan.calculateTotalInterestPaid().getDoubleValue());
    }

}
