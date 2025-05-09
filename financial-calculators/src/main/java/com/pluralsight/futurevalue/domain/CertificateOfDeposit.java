package com.pluralsight.futurevalue.domain;

import com.pluralsight.utils.BankBigDecimal;
import lombok.Data;

@Data
public class CertificateOfDeposit {
    private BankBigDecimal deposit;
    private BankBigDecimal annualInterestRate;
    private Integer termInYears;
    private BankBigDecimal futureValue;
    private BankBigDecimal interestEarned;

    public void setDeposit(double deposit) {
        this.deposit = new BankBigDecimal(deposit);
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = new BankBigDecimal(annualInterestRate);
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public BankBigDecimal calculateFutureValue() {
        BankBigDecimal interestRate = this.annualInterestRate.divide(100);
        int exponent = this.termInYears * 365;
        BankBigDecimal base = BankBigDecimal.ONE.add(interestRate.divide(365));
        BankBigDecimal futureValue = this.deposit.multiply(base.pow(exponent));
        this.setFutureValue(futureValue);
        return futureValue;
    }

    public BankBigDecimal calculateInterestEarned() {
        if (this.futureValue == null) {
            this.futureValue = this.calculateFutureValue();
        }
        BankBigDecimal interestEarned = this.calculateFutureValue().subtract(this.deposit);
        this.setInterestEarned(interestEarned);
        return interestEarned;
    }
}
