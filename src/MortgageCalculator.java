import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        final double PERCENT = 100.0; // use a constant variable to convert interest rates from percentages to fractions.

        Scanner scanner = new Scanner(System.in); // create a scanner object to read user input from the console.

        boolean continueCalculation = true; // variable to track if the user wants to continue with another calculation

        while (continueCalculation) {
            double principalAmount; // declare a variable to store the principal amount
            double annualInterestRate; // declare a variable to store the annual interest rate
            int years; // declare a variable to store the number of years for the mortgage

            // Validate principal amount
            do {
                System.out.println("Please enter the principal amount:");
                while (!scanner.hasNextDouble()) { // check if the input is a valid double
                    System.out.println("Invalid input. Please enter a valid principal amount:");
                    scanner.next(); // consume invalid input
                }
                principalAmount = scanner.nextDouble(); // store the valid principal amount
            } while (principalAmount <= 0); // continue looping until a valid principal amount is entered

            // Validate annual interest rate
            do {
                System.out.println("Please enter the annual interest rate (e.g., 5 for 5%):");
                while (!scanner.hasNextDouble()) { // check if the input is a valid double
                    System.out.println("Invalid input. Please enter a valid annual interest rate:");
                    scanner.next(); // consume invalid input
                }
                annualInterestRate = scanner.nextDouble(); // store the valid annual interest rate
            } while (annualInterestRate <= 0); // continue looping until a valid annual interest rate is entered

            // Validate period in years
            do {
                System.out.println("Please enter the period in years:");
                while (!scanner.hasNextInt()) { // check if the input is a valid integer
                    System.out.println("Invalid input. Please enter a valid period in years:");
                    scanner.next(); // consume invalid input
                }
                years = scanner.nextInt(); // store the valid number of years
            } while (years <= 0); // continue looping until a valid number of years is entered

            // Calculate total number of payments
            int numberOfPayments = years * 12; // 12 because a year has 12 months

            // Header for the repayment schedule
            System.out.println("\nMonthly Mortgage Payment Schedule:");
            System.out.println("Month\tPayment");

            double monthlyInterestRate = annualInterestRate / PERCENT / 12; // Calculate monthly interest rate
            double remainingBalance = principalAmount; // remainingBalance is the amount remaining to be repaid
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(); // create a NumberFormat object for currency formatting
            currencyFormatter.setMinimumFractionDigits(2); // set the minimum number of fractional digits to 2

            for (int month = 1; month <= numberOfPayments; month++) {
                double monthlyPayment = principalAmount
                        * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                        / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
                remainingBalance -= monthlyPayment - (remainingBalance * monthlyInterestRate);

                String formattedPayment = currencyFormatter.format(monthlyPayment); // format the monthly payment as currency
                System.out.println(month + "\t" + formattedPayment); // display the month and the formatted monthly payment
            }

            // Ask the user if they want to continue with another calculation
            System.out.println("Do you want to perform another calculation? (yes/no)");
            String userInput = scanner.next();
            continueCalculation = userInput.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for using the Mortgage Calculator. Goodbye!");
    }
}
