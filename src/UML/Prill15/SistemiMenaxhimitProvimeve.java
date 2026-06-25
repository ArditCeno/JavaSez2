package UML.Prill15;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemiMenaxhimitProvimeve extends JFrame {

    private JTextField txtNota, txtKoment;
    private JComboBox<String> comboStudentet, comboLendet;
    private JTextArea txtLog;

    public SistemiMenaxhimitProvimeve() {
        setTitle("Sistemi e-Student 2.0 - Paneli i Pedagogut");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Swimlane: PEDAGOG (GUI) ---
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("Zgjidh Studentin:"));
        comboStudentet = new JComboBox<>(new String[]{"Ardit Ceno", "Erla Seci", "Kristi Gjosasi", "Jana Zyka"});
        panelForm.add(comboStudentet);

        panelForm.add(new JLabel("Zgjidh Lenden:"));
        comboLendet = new JComboBox<>(new String[]{"UML", "Java", "Mat.Aplikuar", "Algoritmike"});
        panelForm.add(comboLendet);

        panelForm.add(new JLabel("Vendos Noten (4-10):"));
        txtNota = new JTextField();
        panelForm.add(txtNota);

        panelForm.add(new JLabel("Koment:"));
        txtKoment = new JTextField();
        panelForm.add(txtKoment);

        JButton btnRuaj = new JButton("Ruaj Noten");
        btnRuaj.setBackground(new Color(46, 204, 113));
        btnRuaj.setForeground(Color.WHITE);
        panelForm.add(btnRuaj);

        add(panelForm, BorderLayout.NORTH);

        // Logu i Sistemit (Per te pare çfare ndodh ne "Llogjika" dhe "Data")
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setBackground(new Color(236, 240, 241));
        add(new JScrollPane(txtLog), BorderLayout.CENTER);

        // --- Event Handling (Llogjika e Sistemit) ---
        btnRuaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesiVendosjesNotes();
            }
        });
    }

    private void procesiVendosjesNotes() {
        try {
            String studenti = (String) comboStudentet.getSelectedItem();
            String lenda = (String) comboLendet.getSelectedItem();
            int nota = Integer.parseInt(txtNota.getText());

            // 1. Decision Node: Validimi (4-10)
            if (nota < 4 || nota > 10) {
                JOptionPane.showMessageDialog(this, "Gabim: Nota duhet te jete midis 4 dhe 10!", "Validimi deshtoi", JOptionPane.ERROR_MESSAGE);
                txtLog.append("[Logjika]: Nota " + nota + " eshte e pavlefshme.\n");
                return;
            }

            txtLog.append("[Logjika]: Duke u procesuar nota per " + studenti + " ne " + lenda + "...\n");

            // 2. Simulimi i "Kontrollit per dublikim" dhe "Ruajtjes ne DB"
            txtLog.append("[Data]: Duke kontrolluar ne DB nese ekziston note e vjeter...\n");
            txtLog.append("[Data]: Sukses! Nota u ruajt ne bazen e te dhenave.\n");

            // 3. Paralelizmi (Fork/Join): Email dhe Statistikat
            kryejVeprimetParalele(studenti, lenda, nota);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje numer te vlefshem!");
        }
    }

    private void kryejVeprimetParalele(String studenti, String lenda, int nota) {
        // Thread 1: Dergimi i Email-it (Sipas diagrames)
        Thread emailThread = new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            txtLog.append("[Paralel]: Email u dergua te " + studenti + " (" + lenda + " - Nota: " + nota + ")\n");
        });

        // Thread 2: Perditesimi i Statistikave (Sipas diagrames)
        Thread statsThread = new Thread(() -> {
            try { Thread.sleep(800); } catch (InterruptedException e) {}
            txtLog.append("[Paralel]: Statistikat e " + lenda + " u perditesuan ne DB.\n");
        });

        emailThread.start();
        statsThread.start();

        JOptionPane.showMessageDialog(this, "Procesi u perfundua me sukses!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemiMenaxhimitProvimeve().setVisible(true);
        });
    }
}
