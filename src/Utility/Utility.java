/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import shop.Shop;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author User
 */
public class Utility {

    //retunrs true if the tab is added in the tabbed pane else false
    static JFrame frame;
    public static boolean isYes = false;
    public static String value = new String();
    public static boolean wantPerson = false;
    public static boolean isYes1 = false;
    public static String value1 = new String();
    public static boolean wantFolio = false;

    public static boolean isExists(JTabbedPane tp, String name) {
        for (int i = 0; i < tp.getComponentCount(); i++) {

            if (tp.getTitleAt(i).equals(name)) {
                tp.setSelectedIndex(i);
                return false;
            }
        }
        return true;
    }

    public static void shhowDialog(JPanel panel, String title, Dimension dim) {
        JDialog insertFolioDialog = new JDialog(new Frame(), title, true);

        insertFolioDialog.setSize(dim);
        System.out.println(dim.height);
        insertFolioDialog.setLayout(new BorderLayout());
        insertFolioDialog.add(panel, BorderLayout.CENTER);
        insertFolioDialog.show();
    }

    public static Point getWindowPosition(Dimension dim, Toolkit env) {
        Point p = new Point();
        Dimension envDim = env.getScreenSize();
        p.y = (envDim.height / 2) - (dim.height / 2);
        p.x = (envDim.width / 2) - (dim.width / 2);

        return p;
    }

    public static String getOracleDate(Date date) {
        String[] month = {"Jan", "Feb", "Mar", "Arp", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
            "Nov", "Dec"
        };

        int day = date.getDate();
        int mon = date.getMonth();
        int year = date.getYear() + 1900;
        String datee = day + "-" + month[mon] + "-" + year;
        return datee;
    }

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utility.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void showPictureFromSystem(final String url, JPanel picPanel) {
        JLabel photoLabel = new JLabel(/*whatever*/) {
            @Override
            public void paintComponent(Graphics g) {
                ImageIcon photo = null;
                try {
                    File file = new File(url);
                    BufferedImage bi = ImageIO.read(file);
                    photo = new ImageIcon(bi);
                    super.paintComponent(g);
                    g.drawImage(photo.getImage(), 0, 0, getWidth(), getHeight(), null);
                } catch (OutOfMemoryError oo) {
                    Message.error1(this, "Please select a small size image");
                } catch (IOException ex) {
                    Message.error1(this, ex.getMessage());
                } catch (Exception ex) {
                    Message.error1(this, ex.getMessage());
                }

            }
        };

        if (picPanel.getComponentCount() > 0) {
            picPanel.removeAll();
        }
        picPanel.add(photoLabel, BorderLayout.CENTER);
        picPanel.validate();
    }

    public static void showPictureFromResource(final String url, JPanel picPanel) {
        JLabel photoLabel = new JLabel(/*whatever*/) {
            @Override
            public void paintComponent(Graphics g) {
                ImageIcon photo = null;
                photo = Utility.createImageIcon(url);
                super.paintComponent(g);
                g.drawImage(photo.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        if (picPanel.getComponentCount() > 0) {
            picPanel.removeAll();
        }
        picPanel.add(photoLabel, BorderLayout.CENTER);
        picPanel.validate();

    }

    public static String formateString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static class User {

        public User() {
        }
    }

    /*  public static String addPerson(String title){
     isYes = false;
     wantPerson = true;
     CenterDialog cd = new CenterDialog(Main.home, true, title);
     cd.showDialog(new SearchPanel(cd,"person",true));

     //Message.error1(new JButton(),"hihi***1256  " + value);
     if(!isYes)
     return null;
     isYes = false;
     wantPerson = false;
        
     return value;

     }

     public static String addFolio(String title) {
     isYes1 = false;
     wantFolio = true;
     CenterDialog cd = new CenterDialog(Main.home, true, title);
     cd.showDialog(new SearchPanel(cd,"individual folio",true));


     if(!isYes1)
     return null;
     isYes1 = false;
     wantFolio = false;
     return value1;
     }

     public static void ReplacePanel(JPanel parent, JPanel panel){

     parent.removeAll();
     parent.setLayout(new BorderLayout(0,0));
     parent.add("Center",panel);
     parent.repaint();

     }
     */
}
