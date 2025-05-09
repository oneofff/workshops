package com.pluralsight.mortgage.domain;

import com.pluralsight.utils.BankBigDecimal;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MortgageLoan {
    private BankBigDecimal principal;
    private BankBigDecimal annualInterestRate;
    private Integer loanLength;
    private BankBigDecimal monthlyPayment;
    private BankBigDecimal totalInterestPaid;


    public BankBigDecimal calculateMonthlyPayment() {
        BankBigDecimal monthlyInterestRate = this.annualInterestRate.divide(100).divide(12);
        int numberOfPayments = this.loanLength * 12;

        BankBigDecimal factor = monthlyInterestRate.add(BigDecimal.ONE).pow(numberOfPayments);
        BankBigDecimal numerator = monthlyInterestRate.multiply(factor);
        BankBigDecimal denominator = factor.subtract(BigDecimal.ONE);
        BankBigDecimal monthlyPayment = this.principal.multiply(numerator.divide(denominator));
        this.setMonthlyPayment(monthlyPayment);
        return monthlyPayment;
    }

    public BankBigDecimal calculateTotalInterestPaid() {
        int numberOfPayments = this.loanLength * 12;

        if (this.monthlyPayment == null)
            this.calculateMonthlyPayment();

        BankBigDecimal totalInterestPaid = this.monthlyPayment.multiply(numberOfPayments).subtract(this.principal);
        this.setTotalInterestPaid(totalInterestPaid);
        return totalInterestPaid;
    }


    public void setPrincipal(double principal) {
        this.principal = new BankBigDecimal(principal);
    }


    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = new BankBigDecimal(annualInterestRate);
    }

    public void setLoanLength(int loanLength) {
        this.loanLength = loanLength;
    }
}
