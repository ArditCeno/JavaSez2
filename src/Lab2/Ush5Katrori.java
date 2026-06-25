package Lab2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush5Katrori extends JFrame {

    private JTextField fushaTekstit;
    private JButton butoni;
    private JLabel etiketa;

    public Ush5Katrori() {
        createComponents();
    }

    private void createComponents() {
        setTitle("Llogarit Katrorin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 150);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setLocationRelativeTo(null);

        fushaTekstit = new JTextField(10);
        butoni = new JButton("Llogarit");
        etiketa = new JLabel("Katrori i numrit eshte ");

        add(new JLabel("Vendosni numrin"));
        add(fushaTekstit);
        add(butoni);
        add(etiketa);

        butoni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numri = Double.parseDouble(fushaTekstit.getText());
                    double katrori = numri * numri;

                    if (katrori % 1 == 0) {
                        etiketa.setText("Katrori i numrit eshte: " + (long) katrori);
                    } else {
                        etiketa.setText("Katrori i numrit eshte: " + katrori);
                    }
                } catch (NumberFormatException ex) {
                    etiketa.setText(" Ju lutem jepni nje numer të vlefshem");
                }
            }
        });
    }
}
