package Seminar7 ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ush1Mouse extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener {
    private JTextArea textArea;
    private JLabel statusLabel;

    public Ush1Mouse() {
        setTitle("Ngjarje Mouse Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Leviz mouse-ne panel, kliko, terhiq dhe rrotullo:"));

        textArea = new JTextArea(15, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);

        statusLabel = new JLabel("Status: Duke pritur ngjarje mouse");
        statusLabel.setBorder(BorderFactory.createTitledBorder("Status"));

        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.add(new JLabel("Kliko dhe terhiq kudo ne kete panel:"));
        eventPanel.add(Box.createVerticalStrut(10));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        EventListenerPanel eventListenerPanel = new EventListenerPanel();
        eventListenerPanel.addMouseListener(this);
        eventListenerPanel.addMouseMotionListener(this);
        eventListenerPanel.addMouseWheelListener(this);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.add(scrollPane, BorderLayout.CENTER);
        centerWrapper.add(eventListenerPanel, BorderLayout.NORTH);

        add(centerWrapper, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void log(String event) {
        textArea.append(event + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        log(String.format("[KLIKUAR] X: %d, Y: %d, Butoni: %s, Klikime: %d",
                e.getX(), e.getY(),
                e.getButton() == MouseEvent.BUTTON1 ? "Majtas" :
                e.getButton() == MouseEvent.BUTTON2 ? "Ne mes" : "Djathtas",
                e.getClickCount()));
        statusLabel.setText("Status: Mouse Klikuar ne (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        log(String.format("[SHTYPUR] X: %d, Y: %d, Butoni: %s",
                e.getX(), e.getY(),
                e.getButton() == MouseEvent.BUTTON1 ? "Majtas" :
                e.getButton() == MouseEvent.BUTTON2 ? "Ne mes" : "Djathtas"));
        statusLabel.setText("Status: Mouse Shtypur");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        log(String.format("[LIRUAR] X: %d, Y: %d", e.getX(), e.getY()));
        statusLabel.setText("Status: Mouse Liruar");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        log("[HYRI] Mouse hyri ne komponent");
        statusLabel.setText("Status: Mouse hyri ne komponent");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        log("[DAL] Mouse doli nga komponenti");
        statusLabel.setText("Status: Mouse doli nga komponenti");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        log(String.format("[TERHEQUR] X: %d, Y: %d", e.getX(), e.getY()));
        statusLabel.setText("Status: Mouse terhequr ne (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        log(String.format("[LEVIZUR] X: %d, Y: %d", e.getX(), e.getY()));
        statusLabel.setText("Status: Mouse ne (" + e.getX() + ", " + e.getY() + ")");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        log(String.format("[RROTULLIM] Njesite: %d, Sakt: %.2f, Rotacioni: %d",
                e.getUnitsToScroll(), e.getPreciseWheelRotation(), e.getWheelRotation()));
        statusLabel.setText("Status: Mouse Rrotulluar");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush1Mouse());
    }

    private class EventListenerPanel extends JPanel {
        public EventListenerPanel() {
            setPreferredSize(new Dimension(550, 150));
            setBackground(Color.LIGHT_GRAY);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.drawString("Panel Interaktiv - Provo klikimin, terhjeqjen dhe rrotullimin!", 20, 80);
        }
    }
}
