package Seminar2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ush3Numeruesi2Safe {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ushtrimi 3 Menyra 2 (Safe Version)");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 4, 10, 10));

        JLabel label = new JLabel("Vlera:");
        JTextField textField = new JTextField("0", 20);
        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setForeground(Color.BLUE);

        JButton buttonPlus = new JButton("Rrit +1");
        JButton buttonMinus = new JButton("Zbrit -1");
        JButton buttonKatrori = new JButton("Katrori");
        JButton buttonZero = new JButton("Zero");
        JButton buttonKubi = new JButton("Kubi");
        JButton buttonShtoDhjet = new JButton("Shto +10");
        JButton buttonZbritDhjet = new JButton("Hiq -10");
        JButton buttonNdryshoShenje = new JButton("Ndrysho shenje");
        JButton buttonShto100 = new JButton ("Shto +100");
        JButton buttonPerimetriRrethit  = new JButton ("Perimetri i rrethit");
        JButton buttonSypRrethit = new JButton ("Syprina e rrethit");

        // Helper to get and validate number
        java.util.function.Function<String, Double> getNumber = (text) -> {
            if (text == null || text.trim().isEmpty()) {
                throw new NumberFormatException("Empty input");
            }
            try {
                return Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid number format");
            }
        };

        // Helper to update field with bounds check
        java.util.function.Consumer<Double> updateField = (num) -> {
            if (Double.isNaN(num)) {
                throw new ArithmeticException("Result is NaN");
            }
            if (Double.isInfinite(num)) {
                throw new ArithmeticException("Result overflow to Infinity");
            }
            textField.setText(String.format("%.6f", num));
        };

        buttonPlus.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri + 1);
                statusLabel.setText("Success: +1");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonMinus.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri - 1);
                statusLabel.setText("Success: -1");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonKatrori.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                double result = numri * numri;
                if (Double.isInfinite(result)) {
                    throw new ArithmeticException("Square overflow");
                }
                updateField.accept(result);
                statusLabel.setText("Success: Square");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonZero.addActionListener(e -> {
            textField.setText("0");
            statusLabel.setText("Set to 0");
        });

        buttonKubi.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                double result = numri * numri * numri;
                if (Double.isInfinite(result)) {
                    throw new ArithmeticException("Cube overflow");
                }
                updateField.accept(result);
                statusLabel.setText("Success: Cube");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonShtoDhjet.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri + 10);
                statusLabel.setText("Success: +10");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonZbritDhjet.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri - 10);
                statusLabel.setText("Success: -10");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonNdryshoShenje.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri * -1);
                statusLabel.setText("Success: Negated");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonShto100.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                updateField.accept(numri + 100);
                statusLabel.setText("Success: +100");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonPerimetriRrethit.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                if (numri < 0) {
                    throw new ArithmeticException("Radius cannot be negative");
                }
                double result = 2 * Math.PI * numri;
                if (Double.isInfinite(result)) {
                    throw new ArithmeticException("Radius too large");
                }
                updateField.accept(result);
                statusLabel.setText("Success: Circumference");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        buttonSypRrethit.addActionListener(e -> {
            try {
                double numri = getNumber.apply(textField.getText());
                if (numri < 0) {
                    throw new ArithmeticException("Radius cannot be negative");
                }
                double result = Math.PI * numri * numri;
                if (Double.isInfinite(result)) {
                    throw new ArithmeticException("Radius too large");
                }
                updateField.accept(result);
                statusLabel.setText("Success: Area");
            } catch (Exception ex) {
                handleError(frame, statusLabel, ex.getMessage());
            }
        });

        frame.add(label);
        frame.add(textField);
        frame.add(statusLabel);
        frame.add(new JLabel()); // spacer

        frame.add(buttonPlus);
        frame.add(buttonMinus);
        frame.add(buttonKatrori);
        frame.add(buttonKubi);

        frame.add(buttonShtoDhjet);
        frame.add(buttonZbritDhjet);
        frame.add(buttonShto100);
        frame.add(buttonNdryshoShenje);

        frame.add(buttonPerimetriRrethit);
        frame.add(buttonSypRrethit);
        frame.add(buttonZero);
        frame.add(new JLabel()); // spacer

        frame.setVisible(true);
    }

    private static void handleError(JFrame parent, JLabel statusLabel, String message) {
        statusLabel.setText("Error: " + message);
        statusLabel.setForeground(Color.RED);
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
        statusLabel.setForeground(Color.BLUE);
    }
}
