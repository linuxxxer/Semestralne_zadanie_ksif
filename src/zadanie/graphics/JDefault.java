package zadanie.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDefault extends JFrame {

    private JButton jOurFrame;
    private JButton jSecondFrame;
    private JButton close;

    public JDefault() {
        getContentPane().setBackground(Color.GRAY);
        this.setTitle("Transition breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(350, 350);
        this.setVisible(true);

        jOurFrame = new JButton("Anagram");
        jOurFrame.setBackground(Color.DARK_GRAY);
        jOurFrame.setForeground(Color.WHITE);
        jOurFrame.setBounds(30, 100, 120, 30);

        jSecondFrame = new JButton("Geneticky");
        jSecondFrame.setBackground(Color.DARK_GRAY);
        jSecondFrame.setForeground(Color.WHITE);
        jSecondFrame.setBounds(200, 100, 120, 30);

        close = new JButton("Ukončiť");
        close.setBackground(Color.DARK_GRAY);
        close.setForeground(Color.WHITE);
        close.setBounds(125, 250, 100, 30);

        this.add(close);
        this.add(jOurFrame);
        this.add(jSecondFrame);

        jOurFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOurFrame ourFrame = new JOurFrame();
                JDefault.this.dispose();
            }
        });

        jSecondFrame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSecondFrame jSecondFrame = new JSecondFrame();
                JDefault.this.dispose();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDefault.this.dispose();
            }
        });

    }

}
