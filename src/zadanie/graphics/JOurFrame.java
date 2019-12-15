package zadanie.graphics;

import zadanie.graphics.listeners.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOurFrame extends JFrame {

    private JLabel zadajText, label2, permLabel;
    private JTextArea ot;
    private JScrollPane otScroll;
    private JButton desifruj;
    private JButton nacitaj;
    private JButton konci;
    private JButton clear;
    private JTextField textField;

    private String DEFAULTPERMLABELTEXT = "Neznáma permutácia";

    decryptListener decryptIt;
    String permutation;

    public JOurFrame() {

        getContentPane().setBackground(Color.GRAY);

        decryptIt = new decryptListener();

//        nastavenie roznych objektov
        zadajText = new JLabel();
        zadajText.setText("Zadaj zašifrovaný text: ");
        zadajText.setBounds(10,50,190,30);
        zadajText.setVerticalAlignment(SwingConstants.CENTER);

        textField = new JTextField();
        textField.setBounds(190,50,390,30);

        desifruj = new JButton("Dešifrovať");
        desifruj.setBackground(Color.DARK_GRAY);
        desifruj.setForeground(Color.WHITE);
        desifruj.setBounds(250,100,120,40);

        label2 = new JLabel("Alebo načítaj zo súboru...");
        label2.setBounds(10,175,200,20);
        label2.setVerticalAlignment(SwingConstants.CENTER);

        nacitaj = new JButton("Načítať");
        nacitaj.setBackground(Color.DARK_GRAY);
        nacitaj.setForeground(Color.WHITE);
        nacitaj.setBounds(250, 165, 120, 40);

        permLabel = new JLabel(DEFAULTPERMLABELTEXT);
        permLabel.setBounds(10, 225, 580, 20);

        ot = new JTextArea(5, 20);
        ot.setWrapStyleWord(true);
        ot.setLineWrap(true);
        ot.setEditable(false);

        otScroll = new JScrollPane(ot);
        otScroll.setBounds(10, 250, 580, 250);

        clear = new JButton("Clear");
        clear.setBackground(Color.DARK_GRAY);
        clear.setForeground(Color.WHITE);
        clear.setBounds(180, 520, 120, 40);

        konci = new JButton("Ukončiť");
        konci.setBackground(Color.DARK_GRAY);
        konci.setForeground(Color.WHITE);
        konci.setBounds(320, 520, 120, 40);


//        nastavenia frameu
        this.setTitle("Anagramová metóda");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.add(zadajText);
        this.add(textField);
        this.add(label2);
        this.add(permLabel);
        this.add(nacitaj);
        this.add(desifruj);
        this.add(konci);
        this.add(clear);
        this.add(otScroll);

        this.setLayout(null);
        this.setSize(600,600);
        this.setVisible(true);

//        action listener na desifrovanie
        desifruj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ZT = textField.getText();
                ot.setText(decryptIt.decrypt(ZT));
                permLabel.setText("Permutácia: " + decryptIt.getPerm());
            }
        });

//        action listener na nacitanie zo suboru
        nacitaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ot.setText( decryptIt
                        .decrypt( readListener
                                .readFromFile( readListener
                                        .getPath(JOurFrame.super.rootPane)) ) );
                String perm = decryptIt.getPerm();
                if (perm.equals("0")) {
                    permLabel.setText(DEFAULTPERMLABELTEXT);
                } else {
                    permLabel.setText("Permutácia: " + decryptIt.getPerm());
                }

            }
        });

//        action listener na vyprazdnenie poli
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                permLabel.setText(DEFAULTPERMLABELTEXT);
                ot.setText("");
            }
        });

//        action listener na ukoncenie programu
        konci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDefault jDefault = new JDefault();
                JOurFrame.super.dispose();
            }
        });

    }

}
