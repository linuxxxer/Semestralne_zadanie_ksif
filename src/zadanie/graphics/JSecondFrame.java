package zadanie.graphics;

import zadanie.crypto.genalg.GeneticalAlgorithm;
import zadanie.graphics.listeners.readListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Frame for work with Genetic Algorithm
 */

public class JSecondFrame extends JFrame {

    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 100;

    GeneticalAlgorithm genAlg;

    JButton close;
    JButton decrypt;
    JButton nacitat;
    JButton clear;

    JTextArea ot;
    JTextField zt;
    JScrollPane scrollPane;

    JLabel zadajLabel;
    JLabel aleboLabel;

    public JSecondFrame() {

        getContentPane().setBackground(Color.GRAY);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Geneticky");
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(null);

        zadajLabel = new JLabel("Zadaj zašifrovaný text: ");
        zadajLabel.setBounds(10, 55, 190, 30);
        zadajLabel.setHorizontalAlignment(SwingConstants.CENTER);

        zt = new JTextField();
        zt.setBounds(200, 55, 250, 30);

        aleboLabel = new JLabel("Alebo načítaj zo súboru...");
        aleboLabel.setVerticalAlignment(SwingConstants.CENTER);
        aleboLabel.setBounds(10, 90, 300, 30);

        ot = new JTextArea();
        ot.setEditable(false);
        ot.setLineWrap(true);
        ot.setWrapStyleWord(true);

        scrollPane = new JScrollPane(ot);
        scrollPane.setBounds(10, 140, 580, 310);

        decrypt = new JButton("Dešifrovať");
        decrypt.setBackground(Color.DARK_GRAY);
        decrypt.setForeground(Color.WHITE);
        decrypt.setBounds(470, 55, BUTTON_WIDTH, BUTTON_HEIGHT);

        nacitat = new JButton("Načítať");
        nacitat.setBackground(Color.DARK_GRAY);
        nacitat.setForeground(Color.WHITE);
        nacitat.setBounds(470, 95, BUTTON_WIDTH, BUTTON_HEIGHT);

        clear = new JButton("Clear");
        clear.setBackground(Color.DARK_GRAY);
        clear.setForeground(Color.WHITE);
        clear.setBounds(250, 480, BUTTON_WIDTH, BUTTON_HEIGHT);

        close = new JButton("Exit");
        close.setBackground(Color.DARK_GRAY);
        close.setForeground(Color.WHITE);
        close.setBounds(250, 520,BUTTON_WIDTH, BUTTON_HEIGHT);

        this.add(decrypt);
        this.add(nacitat);
        this.add(clear);
        this.add(close);

        this.add(zt);
        this.add(zadajLabel);
        this.add(aleboLabel);
        this.add(scrollPane);

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ZT = zt.getText();
                StringBuilder OTtext = new StringBuilder();
                for (int i = 3; i <= 20; i++) {
                    if (ZT.length() % i == 0) {
                        OTtext.append("Dlžka kľúča: ").append(i).append("\n\n");
                        genAlg = new GeneticalAlgorithm(ZT, i);
                        try {
                            OTtext.append(genAlg.geneticAlgorithmRun()).append("\n\n");
                        } catch (Exception except) {
                            continue;
                        }
                    }
                }
                ot.setText(String.valueOf(OTtext));
            }
        });

//        TODO nacitanie zo suboru
        nacitat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ZT = readListener.readFromFile(readListener.getPath(JSecondFrame.super.rootPane));

                StringBuilder OTtext = new StringBuilder(new String(""));
                for (int i = 3; i <= 20; i++) {
                    if (ZT.length() % i == 0) {
                        OTtext.append("Dlžka kľúča: ").append(i).append("\n\n");
                        genAlg = new GeneticalAlgorithm(ZT, i);
                        OTtext.append( genAlg.geneticAlgorithmRun() ).append("\n\n");
                    }
                }

                ot.setText(String.valueOf(OTtext));
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ot.setText("");
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JDefault();
                JSecondFrame.this.dispose();
            }
        });
    }

    private int guessKey(String zt) {

        for (int i = 0; i < 20; i++) {
            if (zt.length() % i == 0) {
                System.out.println("Guessed: " + i);
                return i;
            }
        }

        return 0;
    }

}
