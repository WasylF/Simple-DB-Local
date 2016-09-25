package com.wslfinc;

import com.wslfinc.db.DBMS;
import com.wslfinc.forms.MainForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Wsl_F
 */
public class Main {

    public static void main(String[] args) {
        DBMS dbms = DBMS.getInstance();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm mainForm = new MainForm(dbms);
                mainForm.setVisible(true);
                mainForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event) {
                        DBMS.getInstance().serialize();
                    }
                });
            }
        });

    }
}
