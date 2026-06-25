package Seminar7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush2Koordinatat extends JFrame implements MouseListener {
    private JTextField textFieldX;
    private JTextField textFieldY;

    public Ush2Koordinatat() {
        setTitle("Koordinatat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        setSize(500, 400);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textFieldX = new JTextField(5);
        textFieldY = new JTextField(5);
        textFieldX.setEditable(false);
        textFieldY.setEditable(false);

        topPanel.add(new JLabel("X:"));
        topPanel.add(textFieldX);
        topPanel.add(new JLabel("Y:"));
        topPanel.add(textFieldY);

        add(topPanel, BorderLayout.NORTH);

        JPanel mainArea = new JPanel();
        mainArea.setBackground(Color.WHITE);
        mainArea.add(new JLabel("Kliko kudo"));

        mainArea.addMouseListener(this);
        add(mainArea, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        textFieldX.setText(String.valueOf(e.getX()));
        textFieldY.setText(String.valueOf(e.getY()));
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush2Koordinatat());
    }
}