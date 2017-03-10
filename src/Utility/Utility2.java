/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import javax.swing.text.MaskFormatter;
import java.sql.*;
import javax.swing.*;


import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/*import java.util.Map;

 import net.sf.jasperreports.engine.JRException;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import net.sf.jasperreports.engine.design.JasperDesign;
 import net.sf.jasperreports.engine.xml.JRXmlLoader;
 import net.sf.jasperreports.view.JasperViewer;

 import javax.swing.JFrame;
 import javax.swing.JRootPane;*/

/**
 *
 * @author User
 */
public class Utility2 {

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Utility2.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
        }
        return formatter;
    }

    public static Object[] getSingleRowQueryObjects(String sql, JComponent btn) {
        Object obj[] = null;

        try {

            SqlOperation sop = new SqlOperation();
            System.out.println("sql: " + sql);
            ResultSet rs = sop.getQueryResult(btn, sql);
            obj = new Object[rs.getMetaData().getColumnCount()];
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    obj[i] = "" + rs.getString(i + 1);

                }
            }
        } catch (SQLException ex) {
            Message.error1(btn, ex.getMessage());
        } catch (Exception ex) {
            Message.error1(btn, ex.getMessage());
        }
        return obj;
    }

    public static Object[] getQueryObjects(ResultSet rs, JComponent btn, int rowCount) {
        Object obj[] = new Object[rowCount];
        int i = 0;
        try {
            while (rs.next()) {
                obj[i++] = rs.getString(1);

            }
        } catch (SQLException ex) {
            Message.error1(btn, ex.getMessage());
        }
        return obj;
    }

    public static Object[][] get2DQueryObjects(ResultSet rs, JComponent btn, int rowCount) {
        Object obj[][] = null;

        int i = 0;
        try {
            obj = new Object[rowCount][rs.getMetaData().getColumnCount()];
            //System.out.println(rs.getMetaData().getColumnCount());
            while (rs.next()) {
                for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
                    obj[i][j] = "" + rs.getString(j + 1);
                    // System.out.println(rs.getString(j + 1));

                }
                i++;
            }
        } catch (SQLException ex) {
            Message.error1(btn, ex.getMessage());
            ex.printStackTrace();
        }
        return obj;
    }

    public static Object[][] getMultiRowQueryObjects(String sql, JComponent btn, int rowCount) {
        Object obj[][] = null;

        int i = 0;
        try {

            SqlOperation sop = new SqlOperation();
            System.out.println("sql: " + sql);
            ResultSet rs = sop.getQueryResult(btn, sql);
            obj = new Object[rowCount][rs.getMetaData().getColumnCount()];
            //System.out.println(rs.getMetaData().getColumnCount());            
            while (rs.next()) {
                for (int j = 0; j < rs.getMetaData().getColumnCount(); j++) {
                    obj[i][j] = "" + rs.getString(j + 1);
                    //System.out.println(rs.getString(j + 1));

                }
                i++;
            }
        } catch (SQLException ex) {
            Message.error1(btn, ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            Message.error1(btn, ex.getMessage());
            ex.printStackTrace();
        }
        return obj;
    }

    public static void insertUpdateQuery(String sql, JComponent btn, int isInsert) {
        System.out.println(sql);
        try {
            SqlOperation sop = new SqlOperation();
            Connection con = sop.getConnection();
            Statement stm = con.createStatement();
            stm.executeUpdate(sql);

            /*if (isInsert == 1) {
             Message.success1(btn, "Data inserted");
             } else if (isInsert == 2) {
             Message.success1(btn, "Data updated");
             } else if (isInsert == 3) {
             Message.success1(btn, "Data deleted");
             }*/

            //con.close();
        } catch (SQLException se) {
            Message.error1(new JButton(), se.getMessage());
        } catch (Exception e) {
            Message.error1(new JButton(), e.getMessage());
        }
    }

    public static int countQuery(Component btn, String sql) {
        int count = 0;
        try {
            SqlOperation sop = new SqlOperation();
            System.out.println("sql: " + sql);
            ResultSet rs = sop.getQueryResult(btn, sql);

            while (rs.next()) {
                count = Integer.parseInt(rs.getString(1));
            }
            //sop.closeConnection();
        } catch (SQLException sqe) {
            Message.error1(btn, sqe.getMessage());
        } catch (Exception e) {
            Message.error1(btn, e.getMessage());
        }

        return count;
    }

    public static String singleRowQuery(Component btn, String sql) {
        int count = 0;
        String value = "";
        try {
            SqlOperation sop = new SqlOperation();

            System.out.println("sql: " + sql);
            ResultSet rs = sop.getQueryResult(btn, sql);

            while (rs.next()) {

                value = rs.getString(1);
                System.out.println(value);
            }
            //sop.closeConnection();
        } catch (SQLException sqe) {
            Message.error1(btn, sqe.getMessage());
        } catch (Exception e) {
            Message.error1(btn, e.getMessage());
        }

        return value;
    }

    public static String singleStOPQuery(JButton btn, String sql) {
        String st = new String();
        System.out.println("sql: " + sql);
        try {
            SqlOperation sop = new SqlOperation();

            ResultSet rs = sop.getQueryResult(btn, sql);

            while (rs.next()) {
                st = "" + rs.getString(1);
            }

        } catch (SQLException sqe) {
            Message.error1(btn, sqe.getMessage());
            return null;
        } catch (Exception e) {
            Message.error1(btn, e.getMessage());
        }

        return st;
    }

    public static void main(String[] args) {
        //setCombo("Morning:Day", new JComboBox());
        String query4 = "select * from subject_marks where sub_id = 'Gen-09001-01'";

        Object[] data = Utility2.getSingleRowQueryObjects(query4, new JButton());
        System.out.println(data[1]);
    }

    public static String joinString(JComboBox cmb) {

        String str = "";
        if (cmb.getModel().getSize() < 1) {
            return str;
        }
        for (int i = 0; i < cmb.getModel().getSize(); i++) {
            if (i == cmb.getModel().getSize() - 1) {
                str = str + cmb.getItemAt(i);
            } else {
                str = str + cmb.getItemAt(i) + ":";
            }
        }
        return str;
    }

    public static void setCombo(String str, JComboBox cmb) {
        try {
            if (!str.equals("")) {
                String[] ary = str.split(":");
                cmb.setModel(new DefaultComboBoxModel(ary));
                cmb.setEnabled(true);
            } else {
                cmb.setModel(new DefaultComboBoxModel());
                cmb.setEnabled(false);
            }
        } catch (NullPointerException ne) {
            cmb.setModel(new DefaultComboBoxModel());
            cmb.setEnabled(false);
        }
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

    public static void combo_setSelectedItem(String str, JComboBox cmb) {

        for (int i = 0; i < cmb.getModel().getSize(); i++) {
            String val = cmb.getItemAt(i).toString();
            if (val.equals(str)) {
                cmb.setSelectedIndex(i);
                break;
            }
        }
    }
    /*   
     public static void viewReports(Map params, String reportFile,String title){
     try{
     JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
           
     JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
     //JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
     SqlOperation sop = new SqlOperation();
     Connection jdbcConnection = sop.getConnection();
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jdbcConnection);
           
     JasperViewer viewer = new JasperViewer(jasperPrint);
     viewer.setDefaultCloseOperation(JasperViewer.HIDE_ON_CLOSE);
     JRootPane pane = (JRootPane) viewer.getComponent(0);
     viewer.dispose();
           
     JFrame frame = new JFrame(title);
     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     frame.add(pane);
     Toolkit env = frame.getToolkit();
     Dimension envDim = env.getScreenSize();
     envDim.height=envDim.height - 30;
     frame.setSize(envDim);
     frame.show();
           
     }catch(JRException ex) {
     System.out.println(ex.getMessage());
     ex.printStackTrace();
     }catch(Exception ex) {
     System.out.println(ex.getMessage());
     ex.printStackTrace();
     }
     }*/
}
