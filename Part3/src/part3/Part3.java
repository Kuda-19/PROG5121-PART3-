/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package part3;

/**
 *
 * @author RC_Student_Lab
 */
public class Part3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Registration obj = new Registration();
        Messages messageHandler = new Messages();
        
        obj.signUp();
        obj.signIn();
        messageHandler.sendMessage();
    }
    
}
