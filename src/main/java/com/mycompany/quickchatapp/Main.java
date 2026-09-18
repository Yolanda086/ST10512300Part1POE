package com.mycompany.quickchatapp;

import java.util.Scanner;

/*
  The Main class is the entry point for the QuickChat application.
  
  REFERENCE:
  Apache Maven Project, "Apache Maven," Apache Maven, 2024. 
  [Online]. Available: https://maven.apache.org/
  
  @author [Andiswa Mkhize] - [ST10512300]
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("===Registration===");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
        String cellPhone = scanner.nextLine();
        
        // Line to create space
        System.out.println();

        // 1. Call the methods first and display individual messages
        boolean usernameValid = login.checkUserName(username);
        boolean passwordValid = login.checkPasswordComplexity(password);
        boolean cellValid = login.checkCellPhoneNumber(cellPhone);

        if (usernameValid) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        if (passwordValid) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        if (cellValid) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }
        
        // 2. Only proceed if ALL validations passed
        if (usernameValid && passwordValid && cellValid) { 
            login.registerUser(firstName, lastName, username, password, cellPhone);

            System.out.println("\n===Login===");
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        } else {
            System.out.println("Registration failed. Please restart the application and try again.");
        }

        scanner.close();
    }
}
