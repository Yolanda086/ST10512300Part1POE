package com.mycompany.quickchatapp;

import java.util.Scanner;
import java.util.Random;

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
       
       // Part 1: Registration and Login
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
        
        // Part 2: Menu
        System.out.println("\nWelcome to QuickChat.");
        boolean running = true;
        while(running){
            System.out.println("\nMenu:");
            System.out.println("1)Send Messages");
            System.out.println("2)Show recently sent messages");
            System.out.println("3)Quit");
            System.out.print("Choose an option:");
            
            String choice = scanner.nextLine();
            
            switch(choice){
                case "1":
                    sendMessages(scanner);
                    break;
                case "2":
                    System.out.println("Coming Soon");
                    break;
                case "3":
                    System.out.println("Quiting QuickChat.Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1,2 or 3.");
            }
        }
        
        // Close the scanner
        scanner.close();
    }
    
    // Helper method for handling message sending
    private static void sendMessages(Scanner scanner){
        System.out.print("How many messages do you want to send?");
        int numMessages;
        try{
            numMessages = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Invalid number.");
            return;
        }
        
        Random random = new Random();
        
        for(int i = 0; i< numMessages; i++){
            System.out.println("\n---Message" + (i + 1) + "---");
            
            // 1.Random 10 digit ID
            long randomID = 1000000000L + (long)(random.nextDouble()* 9000000000L);
            String messageID = String.valueOf(randomID);
            
            // 2.Get Recipient
            System.out.print("Enter recipient cell number (e.g., +27838968976):");
            String recipient = scanner.nextLine();
            
            // 3. Get message text
            System.out.print("Enter message(max 250 characters):");
            
            if(messageText.length()> 250){
                System.out.println("Please enter a message of less than 250 characters.");
                i--; // Repeats
                continue;
            }
            
            Message msg = new Message(messageID,i,recipient,messageText);
            
            // Validate Recipient using Message class method
            String recipientCheck = msg.checkRecipientCell();
            if(!recipientCheck.equals("Cell phone number successfully captured.")){
                System.out.println("recipientCheck");
                i--; // Repeats message entry
                continue;
            }
            
            // 4. Choose Action
            System.out.println("Choose an option:");
            System.out.println("1)Send Message");
            System.out.println("2)Disregard Message");
            System.out.println("3)Store Message to send later");
            System.out.print("Choice:");
            int action = Integer.parseInt(scanner.nextLine());
            
            String result = msg.sentMessage(action);
            System.out.println("result");
            
            // 5. Display message details if sent or stored
            if (action == 1|| action == 3){
                System.out.println("\nMessage ID:" + msg.getMessageID());
                System.out.println("Message Hash:" + msg.getMessageHash());
                System.out.println("Recipient:" + msg.getRecipient());
                System.out.println("Message:" + msg.getMessageText());
            }
        }
        
        // Show total messages sent
        System.out.println("\nTotal messages sent so far:" + new Message("0",0,"0").returnTotalMessages());
    }
}
