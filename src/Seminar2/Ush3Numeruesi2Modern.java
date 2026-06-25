package Seminar2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.function.Function;

public class Ush3Numeruesi2Modern {
    private static final Color DARK_BG = new Color(30, 30, 35);
    private static final Color DARK_PANEL = new Color(45, 45, 50);
    private static final Color BUTTON_NORMAL = new Color(60, 60, 70);
    private static final Color BUTTON_HOVER = new Color(80, 80, 90);
    private static final Color BUTTON_CLICK = new Color(50, 50, 60);
    private static final Color ACCENT_COLOR = new Color(0, 150, 255);
    private static final Color SUCCESS_COLOR = new Color(0, 200, 100);
    private static final Color ERROR_COLOR = new Color(255, 70, 70);
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Color PLACEHOLDER_COLOR = new Color(150, 150, 150);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        JFrame frame = new JFrame("Calculator Pro");
        frame.setSize(550, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(DARK_BG);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(DARK_BG);
        JLabel titleLabel = new JLabel(" Ushtrimi 3 Calculator");
        titleLabel.setForeground(ACCENT_COLOR);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(10, 15, 5, 15));
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JTextField textField = new JTextField("0");
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(DARK_PANEL);
        textField.setForeground(TEXT_COLOR);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(60, 60, 70), 2),
                new EmptyBorder(10, 15, 10, 15)
        ));
        textField.setEditable(true);

        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setForeground(SUCCESS_COLOR);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setBorder(new EmptyBorder(5, 0, 5, 0));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(DARK_BG);
        displayPanel.add(textField, BorderLayout.CENTER);
        displayPanel.add(statusLabel, BorderLayout.SOUTH);

        mainPanel.add(displayPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4, 8, 8));
        buttonsPanel.setBackground(DARK_BG);

        String[][] buttonLabels = {
            {"+1", "-1", "x²", "x³"},
            {"+10", "-10", "+100", "+/-"},
            {"Perim", "SIp", "Zero", "PAstro"},
            {"Rrit", "Zbrit", "Katrori", "Kubi"}
        };

        JButton[] buttons = createStyledButtons(buttonLabels);
        for (JButton btn : buttons) {
            buttonsPanel.add(btn);
        }

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(DARK_BG);
        JLabel infoLabel = new JLabel("Calculator by A.Ceno");
        infoLabel.setForeground(PLACEHOLDER_COLOR);
        infoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        bottomPanel.add(infoLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);

        setupButtonActions(buttons, textField, statusLabel, frame);

        frame.setVisible(true);
    }

    private static JButton[] createStyledButtons(String[][] labels) {
        int totalButtons = labels.length * labels[0].length;
        JButton[] buttons = new JButton[totalButtons];
        int index = 0;

        for (String[] row : labels) {
            for (String label : row) {
                JButton btn = new JButton(label);
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                btn.setBackground(BUTTON_NORMAL);
                btn.setForeground(TEXT_COLOR);
                btn.setBorder(new LineBorder(new Color(70, 70, 80), 1));
                btn.setFocusPainted(false);
                btn.setUI(new BasicButtonUI());

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btn.setBackground(BUTTON_HOVER);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        btn.setBackground(BUTTON_NORMAL);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        btn.setBackground(BUTTON_CLICK);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        btn.setBackground(BUTTON_HOVER);
                    }
                });

                buttons[index++] = btn;
            }
        }
        return buttons;
    }

    private static void setupButtonActions(JButton[] buttons, JTextField textField, JLabel statusLabel, JFrame frame) {
        Function<String, Double> getNumber = (text) -> {
            if (text == null || text.trim().isEmpty()) {
                throw new NumberFormatException("Empty input");
            }
            try {
                return Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid number format");
            }
        };

        Consumer<Double> updateField = (num) -> {
            if (Double.isNaN(num)) {
                throw new ArithmeticException("Result is NaN");
            }
            if (Double.isInfinite(num)) {
                throw new ArithmeticException("Result overflow");
            }
            textField.setText(String.format("%.6f", num));
        };

        Consumer<String> setStatus = (msg) -> {
            statusLabel.setText(msg);
            statusLabel.setForeground(SUCCESS_COLOR);
        };

        Consumer<String> setError = (msg) -> {
            statusLabel.setText("Error: " + msg);
            statusLabel.setForeground(ERROR_COLOR);
            JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setForeground(SUCCESS_COLOR);
        };

        buttons[0].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n + 1);
            setStatus.accept("+1 applied");
        }, setError));

        buttons[1].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n - 1);
            setStatus.accept("-1 applied");
        }, setError));

        buttons[2].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            double result = n * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Square overflow");
            updateField.accept(result);
            setStatus.accept("Katrori");
        }, setError));

        buttons[3].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            double result = n * n * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Cube overflow");
            updateField.accept(result);
            setStatus.accept("Kubi");
        }, setError));

        buttons[4].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n + 10);
            setStatus.accept("+10 applied");
        }, setError));

        buttons[5].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n - 10);
            setStatus.accept("-10 applied");
        }, setError));

        buttons[6].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n + 100);
            setStatus.accept("+100 applied");
        }, setError));

        buttons[7].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n * -1);
            setStatus.accept("SHenja u nderrua");
        }, setError));

        buttons[8].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            if (n < 0) throw new ArithmeticException("Rezja jo negatiev");
            double result = 2 * Math.PI * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Radius too large");
            updateField.accept(result);
            setStatus.accept("Circumference calculated");
        }, setError));

        buttons[9].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            if (n < 0) throw new ArithmeticException("Radius cannot be negative");
            double result = Math.PI * n * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Radius too large");
            updateField.accept(result);
            setStatus.accept("Area calculated");
        }, setError));

        buttons[10].addActionListener(e -> {
            textField.setText("0");
            statusLabel.setText("Reset to 0");
            statusLabel.setForeground(SUCCESS_COLOR);
        });

        buttons[11].addActionListener(e -> {
            textField.setText("");
            textField.requestFocus();
            statusLabel.setText("Cleared");
            statusLabel.setForeground(SUCCESS_COLOR);
        });

        // Additional action buttons (mapped differently)
        buttons[12].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n + 1);
            setStatus.accept("Rrit +1");
        }, setError));

        buttons[13].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            updateField.accept(n - 1);
            setStatus.accept("Zbrit -1");
        }, setError));

        buttons[14].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            double result = n * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Square overflow");
            updateField.accept(result);
            setStatus.accept("Katrori");
        }, setError));

        buttons[15].addActionListener(e -> performCalc(() -> {
            double n = getNumber.apply(textField.getText());
            double result = n * n * n;
            if (Double.isInfinite(result)) throw new ArithmeticException("Cube overflow");
            updateField.accept(result);
            setStatus.accept("Kubi");
        }, setError));
    }

    private static void performCalc(Runnable calc, Consumer<String> setError) {
        try {
            calc.run();
        } catch (Exception ex) {
            setError.accept(ex.getMessage());
        }
    }
}