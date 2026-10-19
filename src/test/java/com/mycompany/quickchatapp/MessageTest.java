package com.mycompany.quickchatapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    private Message message;

    @Before
    public void setUp() {
        // Test Data from POE: Recipient +27718693002, Message "Hi Mike, can you join us for dinner tonight?"
        message = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
    }

    @After
    public void tearDown() {
        message = null;
    }

    @Test
    public void testCheckMessageID_Valid() {
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, message.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        Message invalidMsg = new Message("0012345678", 0, "08575975889", "Test");
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, invalidMsg.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        // Expected format for the first test case: 00:0:HITHANKS
        String expected = "00:0:HITONIGHT";
        assertEquals(expected, message.getMessageHash());
    }

    @Test
    public void testMessageLength_Success() {
        String testText = "Short message";
        assertTrue(testText.length() <= 250);
    }
    
    @Test
    public void testSentMessage_Send() {
        assertEquals("Message successfully sent.", message.sentMessage(1));
    }

    @Test
    public void testSentMessage_Disregard() {
        assertEquals("Press 0 to delete the message.", message.sentMessage(2));
    }

    @Test
    public void testSentMessage_Store() {
        assertEquals("Message successfully stored.", message.sentMessage(3));
    }
}
