package com.pluralsight.presentvalue;

import com.pluralsight.presentvalue.model.OrdinaryAnnuity;
import com.pluralsight.presentvalue.ui.OrdinaryAnnuityConsoleUI;

public class OrdinaryAnnuityCalculatorApp {
    public static void main(String[] args) {
        OrdinaryAnnuity ordinaryAnnuity = OrdinaryAnnuityConsoleUI.getOrdinaryAnnuity();
        ordinaryAnnuity.calculatePresentValue();
        OrdinaryAnnuityConsoleUI.printOrdinaryAnnuityInfo(ordinaryAnnuity);
    }
}
