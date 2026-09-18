package com.mycompany.quickchatapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
  Unit tests for the Login class using JUnit 4.
  
  REFERENCE:
  JUnit Team, "JUnit 4 Documentation," JUnit, 2023. 
  [Online]. Available: https://junit.org/junit4/
  
  @author [Andiswa Mkhize] - [ST10512300]
 */

public class LoginTest {
    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    @After
    public void tearDown() {
        login = null;
    }

    // ========== USERNAME TESTS ==========
    @Test
    // Verify valid username format passes
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test 
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // ========== PASSWORD TESTS ==========
    @Test 
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test 
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ========== CELL PHONE TESTS ==========
    @Test 
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test 
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ========== LOGIN TESTS ==========
    @Test 
    public void testLoginUser_Success() { 
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test 
    public void testLoginUser_Failure() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "WrongPass1!"));
    }

    // ========== REGISTRATION MESSAGE TESTS ==========
    @Test 
    public void testRegisterUser_InvalidUsernameMessage() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = login.registerUser("A", "B", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expected, result);
    }

    @Test 
    public void testRegisterUser_InvalidPasswordMessage() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String result = login.registerUser("A", "B", "kyl_1", "password", "+27838968976");
        assertEquals(expected, result);
    }

    // ========== LOGIN STATUS MESSAGE TESTS ==========
    @Test 
    public void testReturnLoginStatus_Success() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean ok = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String expected = "Welcome Kyle Smith, it is great to see you again.";
        assertEquals(expected, login.returnLoginStatus(ok));
    }

    @Test 
    public void testReturnLoginStatus_Failure() {
        boolean ok = login.loginUser("wrong_user", "WrongPass1!");
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus(ok));
    }
}