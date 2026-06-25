package Seminar6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush2Nota extends JFrame {

    private JTextField fushaNota;
    private JTextArea siperfaqeTeksti;
    private JButton butoniShto;

    public Ush2Nota() {
        setTitle("YJE NE TABEL SE YJE JEMI VETE");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel paneliInput = new JPanel(new FlowLayout());
        fushaNota = new JTextField(5);
        butoniShto = new JButton("Shto");

        paneliInput.add(new JLabel("Nota 0-10"));
        paneliInput.add(fushaNota);
        paneliInput.add(butoniShto);

        siperfaqeTeksti = new JTextArea();
        siperfaqeTeksti.setEditable(false);
        siperfaqeTeksti.setFont(new Font("Monospaced", Font.BOLD, 18));

        siperfaqeTeksti.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(siperfaqeTeksti);

        add(paneliInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        butoniShto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gjeneroHistogramen();
            }
        });
    }

    private void gjeneroHistogramen() {
        try {
            String teksti = fushaNota.getText();
            int nota = Integer.parseInt(teksti);

            if (nota >= 0 && nota <= 10) {
                StringBuilder rreshti = new StringBuilder();
                for (int i = 0; i < nota; i++) {
                    rreshti.append("*");
                }
                siperfaqeTeksti.append(rreshti.toString() + "\n");

                fushaNota.setText("");
                fushaNota.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "vendosni nje numer nga 0 ne 10");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "vendosni vetem numra te plote nqs doni pergjigje");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ush2Nota().setVisible(true);
            }
        });
    }
}