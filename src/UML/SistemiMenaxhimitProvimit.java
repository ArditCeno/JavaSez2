package UML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SistemiMenaxhimitProvimit extends JFrame {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private JPanel mainPanel;
    private LoginPanel loginPanel;

    public SistemiMenaxhimitProvimit() {
        setTitle("Sistemi i Menaxhimit te Provimeve - Team 4");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel();
        mainPanel = krijoPanelinKryesor();

        setContentPane(loginPanel);
    }

    private JPanel krijoPanelinKryesor() {
        JPanel panel = new JPanel(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuSkedar = new JMenu("Skedar");
        menuSkedar.setMnemonic('S');

        JMenuItem itemDil = new JMenuItem("Dil");
        itemDil.setMnemonic('D');
        itemDil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        itemDil.addActionListener(e -> System.exit(0));

        JMenuItem itemRifillo = new JMenuItem("Rifillo");
        itemRifillo.setMnemonic('R');
        itemRifillo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemRifillo.addActionListener(e -> rifilloSistemin());

        menuSkedar.add(itemRifillo);
        menuSkedar.addSeparator();
        menuSkedar.add(itemDil);

        JMenu menuNdihme = new JMenu("Ndihme");
        menuNdihme.setMnemonic('N');

        JMenuItem itemInfo = new JMenuItem("Rreth Sistemit");
        itemInfo.addActionListener(e -> shfaqInfo());
        menuNdihme.add(itemInfo);

        menuBar.add(menuSkedar);
        menuBar.add(menuNdihme);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Studenti", krijoPanelinStudentit());
        tabbedPane.addTab("Sekretarja", krijoPanelinSekretares());
        tabbedPane.addTab("Pedagogu", krijoPanelinPedagogut());

        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }

    private void hapMainFrame() {
        setContentPane(mainPanel);
        revalidate();
        repaint();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private class LoginPanel extends JPanel {
        private JTextField txtUsername;
        private JPasswordField txtPassword;
        private JButton btnLogin;
        private JButton btnAnulo;
        private JLabel lblMessage;

        public LoginPanel() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblTitle = new JLabel("Sistemi i Menaxhimit te Provimeve");
            lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
            lblTitle.setForeground(new Color(0, 102, 204));

            JLabel lblSubtitle = new JLabel("Team 4");
            lblSubtitle.setFont(new Font("Arial", Font.ITALIC, 14));

            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    "Hyrja ne Sistem",
                    javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.TOP,
                    new Font("Arial", Font.BOLD, 14)
            ));
            formPanel.setPreferredSize(new Dimension(350, 180));

            txtUsername = new JTextField(20);
            txtPassword = new JPasswordField(20);

            btnLogin = new JButton("Hyr");
            btnLogin.setPreferredSize(new Dimension(100, 35));
            btnLogin.setMnemonic(KeyEvent.VK_H);
            btnLogin.setBackground(new Color(0, 153, 76));
            btnLogin.setForeground(Color.WHITE);
            btnLogin.setFocusPainted(false);

            btnAnulo = new JButton("Anulo");
            btnAnulo.setPreferredSize(new Dimension(100, 35));
            btnAnulo.setMnemonic(KeyEvent.VK_A);
            btnAnulo.setBackground(new Color(204, 51, 51));
            btnAnulo.setForeground(Color.WHITE);
            btnAnulo.setFocusPainted(false);

            lblMessage = new JLabel(" ");
            lblMessage.setForeground(Color.RED);
            lblMessage.setFont(new Font("Arial", Font.ITALIC, 12));

            gbc.gridx = 0; gbc.gridy = 0;
            formPanel.add(new JLabel("Perdoruesi:"), gbc);
            gbc.gridx = 1;
            formPanel.add(txtUsername, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            formPanel.add(new JLabel("Fjalekalimi:"), gbc);
            gbc.gridx = 1;
            formPanel.add(txtPassword, gbc);

            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            btnPanel.add(btnLogin);
            btnPanel.add(btnAnulo);

            gbc.gridx = 0; gbc.gridy = 2;
            gbc.gridwidth = 2;
            formPanel.add(btnPanel, gbc);

            gbc.gridx = 0; gbc.gridy = 3;
            gbc.gridwidth = 2;
            formPanel.add(lblMessage, gbc);

            gbc.gridx = 0; gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(lblTitle, gbc);

            gbc.gridy = 1;
            add(lblSubtitle, gbc);

            gbc.gridy = 2;
            add(formPanel, gbc);

            JLabel lblCredits = new JLabel("Sheno: admin / admin123");
            lblCredits.setFont(new Font("Arial", Font.ITALIC, 10));
            lblCredits.setForeground(Color.GRAY);
            gbc.gridy = 3;
            add(lblCredits, gbc);

            btnLogin.addActionListener(e -> autentiko());
            btnAnulo.addActionListener(e -> System.exit(0));
            txtPassword.addActionListener(e -> autentiko());
            txtUsername.addActionListener(e -> txtPassword.requestFocus());
        }

        private void autentiko() {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                lblMessage.setText("Plotesoni te gjitha fushat!");
                return;
            }

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                SwingUtilities.invokeLater(() -> hapMainFrame());
            } else {
                lblMessage.setText("Perdoruesi ose fjalekalimi eshte i gabuar!");
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        }
    }

    private java.util.List<KerkesaPerRegjistrim> kerkesatNePritje = new java.util.ArrayList<>();
    private java.util.List<KerkesaPerRegjistrim> kerkesatEApprovuara = new java.util.ArrayList<>();
    private java.util.List<Studenti> studentet = new java.util.ArrayList<>();

    private JList<KerkesaPerRegjistrim> listaKerkesave;
    private JList<Studenti> listaStudenteve;
    private JLabel lblStatusi;
    private JLabel lblDataProvimit;
    private DefaultListModel<KerkesaPerRegjistrim> modelKerkesat;
    private DefaultListModel<Studenti> modelStudentet;

    private final String[] departamentet = {
            "Departamenti i Informatikes",
            "Departamenti i Fizikes",
            "Departamenti i Kimise",
            "Departamenti i Biologjise",
            "Departamenti i Matematikes"
    };

    private final String[] deget = {
            "Inxhinieri Matematike & Informatike",
            "Informatike",
            "TIK",
            "Fizike"
    };

    private final String[] vitet = {"Viti I", "Viti II", "Viti III"};

    private final String[] lendet = {
            "Analize Matematike",
            "Programim ne Java",
            "UML",
            "Fizike",
            "Kimi Organike"
    };

    private JPanel krijoPanelinStudentit() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JComboBox<String> cbDept = new JComboBox<>(departamentet);
        JComboBox<String> cbDega = new JComboBox<>(deget);
        JComboBox<String> cbViti = new JComboBox<>(vitet);
        JComboBox<String> cbLenda = new JComboBox<>(lendet);

        JButton btnRegjistrohu = new JButton("Dergo Kerkesen per Regjistrim");
        btnRegjistrohu.setPreferredSize(new Dimension(250, 35));

        lblStatusi = new JLabel("Statusi: Pa derguar kerkese");
        lblStatusi.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Departamenti:"), gbc);
        gbc.gridx = 1;
        panel.add(cbDept, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Deget:"), gbc);
        gbc.gridx = 1;
        panel.add(cbDega, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Viti:"), gbc);
        gbc.gridx = 1;
        panel.add(cbViti, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Lendet:"), gbc);
        gbc.gridx = 1;
        panel.add(cbLenda, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(btnRegjistrohu, gbc);

        gbc.gridy = 5;
        panel.add(lblStatusi, gbc);

        btnRegjistrohu.addActionListener(e -> {
            String dept = (String) cbDept.getSelectedItem();
            String dega = (String) cbDega.getSelectedItem();
            String viti = (String) cbViti.getSelectedItem();
            String lenda = (String) cbLenda.getSelectedItem();

            KerkesaPerRegjistrim kerkesa = new KerkesaPerRegjistrim(dept, dega, viti, lenda);
            kerkesatNePritje.add(kerkesa);
            azhurzoListenESekretares();

            lblStatusi.setText("Statusi: E derguar - ne pritje");
            lblStatusi.setForeground(new Color(0, 100, 0));

            JOptionPane.showMessageDialog(panel,
                    "Kerkesa u dergua me sukses!",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
        });

        return panel;
    }

    private JPanel krijoPanelinSekretares() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        modelKerkesat = new DefaultListModel<>();
        listaKerkesave = new JList<>(modelKerkesat);
        listaKerkesave.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaKerkesave.setVisibleRowCount(8);

        JButton btnAprovo = new JButton("Aprovo");
        btnAprovo.setPreferredSize(new Dimension(100, 30));

        JButton btnRefuzo = new JButton("Refuzo");
        btnRefuzo.setPreferredSize(new Dimension(100, 30));

        JButton btnPubliko = new JButton("Publiko Orarin");
        btnPubliko.setPreferredSize(new Dimension(150, 30));

        JPanel butonaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        butonaPanel.add(btnAprovo);
        butonaPanel.add(btnRefuzo);
        butonaPanel.add(Box.createHorizontalGlue());
        butonaPanel.add(btnPubliko);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Kerkesa per Regjistrim:"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(listaKerkesave), BorderLayout.CENTER);
        topPanel.add(butonaPanel, BorderLayout.SOUTH);

        btnAprovo.addActionListener(e -> aprovoKerkesen());
        btnRefuzo.addActionListener(e -> refuzoKerkesen());
        btnPubliko.addActionListener(e -> publikoOrarin());

        panel.add(topPanel, BorderLayout.CENTER);

        return panel;
    }

    private void azhurzoListenESekretares() {
        modelKerkesat.clear();
        for (KerkesaPerRegjistrim k : kerkesatNePritje) {
            if (k.getStatusi() == KerkesaPerRegjistrim.StatusiKerkeses.NE_PRITJE) {
                modelKerkesat.addElement(k);
            }
        }
    }

    private void aprovoKerkesen() {
        KerkesaPerRegjistrim kerkesa = listaKerkesave.getSelectedValue();
        if (kerkesa == null) {
            JOptionPane.showMessageDialog(this, "Zgjidhni nje kerkese!");
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "A jeni te sigurt qe doni te aprovoni?", "Konfirmo",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            kerkesa.setStatusi(KerkesaPerRegjistrim.StatusiKerkeses.E_APROVUAR);
            kerkesatEApprovuara.add(kerkesa);
            azhurzoListenESekretares();
            azhurzoListenEPedagogut();
            JOptionPane.showMessageDialog(this, "Studenti u aprovua!");
        }
    }

    private void refuzoKerkesen() {
        KerkesaPerRegjistrim kerkesa = listaKerkesave.getSelectedValue();
        if (kerkesa == null) {
            JOptionPane.showMessageDialog(this, "Zgjidhni nje kerkese!");
            return;
        }
        kerkesa.setStatusi(KerkesaPerRegjistrim.StatusiKerkeses.E_REFUZUAR);
        kerkesatNePritje.remove(kerkesa);
        azhurzoListenESekretares();
    }

    private void publikoOrarin() {
        if (kerkesatEApprovuara.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nuk ka studente te aprovuar!");
            return;
        }
        String dataStr = JOptionPane.showInputDialog(this, "Fut daten e provimit (dd/MM/yyyy):");
        if (dataStr != null && !dataStr.trim().isEmpty()) {
            lblDataProvimit.setText("Data e Provimit: " + dataStr);
            JOptionPane.showMessageDialog(this, "Orari u publikua!");
        }
    }

    private JPanel krijoPanelinPedagogut() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        modelStudentet = new DefaultListModel<>();
        listaStudenteve = new JList<>(modelStudentet);
        listaStudenteve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaStudenteve.setVisibleRowCount(8);

        JTextField txtNota = new JTextField(5);
        txtNota.setHorizontalAlignment(JTextField.CENTER);

        JButton btnVendosNoten = new JButton("Ruaj Noten");
        btnVendosNoten.setPreferredSize(new Dimension(120, 30));

        lblDataProvimit = new JLabel("Data e Provimit: Nuk eshte caktuar");

        JPanel vleresimPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        vleresimPanel.add(new JLabel("Vendos Noten (4-10):"));
        vleresimPanel.add(txtNota);
        vleresimPanel.add(btnVendosNoten);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(vleresimPanel, BorderLayout.NORTH);
        infoPanel.add(lblDataProvimit, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Studente per vleresim:"), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(listaStudenteve), BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.SOUTH);

        btnVendosNoten.addActionListener(e -> {
            Studenti studenti = listaStudenteve.getSelectedValue();
            String notaStr = txtNota.getText().trim();

            if (studenti == null) {
                JOptionPane.showMessageDialog(this, "Zgjidhni nje student!");
                return;
            }
            if (notaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Plotesoni noten!");
                return;
            }

            try {
                int nota = Integer.parseInt(notaStr);
                if (nota < 4 || nota > 10) {
                    JOptionPane.showMessageDialog(this, "Nota duhet te jete 4-10!");
                    return;
                }
                studenti.setNota(nota);
                modelStudentet.removeElement(studenti);
                JOptionPane.showMessageDialog(this, "Nota u regjistrua!");
                txtNota.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Futni numer te vlefshem!");
            }
        });

        txtNota.addActionListener(e -> btnVendosNoten.doClick());

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private void azhurzoListenEPedagogut() {
        modelStudentet.clear();
        for (KerkesaPerRegjistrim k : kerkesatEApprovuara) {
            Studenti s = new Studenti(k.getLenda(), k.getDega(), k.getViti());
            studentet.add(s);
            modelStudentet.addElement(s);
        }
    }

    private void shfaqInfo() {
        JOptionPane.showMessageDialog(this,
                "Sistemi i Menaxhimit te Provimeve\nVersioni: 2.0\nEkipi: Team 4",
                "Rreth Sistemit", JOptionPane.INFORMATION_MESSAGE);
    }

    private void rifilloSistemin() {
        int result = JOptionPane.showConfirmDialog(this,
                "A jeni te sigurt?", "Rifillo", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            kerkesatNePritje.clear();
            kerkesatEApprovuara.clear();
            studentet.clear();
            if (modelKerkesat != null) modelKerkesat.clear();
            if (modelStudentet != null) modelStudentet.clear();
            lblStatusi.setText("Statusi: Pa derguar kerkese");
            lblStatusi.setForeground(Color.BLACK);
            if (lblDataProvimit != null) lblDataProvimit.setText("Data e Provimit: Nuk eshte caktuar");
            JOptionPane.showMessageDialog(this, "Sistemi u rifillua!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemiMenaxhimitProvimit frame = new SistemiMenaxhimitProvimit();
            frame.setVisible(true);
        });
    }
}
