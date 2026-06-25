package UML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplikimGUI extends JFrame {

    private JTextField emriField;
    private JTextField IdField;
    private JTextField emailField;
    private JTextField mbiemriField;
    private JTextField atesiaField;
    private JTextField vitiField;
    private JTextField grupiField;
    private JLabel statusLabel;

    public AplikimGUI() {
        setTitle("Application Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        // Ush5Map panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Student Application Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Name:"), gbc);
        emriField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(emriField, gbc);

        // Student ID
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Student ID:"), gbc);
        IdField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(IdField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(emailField, gbc);

        // Mbiemri
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Mbiemri:"), gbc);
        mbiemriField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(mbiemriField, gbc);
        // Atesia
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Atesia:"), gbc);
        atesiaField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 5;
        mainPanel.add(atesiaField, gbc);
        // grupi
        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Grupi:"), gbc);
        grupiField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 6;
        mainPanel.add(grupiField, gbc);
        // Viti
        gbc.gridx = 0; gbc.gridy = 7;
        mainPanel.add(new JLabel("Viti:"), gbc);
        vitiField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 7;
        mainPanel.add(vitiField, gbc);


        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        // Status label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        mainPanel.add(statusLabel, gbc);

        // Submit action
        submitButton.addActionListener(e -> {
            String emri = emriField.getText().trim();
            String ID = IdField.getText().trim();
            String email = emailField.getText().trim();
            String mbiemri = mbiemriField.getText().trim();
            String atesia = atesiaField.getText().trim();
            String grupi = grupiField.getText().trim();
            String viti = vitiField.getText().trim();

            if (emri.isEmpty() || ID.isEmpty() || email.isEmpty()|| mbiemri.isEmpty() || atesia.isEmpty() || grupi.isEmpty()|| viti.isEmpty()) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Please fill in all fields.");
            } else if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Invalid email address.");
            } else {
                statusLabel.setForeground(new Color(121, 248, 121));
                statusLabel.setText("Submitted: " + emri + " | " + ID + " | " + email+" | " + mbiemri+ " | " + atesia+ " | " + grupi+" | " + viti);
            }
        });

        // Clear action
        clearButton.addActionListener(e -> {
            emriField.setText("");
            IdField.setText("");
            emailField.setText("");
            mbiemriField.setText("");
            atesiaField.setText("");
            grupiField.setText("");
            vitiField.setText("");


            statusLabel.setText(" ");
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplikimGUI::new);
    }
}

