package com.mycompany.quickchatapp;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

// Message class handles message generation, validation, and storage.

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Array to keep track of all sent messages for Part 2's "printMessages" method
    private static String[]sentMessages = new String[100];
    private static int totalMessagesSent = 0;
    
    public Message(String messageID, int messageNumber, String recipient, String messageText){
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    
    // Ensures the message ID is no more than 10 characters
    public boolean checkMessageID(){
        return this.messageID.length()<= 10;
    }
    
    // Ensures the recipient cell number is correctly formatted
    public String checkRecipientCell(){
        if(this.recipient!= null && this.recipient.matches("^\\+27[0-9]{9}$")){
            return "Cell phone number successfully captured.";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    // Creates the Message Hash
    public String createMessageHash(){
        if(messageText == null || messageText.trim().isEmpty())return"";
        
        //Get first two digits of ID
        String firstTwoID = messageID.substring(0,2);
        
        // Get first and last words
        String[]words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Format: 00:0:HITHANKS
        return(firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    // Allows the user to choose what to do with the message
    public String sentMessage(int choice){
        switch(choice){
            case 1 -> {
                sentMessages[totalMessagesSent] = this.messageText;
                totalMessagesSent++;
                return "Message successfully sent.";
            }
            case 2 -> {
                return "Press 0 to delete the message.";
            }
            case 3 -> {
                storeMessage();//calling JSON method
                return "Message successfully stored.";
            }
            default -> {
                return "Invalid choice.";
            }
        }
    }
    
    //Return all sent messages
    public String printMessages(){
        if(totalMessagesSent == 0) return "No messages sent yet.";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < totalMessagesSent; i++){
            sb.append(sentMessages[i]).append("\n");
        }
        return sb.toString();
    }
    
    // Return total messages
    public int returnTotalMessages(){
        return totalMessageSent;
    }
    
    // Store the message in a JSON file
    public void storeMessage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MessageID",this.messageID);
        jsonObject.put("MessageHash",this.messageHash);
        jsonObject.put("Recipient",this.recipient);
        jsonObject.put("Message",this.messageText);
        
        try(FileWriter file = new FileWriter("messages.json",true)){
            file.write(jsonObject.toString()+ "\n");
        }catch(IOException e) {
            System.out.print("Error storing message to JSON file.");
        }
    }
    
    // Getters for testing/displaying
    public String getMessageHash(){return messageHash;}
    public String getMessageID(){return messageID;}
    public String getRecipient(){return recipient;}
    public String getMessageText(){return messageText;}
}
