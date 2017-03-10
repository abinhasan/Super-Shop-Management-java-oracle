/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jvnet.substance.skin.*;

/**
 *
 * @author Porag
 */
public class Shop {

    //-----------------------------------
    public static int porag = 0;
    //------------------------------------
    public static Home home;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //System.out.println("Substance Raven Graphite failed to initialize");
        }



        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new LoginJDialog().setVisible(true);
            }
        });
    }

    public static void saveFile(Home c) {

        JFileChooser chooser;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Back Up", "txt", "backup");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showSaveDialog(c) == JFileChooser.APPROVE_OPTION) {



            try {
                String fName = chooser.getSelectedFile().toString();
                if (!fName.endsWith(".txt")) {
                    fName = fName + ".txt";

                }
                BufferedWriter br = new BufferedWriter(new FileWriter(fName));
                br.write(porag + "Porag");
                br.newLine();


                br.write("Mona");
                br.newLine();

                br.write("END");


                br.close();
                JOptionPane.showMessageDialog(new JButton(), "Back Up saved successfully!", "Success!", JOptionPane.PLAIN_MESSAGE);
                return;



            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JButton(), "Error in File", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else {
            return;
        }


    }
}