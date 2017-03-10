/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;

/**
 *
 * @author Porag
 */
public class PButton extends JButton{
    
    String P = new String();
    
    public PButton(){
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sLabelMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sLabelMouseExited(evt);
            }
            
                                           

            private void sLabelMouseEntered(java.awt.event.MouseEvent evt) {                                    
              // TODO add your handling code here:
                P = getText();
                setForeground(Color.blue);
                
                setText("<html><h2><i>"+P+"</h2></i></html>");
              
                
            }                                   

            private void sLabelMouseExited(java.awt.event.MouseEvent evt) {                                   
               // TODO add your handling code here:
                setForeground(Color.black);
                setText(P);
            }

                       

            
        });
        
        
    }
    
   public static void main(String[] args) {
        new PButton();
    } 
    
}