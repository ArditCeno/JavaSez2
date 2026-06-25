package Lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush4Kliko extends JFrame {
    private JLabel label;
    private JButton button;

    public void ndertoGUI() {
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        label = new JLabel("Butoni nuk eshte klikuar");
        button = new JButton("Kliko Ketu");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Butoni u klikua");
            }
        });

        add(label);
       add(button);

        setVisible(true);
    }
}