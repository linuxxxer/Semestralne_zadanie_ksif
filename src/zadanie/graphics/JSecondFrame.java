package zadanie.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSecondFrame extends JFrame {


    JButton close;

    public JSecondFrame() {

        getContentPane().setBackground(Color.GRAY);
        this.setName("Geneticky");
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(null);


        close = new JButton("Exit");
        close.setBackground(Color.DARK_GRAY);
        close.setForeground(Color.WHITE);
        close.setBounds(250, 520,100, 30);

        this.add(close);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDefault jDefault = new JDefault();
                JSecondFrame.this.dispose();
            }
        });
    }

}
