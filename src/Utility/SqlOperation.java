/*
 * SqlOperation.java
 * 
 * Created on Feb 27, 2008, 11:01:51 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Saikat
 */
import java.awt.Component;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SqlOperation {

    static String userid = "admin", password = "admin";
    static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    static Connection con = null;
    Statement stm;

    public SqlOperation() throws Exception {
        Connection con = getOracleJDBCConnection();
        if (con != null) {
            System.out.println("Got Connection.");
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Driver Name : " + meta.getDriverName());
            System.out.println("Driver Version : " + meta.getDriverVersion());
            stm = con.createStatement();

        } else {
            System.out.println("Could not Get Connection");
        }
    }

    public static void main(String[] args) throws Exception {

        SqlOperation sqlop = new SqlOperation();
    }

    public Connection getConnection() {
        return con;
    }
    /*  public void closeConnection()
     {
      
     try {
     con.close();
     } catch(SQLException ex) {
     JButton btn = new JButton();
     System.err.println("SQLException: " + ex.getMessage());
     Message.error1(btn,ex.getMessage());
     }
     }*/

    public static Connection getOracleJDBCConnection() throws SQLException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver() );

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            //con = DriverManager.getConnection(url, "admin", "admin");
            //System.out.println("id: "+userid+"  pass: "+password+" hihihi");
            url = "jdbc:oracle:thin:@" + User.host + ":" + User.port + ":" + User.sid;
            con = DriverManager.getConnection(url, User.usernName, User.password);
        } catch (SQLException ex) {
            JButton btn = new JButton();
            System.err.println("SQLException: " + ex.getMessage());
            Message.error1(btn, ex.getMessage());
        }

        return con;
    }

    public ResultSet getQueryResult(Component btn, String sql) {
        ResultSet rs = null;
        try {

            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);

            return rs;

        } catch (SQLException sqe) {
            System.out.println("this is sql excep from sqlOp class");
            Message.error1(btn, sqe.getMessage());

        } catch (Exception e) {
            System.out.println("this is excep e from sqlOp class");
            Message.error1(btn, e.getMessage());
        }

        return rs;
    }

    public static String singleStOPQuery(JButton btn, String sql) {
        String st = new String();
        System.out.println("sql: " + sql);
        try {
            SqlOperation sop = new SqlOperation();


            ResultSet rs = sop.getQueryResult(btn, sql);

            while (rs.next()) {
                st = rs.getString(1);
            }

        } catch (SQLException sqe) {
            Message.error1(btn, sqe.getMessage());
            return null;
        } catch (Exception e) {
            Message.error1(btn, e.getMessage());
        }

        return st;
    }

    public static void insertUpadateQuery(String sql, String className, JLabel c) {
        System.out.println("sql: " + sql);
        try {
            SqlOperation sop = new SqlOperation();
            ResultSet rs = sop.getQueryResult(new JButton(), sql);




        } catch (SQLException sqe) {
            System.out.println("this is sql excep from " + className);
            c.setText(sqe.getMessage());
        } catch (Exception e) {
            System.out.println("this is excep e from " + className);
            c.setText(e.getMessage());
        }
    }
}
