package UML.Prill15;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CompletableFuture;

public class Zgjidhje2 extends JFrame {

    private JTextField txtNota, txtKoment;
    private JComboBox<String> comboStudentet, comboLendet;
    private JTextArea txtLog;
    private JLabel lblUser;
    private String loggedInUser;
    private String userRole;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private static final Color COLOR_PRIMARY = new Color(99, 55, 210);
    private static final Color COLOR_PRIMARY_HOVER = new Color(80, 45, 180);
    private static final Color COLOR_SECONDARY = new Color(156, 136, 255);
    private static final Color COLOR_SUCCESS = new Color(46, 204, 113);
    private static final Color COLOR_SUCCESS_HOVER = new Color(39, 174, 96);
    private static final Color COLOR_DANGER = new Color(231, 76, 60);
    private static final Color COLOR_DANGER_HOVER = new Color(192, 57, 43);
    private static final Color COLOR_INFO = new Color(52, 152, 219);
    private static final Color COLOR_INFO_HOVER = new Color(41, 128, 185);
    private static final Color COLOR_BG_LIGHT = new Color(250, 250, 255);
    private static final Color COLOR_BG_DARK = new Color(45, 55, 72);
    private static final Color COLOR_TEXT_DARK = new Color(44, 62, 80);
    private static final Color COLOR_TEXT_LIGHT = new Color(236, 240, 241);
    private static final Color COLOR_CARD = Color.WHITE;

    public Zgjidhje2() {
        setTitle("🎓 Sistemi e-Student 2.0 - Zgjidhja 2");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(krijoLoginPanel(), "LOGIN");
        mainPanel.add(krijoPedagogPanel(), "PEDAGOG");
        mainPanel.add(krijoStudentPanel(), "STUDENT");

        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    private void styleButton(final JButton btn, final Color bgColor) {
        final Color hoverColor;
        if (bgColor == COLOR_PRIMARY) hoverColor = COLOR_PRIMARY_HOVER;
        else if (bgColor == COLOR_SUCCESS) hoverColor = COLOR_SUCCESS_HOVER;
        else if (bgColor == COLOR_DANGER) hoverColor = COLOR_DANGER_HOVER;
        else if (bgColor == COLOR_INFO) hoverColor = COLOR_INFO_HOVER;
        else hoverColor = bgColor;

        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setContentAreaFilled(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
    }

    private JPanel krijoLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(99, 55, 210, 30), 0, getHeight(), new Color(52, 152, 219, 30));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        panel.setBackground(COLOR_BG_LIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("🎓 Sistemi e-Student 2.0");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(COLOR_PRIMARY);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 18));
        formPanel.setOpaque(false);

        JLabel lblUsername = new JLabel("👤 Username:");
        lblUsername.setForeground(COLOR_TEXT_DARK);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        formPanel.add(lblUsername);

        JTextField txtUsername = new JTextField();
        styleTextFieldModern(txtUsername);
        formPanel.add(txtUsername);

        JLabel lblPassword = new JLabel("🔑 Password:");
        lblPassword.setForeground(COLOR_TEXT_DARK);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        formPanel.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        styleTextFieldModern(txtPassword);
        formPanel.add(txtPassword);

        JLabel lblRoli = new JLabel("👥 Roli:");
        lblRoli.setForeground(COLOR_TEXT_DARK);
        lblRoli.setFont(new Font("Segoe UI", Font.BOLD, 13));
        formPanel.add(lblRoli);

        JComboBox<String> comboRoli = new JComboBox<>(new String[]{"Pedagog", "Student"});
        styleComboBoxModern(comboRoli);
        formPanel.add(comboRoli);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(formPanel, gbc);

