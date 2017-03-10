/*
 * Message.java
 * 
 * Created on Feb 26, 2008, 12:01:07 AM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Saikat
 */
import javax.swing.JOptionPane;
import java.awt.*;

public class Message {

    public Message() {
    }

    public static void error1(Component c, String message) {   /*if(message.startsWith("ORA-01017"))
         message = "Invalid username - password combination";*/
        if (!message.startsWith("Listener refused")) {
            JOptionPane.showMessageDialog(c, message, "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void success1(Component c, String message) {
        JOptionPane.showMessageDialog(c, message, "Successful", JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(c, message,"Successful", JOptionPane.OK_OPTION, JOptionPane. );
    }

    /**
     *
     * @param c
     * @param message
     */
    public static void message(Component c, String message) {
        JOptionPane.showMessageDialog(c, message, "Message", JOptionPane.OK_OPTION);
    }
}
