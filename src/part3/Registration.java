/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part3;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.HashSet;

/**
 *
 * @author RC_Student_Lab
 */
class Registration {
      String registeredUser;
    String registeredPassword;
    String registeredPhone;
    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> recipientPhone = new ArrayList<>();
    ArrayList<String> hashId = new ArrayList<>();
    ArrayList<String> uniqueMessageId = new ArrayList<>();
    Random random = new Random();

    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error:Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length");
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=-])(?=.*\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Password successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Cellphone number successfully added");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cellphone number incorrectly formatted or does not contain international code");
            return false;
        }

    }

    public void signUp() {
        String userName;
        String userPassword;
        String userCellNumber;

        do {

            userName = JOptionPane.showInputDialog("Enter your username");
            if (userName == null) {
                return;

            }

            if (!checkUserName(userName)) {
                JOptionPane.showMessageDialog(null, "Invalid username");

            }

        } while (!checkUserName(userName));
        registeredUser = userName;

        do {

            userPassword = JOptionPane.showInputDialog("Enter password");
            if (userPassword == null) {
                return;

            }

            if (!checkPasswordComplexity(userPassword)) {
                JOptionPane.showMessageDialog(null, "Weak Password");

            }

        } while (!checkPasswordComplexity(userPassword));
        registeredPassword = userPassword;
        JOptionPane.showMessageDialog(null, "Valid password, password successfully captured.");

        userCellNumber = JOptionPane.showInputDialog("Enter cellphone");
        if (userCellNumber == null) {
            return;

        }
        while (!checkCellPhoneNumber(userCellNumber)) {
            JOptionPane.showMessageDialog(null, "Invalid cellphone");
            userCellNumber = JOptionPane.showInputDialog("Re-enter Cellphone number");

        }
        registeredPhone = userCellNumber;

    }

    public void signIn() {

        if (registeredUser == null && registeredPassword == null) {
            JOptionPane.showMessageDialog(null, "You need to sign up before you can register");
            return;

        }

        String userName = JOptionPane.showInputDialog("Enter your username to login!");
        String userPassword = JOptionPane.showInputDialog("Enter your password to login!");

        if (userName.equals(registeredUser) && userPassword.equals(registeredPassword)) {
            String[] options = {"Send message", "(Coming soon)", "Exit"};
            int choices = JOptionPane.showOptionDialog(null, "welcome to chatbot", "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);

            
        } else {

            JOptionPane.showMessageDialog(null, "Your credentials are incorrect");

        }
    }

}
