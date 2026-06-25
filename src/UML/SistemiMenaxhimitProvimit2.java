package UML;
import javax.swing.*;
import java.awt.*;

public class SistemiMenaxhimitProvimit2 extends JFrame {

    // Databaza ne memorie per te kaluar te dhenat mes paneleve
    private DefaultListModel<String> modelSekretarja = new DefaultListModel<>();
    private DefaultListModel<String> modelPedagogu = new DefaultListModel<>();

    public SistemiMenaxhimitProvimit2() {
        setTitle("Sistemi i Menaxhimit te Provimeve - Team 4");
        setSize(800, 600); // U rrit pak madhesia per te nxene tekstet e gjata
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Shtimi i skedave (Tabs)
        tabbedPane.addTab("Studenti", krijoPanelinStudentit());
        tabbedPane.addTab("Sekretarja", krijoPanelinSekretares());
        tabbedPane.addTab("Pedagogu", krijoPanelinPedagogut());

        add(tabbedPane);
    }

    // --- 1. PANELI I STUDENTIT ---
    private JPanel krijoPanelinStudentit() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Listen e opsioneve sipas kerkeses tende
        String[] departamentet = {
                "Departamenti i Informatikes",
                "Departamenti i Fizikes",
                "Departamenti i Kimise",
                "Departamenti i Biologjise",
                "Departamenti i Matematikes"
        };

        String[] deget = {"Inxhinieri Matematike&Informatike", "Informatike", "TIK", "Fizike "};
        String[] vitet = {"Viti I", "Viti II", "Viti III"};
        String[] lendet = {"Analize Matematike", "Programim ne Java", "UML", "Fizike", "Kimi Organike"};

        JComboBox<String> cbDept = new JComboBox<>(departamentet);
        JComboBox<String> cbDega = new JComboBox<>(deget);
        JComboBox<String> cbViti = new JComboBox<>(vitet);
        JComboBox<String> cbLenda = new JComboBox<>(lendet);

        JButton btnRegjistrohu = new JButton("Dergo Kerkesen per Regjistrim");
        JLabel lblStatusi = new JLabel("Statusi: Pa regjistruar");

        // Ndertimi i UI
        panel.add(new JLabel("Zgjidh Departamentin:"));
        panel.add(cbDept);
        panel.add(new JLabel("Zgjidh Degen:"));
        panel.add(cbDega);
        panel.add(new JLabel("Zgjidh Vitin:"));
        panel.add(cbViti);
        panel.add(new JLabel("Zgjidh Lenden:"));
        panel.add(cbLenda);
        panel.add(new JLabel("Veprimi:"));
        panel.add(btnRegjistrohu);
        panel.add(new JLabel("Statusi aktual:"));
        panel.add(lblStatusi);

        btnRegjistrohu.addActionListener(e -> {
            // Bashkimi i te gjitha zgjedhjeve ne nje rresht per Sekretarine
            String teDhenatPlota = "STUDENTI | " +
                    cbDept.getSelectedItem() + " | " +
                    cbDega.getSelectedItem() + " | " +
                    cbViti.getSelectedItem() + " | " +
                    cbLenda.getSelectedItem();

            modelSekretarja.addElement(teDhenatPlota);
            lblStatusi.setText("Statusi: Ne pritje te aprovimit...");
            JOptionPane.showMessageDialog(this, "Kerkesa u dergua me sukses te Sekretaria!");
        });

        return panel;
    }

    // --- 2. PANELI I SEKRETARES ---
    private JPanel krijoPanelinSekretares() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JList<String> listaKerkesave = new JList<>(modelSekretarja);
        JButton btnAprovo = new JButton("Aprovo Regjistrimin");
        JButton btnPubliko = new JButton("Publiko Daten e Provimit");

        JPanel butonaPanel = new JPanel();
        butonaPanel.add(btnAprovo);
        butonaPanel.add(btnPubliko);

        btnAprovo.addActionListener(e -> {
            String selektuar = listaKerkesave.getSelectedValue();
            if (selektuar != null) {
                modelPedagogu.addElement(selektuar); // Kalon te Pedagogu
                modelSekretarja.removeElement(selektuar); // Largohet nga lista e pritjes
                JOptionPane.showMessageDialog(this, "Studenti u aprovua me sukses!");
            }
        });

        btnPubliko.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Orari dhe data e provimit u publikuan!");
        });

        panel.add(new JLabel("Lista e kerkesave per shqyrtim:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(listaKerkesave), BorderLayout.CENTER);
        panel.add(butonaPanel, BorderLayout.SOUTH);

        return panel;
    }

    // --- 3. PANELI I PEDAGOGUT ---
    private JPanel krijoPanelinPedagogut() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JList<String> listaStudenteve = new JList<>(modelPedagogu);
        JTextField txtNota = new JTextField(5);
        JButton btnVendosNoten = new JButton("Ruaj Noten");

        JPanel vleresimPanel = new JPanel();
        vleresimPanel.add(new JLabel("Vendos Noten (4-10):"));
        vleresimPanel.add(txtNota);
        vleresimPanel.add(btnVendosNoten);

        btnVendosNoten.addActionListener(e -> {
            String studenti = listaStudenteve.getSelectedValue();
            if (studenti != null && !txtNota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nota " + txtNota.getText() + " u regjistrua per:\n" + studenti);
                txtNota.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Zgjidhni nje student dhe plotesoni noten!");
            }
        });

        panel.add(new JLabel("Lista e studenteve te gatshem per vleresim:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(listaStudenteve), BorderLayout.CENTER);
        panel.add(vleresimPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        // Ekzekutimi i GUI
        SwingUtilities.invokeLater(() -> {
            new SistemiMenaxhimitProvimit2().setVisible(true);
        });
    }
}