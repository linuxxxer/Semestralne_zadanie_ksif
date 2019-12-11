package zadanie.graphics;

import zadanie.graphics.listeners.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOurFrame extends JFrame {

    private JLabel zadajText, label2;
    private JTextArea ot;
    private JScrollPane otScroll;
    private JButton desifruj;
    private JButton nacitaj;
    private JButton konci;
    private JTextField textField;

    public JOurFrame() {
        this.setName("Transposition Cipher");

        desifruj = new JButton("Dešifrovať");
        desifruj.setBounds(250,100,120,40);

        zadajText = new JLabel();
        zadajText.setText("Zadaj zašifrovaný text: ");
        zadajText.setBounds(10,10,190,100);

        label2 = new JLabel("Alebo načítaj zo súboru...");
        label2.setBounds(10,110,200,100);

        ot = new JTextArea(5, 20);
        ot.setWrapStyleWord(true);
        ot.setLineWrap(true);
        ot.setEditable(false);
        ot.setBounds(10, 250, 580, 250);

        otScroll = new JScrollPane(ot);
        otScroll.setBounds(10, 250, 580, 250);
        nacitaj = new JButton("Načítať");
        nacitaj.setBounds(250, 190, 120, 40);

        textField = new JTextField();
        textField.setBounds(190,50,330,30);

        konci = new JButton("Exit");
        konci.setBounds(250, 520, 120, 40);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.add(zadajText);
        this.add(textField);
        this.add(label2);
        this.add(nacitaj);
        this.add(desifruj);
        this.add(konci);
//        this.add(ot);
//        otScroll.setSize(570, 250);
        this.add(otScroll);

        this.setLayout(null);
        this.setSize(600,600);
        this.setVisible(true);

        desifruj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ZT = textField.getText();
                ot.setText(decryptListener.decrypt(ZT));
            }
        });

    }

}
