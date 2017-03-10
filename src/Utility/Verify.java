/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author User
 */
public class Verify {

    public static boolean chk_null(JFormattedTextField jf, JLabel error) {
        if (jf.getText().equals("")) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_null(JXDatePicker jf, JLabel error) {
        if (jf.getDate() == null) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_null(JTextArea jf, JLabel error) {
        if (jf.getText().equals("")) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_null(JComboBox jf, JLabel error) {

        if (jf.getModel().getSize() == 0) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_null2(JFormattedTextField jf, JLabel error) {
        if (jf.getText().equals(" ")) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }

    }

    public static boolean chk_null(JTextField jf, JLabel error) {
        if (jf.getText().equals("")) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_null(JPasswordField jf, JLabel error) {
        if (jf.getText().equals("")) {
            error.setText("Fill in all the required fields");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_num(JFormattedTextField jf, String field, JLabel error) {
        try {
            int i = Integer.parseInt(jf.getText().toString());
            error.setVisible(false);
            return true;
        } catch (NumberFormatException ex) {
            error.setText(field + " must be a number");
            error.setVisible(true);
            return false;
        }
    }

    public static boolean chk_num(JTextField jf, String field, JLabel error) {
        try {
            int i = Integer.parseInt(jf.getText().toString());
            error.setVisible(false);
            return true;
        } catch (NumberFormatException ex) {
            error.setText(field + " must be a number");
            error.setVisible(true);
            return false;
        }
    }

    public static boolean chk_num(JTextField jf, String field, JComponent cm) {
        try {
            int i = Integer.parseInt(jf.getText().toString());
            return true;
        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(cm, field + " must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean chk_length_eq(JFormattedTextField jf, int length, String field, JLabel error) {
        if (jf.getText().length() != length) {
            error.setText(field + " must be " + length + " characters");
            error.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    public static boolean chk_length_eq(JTextField jf, int length, String field, JLabel error) {
        if (jf.getText().length() != length) {
            error.setText(field + " must be " + length + " characters");
            error.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    public static boolean staticchk_length_min(JTextField jf, int length, String field, JLabel error) {
        String str = jf.getText();
        System.out.println(str);
        if (str.length() < length) {
            error.setText(field + " must be minimum " + length + " characters");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_length_max(JTextField jf, int length, String field, JLabel error) {
        if (jf.getText().length() >= length + 1) {
            error.setText(field + " can be maximum " + length + " characters");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_length_max(JFormattedTextField jf, int length, String field, JLabel error) {
        if (jf.getText().length() >= length + 1) {
            error.setText(field + " can be maximum " + length + " characters");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }

    }

    public static boolean chk_isComboboxSelected(JComboBox jf, String field, JLabel error) {
        if (jf.isEnabled() && jf.isVisible() && jf.getSelectedIndex() == 0) {
            error.setText(field + " is not selected yet");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }

    public static boolean chk_dateCombobox(JComboBox day, JComboBox month, JComboBox year, String field, JLabel error) {
        if ((day.isEnabled() && day.isVisible() && day.getSelectedIndex() == 0)
                || (month.isEnabled() && month.isVisible() && month.getSelectedIndex() == 0)
                || (year.isEnabled() && year.isVisible() && year.getSelectedIndex() == 0)) {
            error.setText(field + " is not selected yet");
            error.setVisible(true);
            return false;
        } else {
            error.setVisible(false);
            return true;
        }
    }
    /* Example:
     public void check_button(JButton btn) {
     if (!chk_null(nameFormattedTextField) && !chk_null(rollFormattedTextField)
     && !chk_num(rollFormattedTextField) && !chk_null(subjectFormattedTextField)){
     btn.setEnabled(true);
     }
     else
     btn.setEnabled(false);
     }
     * */
}
