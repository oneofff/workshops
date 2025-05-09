package com.pluralsight.futurevalue;

import com.pluralsight.futurevalue.domain.CertificateOfDeposit;
import com.pluralsight.futurevalue.ui.CertificateDepositCalculatorConsoleUI;

public class CertificateDepositCalculatorApp {
    public static void main(String[] args) {
        CertificateOfDeposit certificateOfDeposit = CertificateDepositCalculatorConsoleUI.getCertificateDepositInfo();
        certificateOfDeposit.calculateFutureValue();
        certificateOfDeposit.calculateInterestEarned();
        CertificateDepositCalculatorConsoleUI.printCertificateDepositInfo(certificateOfDeposit);
    }
}
