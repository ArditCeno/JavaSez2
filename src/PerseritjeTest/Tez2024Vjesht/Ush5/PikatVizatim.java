// 5. (20 pikë) Ndërtoni një program që lexon një arraylist me koordinata pikash dhe i vizaton
// ato në një dritare. Leximi i të dhënave bëhet nga një skedar tekst në arraylist. Formati i
// të dhënave ka shumë rreshta dhe çdo rresht ka një vlerë për X dhe Y.

package PerseritjeTest.Tez2024Vjesht.Ush5;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class PikatVizatim extends JPanel {
    private List<Point> pikat;

    public PikatVizatim(List<Point> pikat) {
        this.pikat = pikat;
        setPreferredSize(new Dimension(600, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (Point p : pikat) {
            g.fillOval(p.x - 3, p.y - 3, 6, 6);
        }
    }

    public static List<Point> lexoPikat(String emriSkedarit) throws IOException {
        List<Point> pikat = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(emriSkedarit))) {
            String rresht;
            while ((rresht = br.readLine()) != null) {
                String[] pjese = rresht.trim().split("\\s+");
                if (pjese.length == 2) {
                    int x = Integer.parseInt(pjese[0]);
                    int y = Integer.parseInt(pjese[1]);
                    pikat.add(new Point(x, y));
                }
            }
        }
        return pikat;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vizatimi i pikave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnZgjedh = new JButton("Zgjidh skedarin");
        btnZgjedh.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Point> pikat = lexoPikat(fc.getSelectedFile().getAbsolutePath());
                    frame.getContentPane().removeAll();
                    frame.add(new PikatVizatim(pikat), BorderLayout.CENTER);
                    frame.add(btnZgjedh, BorderLayout.NORTH);
                    frame.revalidate();
                    frame.repaint();
                    frame.pack();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Gabim: " + ex.getMessage());
                }
            }
        });

        frame.add(btnZgjedh, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
