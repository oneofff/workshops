package com.pluralsight.presentvalue.model;

import com.pluralsight.utils.BankBigDecimal;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdinaryAnnuity {
    private BankBigDecimal monthlyPayout;
    private BankBigDecimal expectedInterestRate;
    private Integer termInYears;
    private BankBigDecimal presentValue;

    public BankBigDecimal calculatePresentValue() {
        BankBigDecimal interestRate = this.expectedInterestRate.divide(100).divide(12);
        int numberOfPayments = this.termInYears * 12;
        BankBigDecimal factor = interestRate.add(BigDecimal.ONE).pow(-numberOfPayments);
        BankBigDecimal numerator = BankBigDecimal.ONE.subtract(factor);
        BankBigDecimal denominator = interestRate;
        BankBigDecimal multiply = this.monthlyPayout.multiply(numerator.divide(denominator));
        this.presentValue = multiply;
        return multiply;
    }

    public void setMonthlyPayout(double monthlyPayout) {
        this.monthlyPayout = new BankBigDecimal(monthlyPayout);
    }

    public void setInterestRate(double positiveDouble) {
        this.expectedInterestRate = new BankBigDecimal(positiveDouble);
    }
}
