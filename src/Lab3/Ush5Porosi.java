import javax.swing.*;
import java.awt.*;

public class Ush5Porosi extends JFrame {
    private JCheckBox waterCheck;
    private JCheckBox coffeeCheck;
    private JCheckBox teaCheck;
    private JButton orderButton;
    private JButton clearButton;
    private JLabel summaryLabel;
    private JLabel priceLabel;

    private static final double WATER_PRICE = 50;
    private static final double COFFEE_PRICE = 100;
    private static final double TEA_PRICE = 70;

    public Ush5Porosi() {
        setTitle("Porosia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        setSize(380, 200);

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(BorderFactory.createTitledBorder("Zgjidhni pijet:"));

        waterCheck = new JCheckBox("Uje - 50 Lek\u00eb");
        coffeeCheck = new JCheckBox("Kafe - 100 Lek\u00eb");
        teaCheck = new JCheckBox("\u00c7aj - 70 Lek\u00eb");

        waterCheck.addItemListener(e -> updatePrice());
        coffeeCheck.addItemListener(e -> updatePrice());
        teaCheck.addItemListener(e -> updatePrice());

        checkboxPanel.add(waterCheck);
        checkboxPanel.add(coffeeCheck);
        checkboxPanel.add(teaCheck);

        orderButton = new JButton("Porosit");
        clearButton = new JButton("Anulo");

        summaryLabel = new JLabel("Nuk keni zgjedhur asnj\u00eb pije");
        summaryLabel.setPreferredSize(new Dimension(300, 25));
        summaryLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        priceLabel = new JLabel("Totali: 0 Lek\u00eb");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(0, 100, 0));

        orderButton.addActionListener(e -> placeOrder());
        clearButton.addActionListener(e -> clearSelection());

        add(checkboxPanel);
        add(orderButton);
        add(clearButton);
        add(summaryLabel);
        add(priceLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updatePrice() {
        double total = 0;
        StringBuilder summary = new StringBuilder("Porosia: ");
        int count = 0;

        if (waterCheck.isSelected()) {
            total += WATER_PRICE;
            if (count > 0) summary.append(", ");
            summary.append("Uje");
            count++;
        }
        if (coffeeCheck.isSelected()) {
            total += COFFEE_PRICE;
            if (count > 0) summary.append(", ");
            summary.append("Kafe");
            count++;
        }
        if (teaCheck.isSelected()) {
            total += TEA_PRICE;
            if (count > 0) summary.append(", ");
            summary.append("\u00c7aj");
            count++;
        }

        if (count == 0) {
            summaryLabel.setText("Nuk keni zgjedhur asnj\u00eb pije");
            orderButton.setEnabled(false);
        } else {
            summaryLabel.setText(summary.toString());
            orderButton.setEnabled(true);
        }

        priceLabel.setText(String.format("Totali: %.0f Lek\u00eb", total));
    }

    private void placeOrder() {
        StringBuilder order = new StringBuilder("Porosia juaj:\n");
        double total = 0;

        if (waterCheck.isSelected()) {
            order.append("- Uje: 50 Lek\u00eb\n");
            total += WATER_PRICE;
        }
        if (coffeeCheck.isSelected()) {
            order.append("- Kafe: 100 Lek\u00eb\n");
            total += COFFEE_PRICE;
        }
        if (teaCheck.isSelected()) {
            order.append("- \u00c7aj: 70 Lek\u00eb\n");
            total += TEA_PRICE;
        }

        order.append("\nTotali: ").append(String.format("%.0f Lek\u00eb", total));

        JOptionPane.showMessageDialog(this,
            order.toString(),
            "Konfirmimi i Porosis\u00eb",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearSelection() {
        waterCheck.setSelected(false);
        coffeeCheck.setSelected(false);
        teaCheck.setSelected(false);
        updatePrice();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush5Porosi().setVisible(true));
    }
}
