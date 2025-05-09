package com.pluralsight.mortgage;

import com.pluralsight.mortgage.domain.MortgageLoan;
import com.pluralsight.mortgage.ui.MortgageCalculatorConsoleUI;

public class MortgageCalculatorApp {
    public static void main(String[] args) {
        MortgageLoan mortgageInfo = MortgageCalculatorConsoleUI.getMortgageInfo();
        mortgageInfo.calculateMonthlyPayment();
        mortgageInfo.calculateTotalInterestPaid();
        MortgageCalculatorConsoleUI.printMortgageInfo(mortgageInfo);
    }
}
