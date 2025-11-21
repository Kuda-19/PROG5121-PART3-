/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part3;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author RC_Student_Lab
 */
class Messages {
    
    private ArrayList<String> sentMessage = new ArrayList<>();
    private ArrayList<String> storeMessage = new ArrayList<>();
    private ArrayList<String> disregardMessage = new ArrayList<>();
    private ArrayList<String> uniqueMessageId = new ArrayList<>();
    private ArrayList<String> hashId = new ArrayList<>();
    private ArrayList<String> recipientPhone = new ArrayList<>();
    private Random random = new Random();
    
    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$")) {
            
            
        }    
        return true;
    }    
        
    public void sendMessage() {
        String recipientNumber;

        do {
            recipientNumber = JOptionPane.showInputDialog(null, "Enter recipient number");
            if (recipientNumber == null) {
                return;
            }
            if (!checkCellPhoneNumber(recipientNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number");
            }
        } while (!checkCellPhoneNumber(recipientNumber));

        String messageNumber = JOptionPane.showInputDialog("How many messages do you want to send");
        if (messageNumber == null) {
            return;
        }

        try {
            int messageCount = Integer.parseInt(messageNumber);
            for (int i = 0; i < messageCount; i++) {
                String message = JOptionPane.showInputDialog(null,
                        String.format("Enter your message(%d of %d):", i + 1, messageCount));
                if (message == null) {
                    return;
                }

                String[] options = {"Send", "Store", "Disregard"};
                int action = JOptionPane.showOptionDialog(null,
                        "What do you want to do with this message?", "Message action",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);

                String id = checkMessageId();

                switch (action) {
                    case 0 -> {
                        sentMessage.add(message);
                        uniqueMessageId.add(id);
                        int number = sentMessage.size();
                        String hash = createMessageHash(id, number, message);
                        hashId.add(hash);
                        
                        recipientPhone.add(recipientNumber);
                        JOptionPane.showMessageDialog(null,
                                "Message sent successfully!\nMessage ID: " + id
                                + "\nHash: " + hash);
                    }
                    case 1 -> {
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message stored successfully");
                    }
                    case 2 -> {
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded");
                    }
                    default -> {
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }

        printMessages();
        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
        
        boolean continueChat = true;
                while (continueChat) {
                String[] options = {"Send", "Message Management", "Quit"};
                int choice = JOptionPane.showOptionDialog(null, "What would you like to do next?", "Chatbot",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]
        );

        switch (choice) {
            case 0 -> sendMessage();
            case 1 -> messageManagement(); 
            case 2, JOptionPane.CLOSED_OPTION -> continueChat = false;
            default -> continueChat = false;
        }
    }
    }

    public String checkMessageId() {
        String id;
        do {
            int firstNum = 1 + random.nextInt(9);
            int remainingNum = random.nextInt(1_000_000_000);
            id = firstNum + String.format("%09d", remainingNum);
        } while (uniqueMessageId.contains(id));
        return id;
    }

    public String createMessageHash(String messageId, int messageIndex, String message) {
        String idPrefix = messageId.length() >= 2 ? messageId.substring(0, 2) : messageId;
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return String.format("%s:%d:%s%s", idPrefix, messageIndex, firstWord, lastWord).toUpperCase();
    }

    public int returnTotalMessages() {
        return sentMessage.size();
    }

    public void printMessages() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No message was sent");
            return;
        }

        StringBuilder display = new StringBuilder("\n*** SENT MESSAGES ***\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Message: ").append(i + 1).append("\n")
                    .append("Message HASH ID: ").append(hashId.get(i)).append("\n")
                    .append("Message ID: ").append(uniqueMessageId.get(i)).append("\n")
                    .append("Message Content: ").append(sentMessage.get(i)).append("\n")
                    .append("Message Recipient: ").append(recipientPhone.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, display.toString());
        
    }
    //Part 3
     public void messageManagement(){
        String[] options = {"Search Id", "Delete by Hash", "Display all Messages", "Display longest Message"};
        int action = JOptionPane.showOptionDialog(null,
                "Message management",
                "Management",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        
        switch (action) {
            case 0 ->
                searchById();
            case 1 ->
                deletebyHash();
            case 2 ->
                printMessages();
            case 3 ->
                displayLongestMessage();
        }
    }
    
    public void searchById() {
        if (uniqueMessageId.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No unique ID to search");
        }
        
        String uniqueId = JOptionPane.showInputDialog(null,"Enter unique you want to search");
        if (uniqueId == null) {
            return;
        }
        
        int id = uniqueMessageId.indexOf(uniqueId);
        
        if (id == -1) {
            JOptionPane.showMessageDialog(null,"The position is not define");
        }
        
        
        StringBuilder display = new StringBuilder("\n*** MESSAGE DETAILS ***\n");
        
        
        display.append("Message : ").append(id).append(":\n");
        display.append("Message HASHID: ").append(hashId.get(id)).append("\n");
        display.append("Message ID: ").append(uniqueMessageId.get(id)).append("\n");
        display.append("Message content: ").append(sentMessage.get(id)).append("\n");
        display.append("Recipient: ").append(recipientPhone.get(id)).append("\n");
        
        JOptionPane.showMessageDialog(null, display.toString());
        
    }
    
    public void deletebyHash() {
        
        if (hashId.isEmpty ()) {
            JOptionPane.showMessageDialog(null,"No hashId to delete message details");
        }
        
        String hash = JOptionPane.showInputDialog(null, "Enter HashId to delete a message");
        if (hash == null) {
            return;
        }
        
        
        int index = hashId.indexOf(hash);
        if (index != -1) {
            disregardMessage.add(sentMessage.get(index));
            sentMessage.remove(index);
            recipientPhone.remove(index);
            uniqueMessageId.remove(index);
            hashId.remove(index);
            JOptionPane.showMessageDialog(null,
                    "Message successfully deleted!", 
                    null,
                    JOptionPane.DEFAULT_OPTION);
            
        } else {JOptionPane.showMessageDialog(null,
                "No message exit with this hash!", null, JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void displayLongestMessage(){
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No message available.");
            return;
        }
        
        int longestIndex = 0;
        for (int i = 1; i < sentMessage.size(); i++) {
            if (sentMessage.get(i).length() > sentMessage.get(longestIndex).length()){
                longestIndex = i;
                
            }
        }
        
        StringBuilder display = new StringBuilder("\n*** Longest Sent Message ***\n");
        display.append("Message: ").append(sentMessage.get(longestIndex)).append("\n");
        display.append("HASH ID: ").append(hashId.get(longestIndex)).append("\n");
        display.append("Unique ID: ").append(uniqueMessageId.get(longestIndex)).append("\n");
        display.append("Recipient Phone: ").append(recipientPhone.get(longestIndex)).append("\n");
        
        JOptionPane.showMessageDialog(null, display.toString());
        
    }
   
    
    

    
}
