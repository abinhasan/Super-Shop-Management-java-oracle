/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author Saikat
 */
public class Tracer1 {

    LinkedList elm;
    JLabel label;

    public Tracer1(String init, JLabel lab) {
        elm = new LinkedList();
        label = lab;
        elm.add(init);
        label.setText(init);

    }

    public void tracerAdd(String st) {
        elm.add(st);
        setLabel();
    }

    public void tracerBack() {
        elm.removeLast();
        setLabel();
    }

    private void setLabel() {
        String temp = new String();
        for (int i = 0; i < elm.size(); i++) {
            if (i > 0) {
                temp = temp + " > ";
            }

            temp = temp + elm.get(i);
        }

        label.setText(temp);
        System.out.println("go ---> " + temp);
    }
}
