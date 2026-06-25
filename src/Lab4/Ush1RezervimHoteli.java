package Lab4;

import javax.swing.*;
import java.awt.*;

public class Ush1RezervimHoteli extends JFrame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 450;

    private JTextField emriField;
    private JComboBox<String> roomTypeCombo;
    private JCheckBox mengjesCheck, parkingCheck, wifiCheck, extrabedCheck;
    private JRadioButton cashRadio, cardRadio, halfRadio;
    private JTextArea summaryArea;
    private JButton rezervoButton;

    public Ush1RezervimHoteli() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Rezervim Hoteli");
        createComponents();
        setVisible(true);
    }

    public void createComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Emri:"));
        emriField = new JTextField(25);
        namePanel.add(emriField);

        JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomPanel.add(new JLabel("Tipi i dhomes:"));
        String[] rooms = {"Single", "Double", "Suite", "Mberterore"};
        roomTypeCombo = new JComboBox<>(rooms);
        roomPanel.add(roomTypeCombo);

        JPanel servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));
        servicesPanel.add(new JLabel("Shërbime shtese"));
        mengjesCheck = new JCheckBox("Mengjes");
        parkingCheck = new JCheckBox("Parking");
        wifiCheck = new JCheckBox("WiFi");
        extrabedCheck = new  JCheckBox("Extra Bed");

        servicesPanel.add(mengjesCheck);
        servicesPanel.add(parkingCheck);
        servicesPanel.add(wifiCheck);
        servicesPanel.add(extrabedCheck);

        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.add(new JLabel("Menyra e pageses:"));
        cashRadio = new JRadioButton("Cash");
        cardRadio = new JRadioButton("Card");
        halfRadio = new JRadioButton ("Some card and some cash");
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cashRadio);
        paymentGroup.add(cardRadio);
        paymentPanel.add(cashRadio);
        paymentPanel.add(cardRadio);
        paymentGroup.add(halfRadio);
        paymentPanel.add(halfRadio);

        rezervoButton = new JButton("Rezervo");
        rezervoButton.addActionListener(e -> showSummary());

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.add(new JLabel("Permbledhja "), BorderLayout.NORTH);
        summaryArea = new JTextArea(8, 40);
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(summaryArea);
        summaryPanel.add(scrollPane);

        mainPanel.add(namePanel);
        mainPanel.add(roomPanel);
        mainPanel.add(servicesPanel);
        mainPanel.add(paymentPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(rezervoButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(summaryPanel);

        add(mainPanel);
    }

    private void showSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Emri-- ").append(emriField.getText()).append("\n");
        summary.append("Tipi i dhomese-- ").append(roomTypeCombo.getSelectedItem()).append("\n");

        summary.append("Sherbime shtese-- ");
        boolean hasServices = false;
        if (mengjesCheck.isSelected()) {
            summary.append("Mengjes-- ");
            hasServices = true;
        }
        if (parkingCheck.isSelected()) {
            summary.append("Parking-- ");
            hasServices = true;
        }
        if (wifiCheck.isSelected()) {
            summary.append("WiFi-- ");
            hasServices = true;
        }
        if (extrabedCheck.isSelected()) {
            summary.append("ExtraBed-- ");
            hasServices = true;
        }
        if (!hasServices) {
            summary.append("Asnje--");
        }
        summary.append("\n");

        summary.append("Menyra e pageses-- ");
        if (cashRadio.isSelected()) {
            summary.append("Cash\n");
        } else if (cardRadio.isSelected()) {
            summary.append("Card\n");
        } else if (halfRadio.isSelected()) {
            summary.append("50-50\n");
        } else {
            summary.append("Nuk eshte zgjedhur\n");
        }

        summaryArea.setText(summary.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush1RezervimHoteli());
    }
}
