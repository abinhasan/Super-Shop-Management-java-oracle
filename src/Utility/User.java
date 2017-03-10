/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Saikat
 */
public class User {

    public static String usernName = new String();
    public static String password = new String();
    public static String ID = new String();
    public static String name = new String();
    public static String desig = new String();
    public static String host = "localhost";
    public static String port = "1521";
    public static String sid = "xe";

    public static void logout() {
        usernName = "";
        password = "";
        ID = "";
        name = "";
        desig = "";

    }

    public static String getUrl() {
        return "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
    }
}
