package Seminar6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Ush1Anagrama extends JFrame {

    private JTextField field1, field2;
    private JLabel labelStatus;
    private JButton btnCheck;

    private final Color BG_COLOR = new Color(14, 103, 152);
    private final Color ACCENT_COLOR = new Color(13, 110, 253);
    private final Color TEXT_COLOR = new Color(248, 249, 250);

    public Ush1Anagrama() {
        setUndecorated(true);
        initComponent();

        FrameDragListener frameDragListener = new FrameDragListener(this);
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);
    }

    private void initComponent() {
        setTitle("ANAGRAM OSE FJALE TE NJEJTA");
        setSize(450, 350);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_COLOR);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
                g2.dispose();
            }
        };
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        mainPanel.setOpaque(false);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel header = new JLabel("ANAGRAM OSE FJALE TE NJEJTA\"", SwingConstants.LEFT);
        header.setFont(new Font("Inter", Font.BOLD, 22));
        header.setForeground(TEXT_COLOR);

        JButton btnClose = new JButton("✕");
        btnClose.setForeground(Color.GRAY);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> System.exit(0));

        headerPanel.add(header, BorderLayout.WEST);
        headerPanel.add(btnClose, BorderLayout.EAST);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        inputPanel.setOpaque(false);

        field1 = createModernField("Fjala e pare");
        field2 = createModernField("Fjala e dyte");

        inputPanel.add(field1);
        inputPanel.add(field2);

        btnCheck = new JButton("KONTROLLO") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnCheck.setContentAreaFilled(false);
        btnCheck.setBorderPainted(false);
        btnCheck.setFocusPainted(false);
        btnCheck.setBackground(ACCENT_COLOR);
        btnCheck.setForeground(Color.WHITE);
        btnCheck.setFont(new Font("Inter", Font.BOLD, 14));
        btnCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));

        labelStatus = new JLabel("Gati per analize", SwingConstants.CENTER);
        labelStatus.setForeground(Color.GRAY);
        labelStatus.setFont(new Font("Inter", Font.PLAIN, 12));

        JPanel footer = new JPanel(new BorderLayout(10, 10));
        footer.setOpaque(false);
        footer.add(btnCheck, BorderLayout.NORTH);
        footer.add(labelStatus, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        btnCheck.addActionListener(e -> performValidation());
        field1.addKeyListener(enterAdapter());
        field2.addKeyListener(enterAdapter());
    }

    private JTextField createModernField(String placeholder) {
        JTextField tf = new JTextField();
        tf.setBackground(new Color(45, 50, 56));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setFont(new Font("Inter", Font.PLAIN, 15));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(88, 143, 198), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        return tf;
    }

    private void performValidation() {
        String s1 = field1.getText().trim();
        String s2 = field2.getText().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            labelStatus.setText("Plotesoni te dyja fushat");
            labelStatus.setForeground(Color.ORANGE);
            return;
        }

        if (isAnagramOptimized(s1, s2)) {
            labelStatus.setText("anagrama!");
            labelStatus.setForeground(new Color(40, 167, 69));
            btnCheck.setBackground(new Color(40, 167, 69));
        } else {
            labelStatus.setText(" Nuk jane anagrama.");
            labelStatus.setForeground(new Color(220, 53, 69));
            btnCheck.setBackground(ACCENT_COLOR);
        }
    }

    private boolean isAnagramOptimized(String s1, String s2) {
        s1 = s1.toLowerCase().replaceAll("[^a-zëç]", "");
        s2 = s2.toLowerCase().replaceAll("[^a-zëç]", "");
        if (s1.length() != s2.length()) return false;
        int[] counts = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            counts[s1.charAt(i)]++;
            counts[s2.charAt(i)]--;
        }
        for (int c : counts) if (c != 0) return false;
        return true;
    }

    private KeyAdapter enterAdapter() {
        return new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) performValidation();
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush1Anagrama().setVisible(true));
    }

    public static class FrameDragListener extends MouseAdapter {
        private final JFrame frame;
        private Point mouseDownCompCoords = null;
        public FrameDragListener(JFrame frame) { this.frame = frame; }
        public void mouseReleased(MouseEvent e) { mouseDownCompCoords = null; }
        public void mousePressed(MouseEvent e) { mouseDownCompCoords = e.getPoint(); }
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
}