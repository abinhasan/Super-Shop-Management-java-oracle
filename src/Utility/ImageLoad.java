/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Mr.Jenius
 */
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.swing.JComponent;
import oracle.jdbc.OracleResultSet;
import oracle.sql.*;

import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;

public class ImageLoad extends java.applet.Applet {

    Connection con = null;
    Statement stm;
    Image theImage;
    // private Object b_in;

    public ImageLoad(JComponent comp) {
        try {
            SqlOperation sop = new SqlOperation();
            con = sop.getConnection();
            stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
            //stm = con.createStatement();
        } catch (SQLException e) {
            Message.error1(comp, e.getMessage());

        } catch (Exception e) {
            Message.error1(comp, e.getMessage());
        }
    }

    private boolean createEmptyBlobRow(String pKey) {
        Utility2.insertUpdateQuery("delete from person_image where person_id = '" + pKey + "' ", new JButton(), 0);
        try {
            String sql = "insert into person_image values('" + pKey + "',EMPTY_BLOB())";
            System.out.println(sql);
            stm.executeUpdate(sql);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //parm.logFile(e.toString());
            return false;
        }


    }

    private BLOB getTempBlob(String pKey) {
        try {
            ResultSet rs = stm.executeQuery("select  image  from person_image "
                    + "where person_id = '" + pKey + "' for update");
            if (rs.next()) {
                return ((OracleResultSet) rs).getBLOB(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean writeBlobToDatebase(BLOB t_Blob, File file) {

        try {
            ImageInputStream b_in = ImageIO.createImageInputStream(file);

            int chunksize = t_Blob.getChunkSize();
            byte[] byteBuffer = new byte[chunksize];
            //call BC for complete script
            long position = 1;
            int bytesRead;
            System.out.println("Is open:" + t_Blob.isOpen());
            while ((bytesRead = b_in.read(byteBuffer)) != -1) {
                t_Blob.putBytes(position, byteBuffer);
                position += bytesRead;
            }
            stm.execute("COMMIT");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //parm.logFile(e.toString());
            //parm.logFile(e.toString());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            //parm.logFile(e.toString());
            return false;
        }
    }

    public boolean saveImageToDatabase(String url, String pri_key) {
        File file = new File(url);
        if (!createEmptyBlobRow(pri_key)) {
            return false;
        }
        BLOB tempBlob = getTempBlob(pri_key);
        if (tempBlob == null) {
            return false;
        }
        if (!writeBlobToDatebase(tempBlob, file)) {
            return false;
        }
        return true;
    }

    public void loadImage(String person_id) {

        try {
            ResultSet rs = stm.executeQuery("select image from person_image where person_id = '" + person_id + "'");
            //
            if (rs.next()) {
                Blob blob = rs.getBlob(1);

                dumpBlob(con, blob);
                //theImage = getImage(getCodeBase(), "images/theImage.gif");

            }
        } catch (ClassNotFoundException c) {
            //handleError(c);
            c.printStackTrace();
        } catch (SQLException sql) {
            // handleError(sql);
            sql.printStackTrace();
        } catch (Exception s) {
            // handleError(s);
            s.printStackTrace();
        }
    }

    static void dumpBlob(Connection conn, Blob blob)
            throws Exception {
        CallableStatement cstmt1 =
                conn.prepareCall("begin ? := dbms_lob.getLength (?); end;");
        CallableStatement cstmt2 =
                conn.prepareCall("begin dbms_lob.read (?, ?, ?, ?); end;");
        cstmt1.registerOutParameter(1, Types.NUMERIC);
        cstmt1.setBlob(2, blob);
        cstmt1.execute();
        long length = cstmt1.getLong(1);
        long i = 0;
        int chunk = 100;
        FileOutputStream file = new FileOutputStream("image.jpeg");
        while (i < length) {
            cstmt2.setBlob(1, blob);
            cstmt2.setLong(2, chunk);
            cstmt2.registerOutParameter(2, Types.NUMERIC);
            cstmt2.setLong(3, i + 1);
            cstmt2.registerOutParameter(4, Types.VARBINARY);
            cstmt2.execute();
            long read_this_time = cstmt2.getLong(2);
            byte[] bytes_this_time = cstmt2.getBytes(4);
            {
// Put into file
                file.write(bytes_this_time);
                i += read_this_time;
                /*      System.out.print ("Read " + i + " bytes of " + length + "\n");*/
                if ((i + read_this_time) > length) {
                    chunk = (int) (length - i);
                }
            }
        }
        file.close();
        cstmt1.close();
        cstmt2.close();
    }
}
