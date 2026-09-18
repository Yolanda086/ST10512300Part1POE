package com.mycompany.quickchatapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Login class handles the user's registration and authentication part.
/*
  REFERENCES:
  [1] R. C. Martin, Clean Code: A Handbook of Agile Software Craftsmanship. 
      Upper Saddle River, NJ, USA: Prentice Hall, 2008.
  [2] Apache NetBeans, "NetBeans Platform Selection Tutorial Using Maven," 2025.
  
  @author [Andiswa Mkhize] - [ST10512300]
  @version 1.0
 */
public class Login {
    // Fields to store the registered user's credentials and details.
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // Checks if the username contains an underscore and is no more than five characters long.
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    /*
    Checks if the password meets the complexity rules:
    -At least 8 characters long
    -Contains at least one capital letter
    -Contains at least one number
    -Contains at least one special character
    */
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /*
    Checks if the cellphone number is correctly formatted.
    It must contain an international country code (+27) followed by a number and must not exceed ten characters.
    * REFERENCE:
     * The regular expression pattern used in this method was researched and adapted from:
     * Baeldung, "Validate Phone Numbers With Java Regex," Baeldung, 2020. 
     * [Online]. Available: https://www.baeldung.com/java-regex-validate-phone-numbers
    */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Regex: Starts with +27 (South African country code) followed by 9 digits.
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Registers a user by validating all their details.
    public String registerUser(String firstName, String lastName, String username, String password, String cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Store the validated credentials
        this.username = username;
        this.password = password;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // Verifies that the login details match the stored credentials.
    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

    // Returns a message based on the login status.
    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
        } 
    } 
}
