# Financial Calculators Suite

This repository contains a suite of financial calculator applications built with Java. The suite includes calculators for:

- **Mortgage Calculations:** Compute monthly payments and total interest for a mortgage loan.
- **Future Value Calculations:** Determine the future value of an investment using compound interest.
- **Certificate of Deposit Calculations:** Calculate the maturity balance and total interest earned on a certificate of deposit.

All calculators are designed using a layered architecture, emphasizing separation of concerns and modular design. Financial computations are handled with precision using Java’s `BigDecimal` wrapped in a custom helper class, `BankBigDecimal`, to streamline operations and avoid typical floating-point issues.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Package Structure](#package-structure)
- [The BigDecimal Wrapper](#the-bigdecimal-wrapper)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)


---

## Overview

This project provides three key financial calculation tools:
- **Mortgage Calculator:** Computes monthly payments and total interest based on principal, interest rate, and loan term.
- **Future Value Calculator:** Uses compound interest formulas to compute the future value of an investment.
- **Certificate of Deposit Calculator:** Estimates the ending balance and interest earned using daily compounding.

Each application has been organized using a modular, layered approach, ensuring that domain logic, business rules, and user interface are separated and maintainable.

---

## Architecture

The project follows a clear layered architecture:

1. **Domain Layer:**  
   Contains the core business models (e.g., `MortgageLoanDetails`, `FutureValueDetails`, `CertificateOfDeposit`). These classes encapsulate both data and the computation logic directly related to the financial instrument.

2. **User Interface (UI) Layer:**  
   Manages all console-based input and output. UI classes gather user input and display results without embedding business calculations, making it easier to swap out for other interfaces in the future.

3. **Utility Layer:**  
   Provides helper classes such as `ConsoleStringReader` for input validation and the custom `BankBigDecimal` wrapper to simplify precise arithmetic.

---

## Package Structure
```plaintext
The repository is organized as follows:
com.pluralsight
├── futurevalue
│   ├── domain
│   │   └── CertificateOfDeposit
│   └── ui
│       └── CertificateDepositCalculatorConsoleUI
│   ├── CertificateDepositCalculatorApp
├── mortgage
│   ├── domain
│   │   └── MortgageLoan
│   └── ui
│       └── MortgageCalculatorConsoleUI
│   ├── MortgageCalculatorApp
├── presentvalue
│   ├── model
│   │   └── OrdinaryAnnuity
│   └── ui
│       └── OrdinaryAnnuityConsoleUI
│   ├── OrdinaryAnnuityCalculatorApp
└── utils
    ├── BankBigDecimal
    └── ConsoleStringReader

```

---

## The BigDecimal Wrapper

Financial calculations require precision—hence the use of Java’s `BigDecimal`. However, direct usage can be verbose. The custom **BankBigDecimal** wrapper addresses this by:
- Simplifying common arithmetic operations (addition, subtraction, multiplication, division).
- Standardizing scale, precision, and rounding policies throughout the project.
- Improving code readability by reducing boilerplate code.
  
---

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- **Java JDK 8** or higher
- **Maven**
- **Git**

### Installation

1. **Clone the Repository**

   Open a terminal and run:

   ```bash
   git clone https://github.com/oneofff/financial-calculators.git
   cd financial-calculators
   ```
   Build the Project
   ```bash
   mvn clean install
   ```
This command will compile the project and run any tests that are configured.

Run the Application
Each calculator has its own entry point. For example, to launch the Certificate of Deposit Calculator, use:
  ```bash
java -cp target/financial-calculators-1.0-SNAPSHOT.jar com.pluralsight.futurevalue.CertificateDepositCalculatorApp
 ```

---

### Usage
After installation, each calculator can be run individually. Here is an example interaction for the Certificate of Deposit Calculator:
```yaml
Please enter the deposit:
15000
Please enter the interest rate:
5
Please enter the number of years:
15
If you deposit $15000.00 in a CD that earns 5.00% interest and matures in 15 years, your CD's ending balance will be $31753.32 and you would have earned $16753.32 in interest
```
