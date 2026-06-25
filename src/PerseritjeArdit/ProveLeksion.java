//package PerseritjeArdit;
////Leksion 17/03/2026
////krijo dicka duke perdorur nje nga layoutet
//import javax.swing.*;
//import java.awt.*;
//
//public class ProveLeksion extends JFrame {
//
//    public ProveLeksion() {
//        setTitle("Java Swing");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(900, 700);
//        setLocationRelativeTo(null);
//
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        tabbedPane.addTab("BorderLayout", createBorderLayoutPanel());
//        tabbedPane.addTab("FlowLayout", createFlowLayoutPanel());
//        tabbedPane.addTab("GridLayout", createGridLayoutPanel());
//        tabbedPane.addTab("BoxLayout", createBoxLayoutPanel());
//        tabbedPane.addTab("GridBagLayout", createGridBagLayoutPanel());
//        tabbedPane.addTab("GroupLayout", createGroupLayoutPanel());
//        tabbedPane.addTab("Complex", createComplexPanel());
//
//        add(tabbedPane);
//        setVisible(true);
//    }
//
//    private JPanel createBorderLayoutPanel() {
//        JPanel panel = new JPanel(new BorderLayout(10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JButton northBtn = new JButton("North");
//        JButton southBtn = new JButton("South");
//        JButton eastBtn = new JButton("East");
//        JButton westBtn = new JButton("West");
//        JButton centerBtn = new JButton("Center");
//
//        panel.add(northBtn, BorderLayout.NORTH);
//        panel.add(southBtn, BorderLayout.SOUTH);
//        panel.add(eastBtn, BorderLayout.EAST);
//        panel.add(westBtn, BorderLayout.WEST);
//        panel.add(centerBtn, BorderLayout.CENTER);
//
//        return panel;
//    }
//
//    private JPanel createFlowLayoutPanel() {
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//
//        JButton btn1 = new JButton("Button 1");
//        JButton btn2 = new JButton("Button 2");
//        JButton btn3 = new JButton("Button 3");
//        JCheckBox checkBox = new JCheckBox("Check Me");
//        JRadioButton radioBtn = new JRadioButton("Radio");
//        JTextField textField = new JTextField(10);
//
//        panel.add(btn1);
//        panel.add(btn2);
//        panel.add(btn3);
//        panel.add(checkBox);
//        panel.add(radioBtn);
//        panel.add(textField);
//
//        return panel;
//    }
//
//    private JPanel createGridLayoutPanel() {
//        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        for (int i = 1; i <= 9; i++) {
//            JButton btn = new JButton("Btn " + i);
//            panel.add(btn);
//        }
//
//        return panel;
//    }
//
//    private JPanel createBoxLayoutPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JLabel label1 = new JLabel("Name:");
//        JTextField nameField = new JTextField(20);
//        JLabel label2 = new JLabel("Password:");
//        JPasswordField passwordField = new JPasswordField(20);
//        JCheckBox rememberCheck = new JCheckBox("Remember me");
//        JButton submitBtn = new JButton("Submit");
//
//        panel.add(label1);
//        panel.add(nameField);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(label2);
//        panel.add(passwordField);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(rememberCheck);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(submitBtn);
//        panel.add(Box.createVerticalGlue());
//
//        return panel;
//    }
//
//    private JPanel createGridBagLayoutPanel() {
//        JPanel panel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        gbc.gridx = 0; gbc.gridy = 0;
//        panel.add(new JLabel("Username:"), gbc);
//
//        gbc.gridx = 1;
//        panel.add(new JTextField(15), gbc);
//
//        gbc.gridx = 0; gbc.gridy = 1;
//        panel.add(new JLabel("Email:"), gbc);
//
//        gbc.gridx = 1;
//        panel.add(new JTextField(15), gbc);
//
//        gbc.gridx = 0; gbc.gridy = 2;
//        gbc.gridwidth = 2;
//        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        checkPanel.add(new JCheckBox("Admin"));
//        checkPanel.add(new JCheckBox("User"));
//        panel.add(checkPanel, gbc);
//
//        gbc.gridy = 3;
//        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        btnPanel.add(new JButton("Save"));
//        btnPanel.add(new JButton("Cancel"));
//        panel.add(btnPanel, gbc);
//
//        return panel;
//    }
//
//    private JPanel createGroupLayoutPanel() {
//        JPanel panel = new JPanel();
//        GroupLayout layout = new GroupLayout(panel);
//        panel.setLayout(layout);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        JLabel nameLbl = new JLabel("Full Name:");
//        JTextField nameField = new JTextField();
//        JLabel ageLbl = new JLabel("Age:");
//        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
//        JLabel genderLbl = new JLabel("Gender:");
//        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
//        JCheckBox termsCheck = new JCheckBox("I accept terms");
//        JButton registerBtn = new JButton("Register");
//
//        layout.setHorizontalGroup(
//            layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//                    .addComponent(nameLbl)
//                    .addComponent(ageLbl)
//                    .addComponent(genderLbl))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                    .addComponent(nameField)
//                    .addComponent(ageSpinner)
//                    .addComponent(genderCombo)
//                    .addComponent(termsCheck)
//                    .addComponent(registerBtn))
//        );
//
//        layout.setVerticalGroup(
//            layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(nameLbl)
//                    .addComponent(nameField))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(ageLbl)
//                    .addComponent(ageSpinner))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(genderLbl)
//                    .addComponent(genderCombo))
//                .addComponent(termsCheck)
//                .addComponent(registerBtn)
//        );
//
//        return panel;
//    }
//
//    private JPanel createComplexPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        topPanel.add(new JLabel("Application Title"));
//        topPanel.add(new JTextField(20));
//        topPanel.add(new JButton("Search"));
//        panel.add(topPanel, BorderLayout.NORTH);
//
//        JPanel leftPanel = new JPanel();
//        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//        leftPanel.add(new JLabel("Categories"));
//        leftPanel.add(new JCheckBox("Java"));
//        leftPanel.add(new JCheckBox("Python"));
//        leftPanel.add(new JCheckBox("C++"));
//        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        panel.add(leftPanel, BorderLayout.WEST);
//
//        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
//        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 0; gbc.gridy = 0;
//        formPanel.add(new JLabel("Name:"), gbc);
//        gbc.gridx = 1;
//        formPanel.add(new JTextField(15), gbc);
//        gbc.gridx = 0; gbc.gridy = 1;
//        formPanel.add(new JLabel("City:"), gbc);
//        gbc.gridx = 1;
//        JComboBox<String> cityCombo = new JComboBox<>(new String[]{"Prishtina", "Tirana", "Shkup"});
//        formPanel.add(cityCombo, gbc);
//        centerPanel.add(formPanel);
//
//        JPanel radioPanel = new JPanel();
//        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
//        radioPanel.add(new JLabel("Select Option:"));
//        ButtonGroup bg = new ButtonGroup();
//        JRadioButton rb1 = new JRadioButton("Option 1");
//        JRadioButton rb2 = new JRadioButton("Option 2");
//        JRadioButton rb3 = new JRadioButton("Option 3");
//        bg.add(rb1); bg.add(rb2); bg.add(rb3);
//        radioPanel.add(rb1); radioPanel.add(rb2); radioPanel.add(rb3);
//        centerPanel.add(radioPanel);
//
//        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
//        JList<String> list = new JList<>(items);
//        JScrollPane listScroll = new JScrollPane(list);
//        listScroll.setBorder(BorderFactory.createTitledBorder("List"));
//        centerPanel.add(listScroll);
//
//        JTextArea textArea = new JTextArea(5, 20);
//        JScrollPane textScroll = new JScrollPane(textArea);
//        textScroll.setBorder(BorderFactory.createTitledBorder("Text Area"));
//        centerPanel.add(textScroll);
//
//        panel.add(centerPanel, BorderLayout.CENTER);
//
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        bottomPanel.add(new JButton("OK"));
//        bottomPanel.add(new JButton("Cancel"));
//        bottomPanel.add(new JButton("Apply"));
//        panel.add(bottomPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ProveLeksion());
//    }
//}
