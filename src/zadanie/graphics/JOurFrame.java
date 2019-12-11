package zadanie.graphics;

import zadanie.graphics.listeners.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOurFrame extends JFrame {

    private JLabel zadajText, label2, permLabel;
    private JTextArea ot;
    private JScrollPane otScroll;
    private JButton desifruj;
    private JButton nacitaj;
    private JButton konci;
    private JTextField textField;

    decryptListener decryptIt;
    String permutation;

    public JOurFrame() {

        decryptIt = new decryptListener();


//        nastavenie roznych objektov
        zadajText = new JLabel();
        zadajText.setText("Zadaj zašifrovaný text: ");
        zadajText.setBounds(10,50,190,30);
        zadajText.setVerticalAlignment(SwingConstants.CENTER);

        textField = new JTextField();
        textField.setBounds(190,50,390,30);

        desifruj = new JButton("Dešifrovať");
        desifruj.setBounds(250,100,120,40);

        label2 = new JLabel("Alebo načítaj zo súboru...");
        label2.setBounds(10,150,200,20);
        label2.setVerticalAlignment(SwingConstants.TOP);

        nacitaj = new JButton("Načítať");
        nacitaj.setBounds(250, 150, 120, 40);

        permLabel = new JLabel("Unknown permutation");
        permLabel.setBounds(10, 225, 580, 20);

        ot = new JTextArea(5, 20);
        ot.setWrapStyleWord(true);
        ot.setLineWrap(true);
        ot.setEditable(false);

        otScroll = new JScrollPane(ot);
        otScroll.setBounds(10, 250, 580, 250);

        konci = new JButton("Exit");
        konci.setBounds(250, 520, 120, 40);


//        nastavenia frameu
        this.setName("Transposition Cipher");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(zadajText);
        this.add(textField);
        this.add(label2);
        this.add(permLabel);
        this.add(nacitaj);
        this.add(desifruj);
        this.add(konci);
//        this.add(ot);
//        otScroll.setSize(570, 250);
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
                permLabel.setText(decryptIt.getPerm());
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
                permLabel.setText(decryptIt.getPerm());
            }
        });

//        action listener na ukoncenie programu
        konci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOurFrame.super.dispose();
            }
        });

    }

}