        JButton btnLogin = new JButton("🚀 Login");
        styleButton(btnLogin, COLOR_PRIMARY);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5);
        panel.add(btnLogin, gbc);

        JLabel lblStatus = new JLabel("");
        lblStatus.setForeground(COLOR_DANGER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 3;
        panel.add(lblStatus, gbc);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String roli = (String) comboRoli.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                lblStatus.setText("Ploteso te gjitha fushat!");
                return;
            }

            autentiko(username, password, roli, lblStatus);
        });

        return panel;
    }

    private void autentiko(String username, String password, String roli, JLabel lblStatus) {
        CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            if (roli.equals("Pedagog")) {
                return username.equals("pedagog") && password.equals("1234");
            } else {
                String[] studentet = {"Ardit Ceno", "Erla Seci", "Kristi Gjosasi", "Jana Zyka"};
                for (String s : studentet) {
                    if (s.equals(username) && password.equals("student")) {
                        return true;
                    }
                }
                return false;
            }
        }).thenAccept(result -> SwingUtilities.invokeLater(() -> {
            if (result) {
                loggedInUser = username;
                userRole = roli;
                lblUser.setText(roli + ": " + username);
                txtLog.append("[Auth]: Perdoruesi " + username + " u logua si " + roli + ".\n");

                if (roli.equals("Pedagog")) {
                    cardLayout.show(mainPanel, "PEDAGOG");
                } else {
                    cardLayout.show(mainPanel, "STUDENT");
                }
            } else {
                lblStatus.setText("Username ose password i gabuar!");
                txtLog.append("[Auth]: Logimi deshtoi per " + username + ".\n");
            }
        }));
    }

    private JPanel krijoPedagogPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_BG_LIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(COLOR_PRIMARY);
        panelTop.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        lblUser = new JLabel("👨‍🏫 Pedagog: ");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setForeground(Color.WHITE);
        panelTop.add(lblUser, BorderLayout.WEST);

        JButton btnLogout = new JButton("Logout");
        styleButton(btnLogout, COLOR_DANGER);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnLogout.addActionListener(e -> {
            txtLog.append("[Auth]: Perdoruesi " + loggedInUser + " u logua jashte.\n");
            loggedInUser = null;
            userRole = null;
            cardLayout.show(mainPanel, "LOGIN");
        });
        panelTop.add(btnLogout, BorderLayout.EAST);
        panel.add(panelTop, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 15, 12));
        panelForm.setBackground(COLOR_BG_LIGHT);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        addFormLabel(panelForm, "👨‍🎤 Zgjidh Studentin:");
        comboStudentet = new JComboBox<>(new String[]{"Ardit Ceno", "Erla Seci", "Kristi Gjosasi", "Jana Zyka"});
        styleComboBoxModern(comboStudentet);
        panelForm.add(comboStudentet);

        addFormLabel(panelForm, "📚 Zgjidh Lenden:");
        comboLendet = new JComboBox<>(new String[]{"UML", "Java", "Mat.Aplikuar", "Algoritmike"});
        styleComboBoxModern(comboLendet);
        panelForm.add(comboLendet);

        addFormLabel(panelForm, "📝 Vendos Noten (4-10):");
        txtNota = new JTextField();
        styleTextFieldModern(txtNota);
        panelForm.add(txtNota);

        addFormLabel(panelForm, "💬 Koment:");
        txtKoment = new JTextField();
        styleTextFieldModern(txtKoment);
        panelForm.add(txtKoment);

        panelForm.add(new JLabel());

        JButton btnRuaj = new JButton("💾 Ruaj Noten");
        styleButton(btnRuaj, COLOR_SUCCESS);
        panelForm.add(btnRuaj);

        panel.add(panelForm, BorderLayout.CENTER);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtLog.setBackground(COLOR_BG_DARK);
        txtLog.setForeground(new Color(126, 217, 87));
        JScrollPane scrollLog = new JScrollPane(txtLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_PRIMARY), "📋 Log", 0, 0, new Font("Segoe UI", Font.BOLD, 12), COLOR_PRIMARY));
        panel.add(scrollLog, BorderLayout.SOUTH);

        btnRuaj.addActionListener(e -> procesiVendosjesNotes());

        return panel;
    }

    private void addFormLabel(JPanel panel, String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(COLOR_TEXT_DARK);
        panel.add(lbl);
    }

    private void styleComboBox(JComboBox<?> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleTextField(JTextField txt) {
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void styleComboBoxModern(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setForeground(COLOR_TEXT_DARK);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
                return this;
            }
        });
    }

    private void styleTextFieldModern(JTextField txt) {
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setForeground(COLOR_TEXT_DARK);
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        txt.setOpaque(true);
        txt.setBackground(Color.WHITE);
    }

    private JPanel krijoStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_BG_LIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTop = new JPanel(new BorderLayout());
        panelTop.setBackground(COLOR_INFO);
        panelTop.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        lblUser = new JLabel("👨‍🎓 Student: ");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setForeground(Color.WHITE);
        panelTop.add(lblUser, BorderLayout.WEST);

        JButton btnLogout = new JButton("Logout");
        styleButton(btnLogout, COLOR_DANGER);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnLogout.addActionListener(e -> {
            txtLog.append("[Auth]: Perdoruesi " + loggedInUser + " u logua jashte.\n");
            loggedInUser = null;
            userRole = null;
            cardLayout.show(mainPanel, "LOGIN");
        });
        panelTop.add(btnLogout, BorderLayout.EAST);
        panel.add(panelTop, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(COLOR_BG_LIGHT);
        panelForm.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Kerko Permiresim Lende");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(COLOR_INFO);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelForm.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel lblLenda = new JLabel("Zgjidh Lenden:");
        lblLenda.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblLenda.setForeground(COLOR_TEXT_DARK);
        panelForm.add(lblLenda, gbc);

        JComboBox<String> comboLendaPermiresim = new JComboBox<>(new String[]{"UML", "Java", "Mat.Aplikuar", "Algoritmike"});
        styleComboBoxModern(comboLendaPermiresim);
        gbc.gridx = 1;
        panelForm.add(comboLendaPermiresim, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblArsyeja = new JLabel("Arsyeja:");
        lblArsyeja.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblArsyeja.setForeground(COLOR_TEXT_DARK);
        panelForm.add(lblArsyeja, gbc);

        JTextArea txtArsyeja = new JTextArea(3, 15);
        txtArsyeja.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtArsyeja.setForeground(COLOR_TEXT_DARK);
        txtArsyeja.setLineWrap(true);
        txtArsyeja.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_SECONDARY, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtArsyeja.setBackground(Color.WHITE);
        JScrollPane scrollArsye = new JScrollPane(txtArsyeja);
        gbc.gridx = 1;
        panelForm.add(scrollArsye, gbc);

        JButton btnDergo = new JButton("Dergo Kerkesen");
        styleButton(btnDergo, COLOR_INFO);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 12, 12, 12);
        panelForm.add(btnDergo, gbc);

        panel.add(panelForm, BorderLayout.CENTER);

        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtLog.setBackground(COLOR_BG_DARK);
        txtLog.setForeground(new Color(126, 217, 87));
        JScrollPane scrollLog = new JScrollPane(txtLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(COLOR_INFO), "📋 Log", 0, 0, new Font("Segoe UI", Font.BOLD, 12), COLOR_INFO));
        panel.add(scrollLog, BorderLayout.SOUTH);

        btnDergo.addActionListener(e -> {
            String lenda = (String) comboLendaPermiresim.getSelectedItem();
            String arsyeja = txtArsyeja.getText();
            
            if (arsyeja.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ju lutem shkruani arsyejen!");
                return;
            }
            
            txtLog.append("[Student]: Kerkese per permiresim - " + lenda + "\n");
            txtLog.append("[Student]: Arsyeja: " + arsyeja + "\n");
            
            CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try { Thread.sleep(800); } catch (InterruptedException ex) {}
                    txtLog.append("[Paralel]: Email u dergua te pedagogu per " + loggedInUser + ".\n");
                }),
                CompletableFuture.runAsync(() -> {
                    try { Thread.sleep(600); } catch (InterruptedException ex) {}
                    txtLog.append("[Paralel]: Kerkesa u regjistrua ne sistem.\n");
                })
            ).join();
            
            txtLog.append("[Auth]: Veprimi u krye nga " + loggedInUser + ".\n");
            JOptionPane.showMessageDialog(this, "Kerkesa u dergua me sukses!");
            txtArsyeja.setText("");
        });

        return panel;
    }

    private void procesiVendosjesNotes() {
        try {
            String studenti = (String) comboStudentet.getSelectedItem();
            String lenda = (String) comboLendet.getSelectedItem();
            int nota = Integer.parseInt(txtNota.getText());

            if (nota < 4 || nota > 10) {
                JOptionPane.showMessageDialog(this, "Gabim: Nota duhet te jete midis 4 dhe 10!", "Validimi deshtoi", JOptionPane.ERROR_MESSAGE);
                txtLog.append("[Logjika]: Nota " + nota + " eshte e pavlefshme.\n");
                return;
            }

            txtLog.append("[Logjika]: Duke u procesuar nota per " + studenti + " ne " + lenda + "...\n");

            txtLog.append("[Data]: Kontrollimi ne DB per note ekzistuese...\n");
            txtLog.append("[Data]: Sukses! Nota u ruajt ne bazen e te dhenave.\n");

            kryejVeprimetParalele(studenti, lenda, nota);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni nje numer te vlefshem!");
        }
    }

    private void kryejVeprimetParalele(String studenti, String lenda, int nota) {
        CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                txtLog.append("[Paralel]: Email u dergua te " + studenti + " (" + lenda + " - Nota: " + nota + ")\n");
            }),
            CompletableFuture.runAsync(() -> {
                try { Thread.sleep(800); } catch (InterruptedException e) {}
                txtLog.append("[Paralel]: Statistikat e " + lenda + " u perditesuan ne DB.\n");
            }),
            CompletableFuture.runAsync(() -> {
                try { Thread.sleep(500); } catch (InterruptedException e) {}
                txtLog.append("[Paralel]: Audit log u ruajt per " + studenti + ".\n");
            })
        ).join();

        txtLog.append("[Auth]: Veprimi u krye nga " + loggedInUser + ".\n");
        JOptionPane.showMessageDialog(this, "Procesi u perfundua me sukses!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Zgjidhje2().setVisible(true);
        });
    }
}
