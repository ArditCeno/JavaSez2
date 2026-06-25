package Seminar6;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

abstract class Objekti {
    protected int x, y;
    protected Color ngjyra;
    protected int id;

    public Objekti(int x, int y, Color ngjyra, int id) {
        this.x = x;
        this.y = y;
        this.ngjyra = ngjyra;
        this.id = id;
    }

    public abstract void vizato(Graphics2D g2d);
    public abstract Rectangle getBounds();
    public boolean përmbanPikën(int px, int py) { return getBounds().contains(px, py); }
    public int getId() { return id; }
    public String getTipi() { return "Objekt"; }
}

class Shtepi extends Objekti {
    private int gjerësia = 80;
    private int lartësia = 65;

    public Shtepi(int x, int y, Color ngjyra, int id) {
        super(x, y, ngjyra, id);
    }

    @Override
    public void vizato(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 0, 40));
        g2d.fillRect(x + 6, y + 6, gjerësia, lartësia);

        GradientPaint muret = new GradientPaint(x, y, ngjyra, x + gjerësia, y + lartësia, ngjyra.darker());
        g2d.setPaint(muret);
        g2d.fillRect(x, y, gjerësia, lartësia);

        g2d.setColor(new Color(139, 90, 43));
        int[] xP = {x - 5, x + gjerësia / 2, x + gjerësia + 5};
        int[] yP = {y, y - 40, y};
        g2d.fillPolygon(xP, yP, 3);

        g2d.setColor(new Color(100, 149, 237));
        g2d.fillRect(x + 12, y + 25, 18, 22);
        g2d.fillRect(x + 50, y + 25, 18, 22);

        g2d.setColor(new Color(101, 67, 33));
        g2d.fillRect(x + 32, y + 30, 16, 35);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 9));
        g2d.drawString("S-" + id, x + 2, y - 45);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 5, y - 40, gjerësia + 10, lartësia + 45);
    }

    @Override
    public String getTipi() { return "Shtepi"; }
}

class Makinë extends Objekti {
    private int gjerësia = 65;
    private int lartësia = 30;
    private boolean drejtimiMajtas;

    public Makinë(int x, int y, Color ngjyra, int id, boolean drejtimiMajtas) {
        super(x, y, ngjyra, id);
        this.drejtimiMajtas = drejtimiMajtas;
    }

    @Override
    public void vizato(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint trupi = new GradientPaint(x, y, ngjyra, x, y + lartësia, ngjyra.darker());
        g2d.setPaint(trupi);
        g2d.fillRoundRect(x, y, gjerësia, lartësia, 8, 8);

        g2d.fillRoundRect(x + 15, y - 8, gjerësia - 30, lartësia + 8, 6, 6);

        g2d.setColor(new Color(200, 220, 255));
        g2d.fillRect(x + 18, y - 4, 20, 12);
        g2d.fillRect(x + 42, y - 4, 12, 12);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 7));
        g2d.drawString("M-" + id, x + 2, y - 12);

        g2d.setColor(new Color(50, 50, 50));
        g2d.fillOval(x + 10, y + 15, 14, 14);
        g2d.fillOval(x + 40, y + 15, 14, 14);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(x + gjerësia - 4, y + 6, 4, 5);
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y + 6, 4, 5);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 2, y - 12, gjerësia + 4, lartësia + 18);
    }

    @Override
    public String getTipi() { return "Makinë"; }
}

class Peme extends Objekti {
    private int madhësia;
    private int trunguLartësi;

    public Peme(int x, int y, Color ngjyra, int id) {
        super(x, y, ngjyra, id);
        this.madhësia = 35 + (int)(Math.random() * 20);
        this.trunguLartësi = 20 + (int)(Math.random() * 10);
    }

    @Override
    public void vizato(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint trungu = new GradientPaint(x + 10, y, new Color(101, 67, 33), x + 18, y, new Color(60, 40, 20));
        g2d.setPaint(trungu);
        g2d.fillRect(x + 10, y, 12, trunguLartësi);

        GradientPaint gjethe = new GradientPaint(x, y - madhësia, ngjyra, x + madhësia, y, ngjyra.darker());
        g2d.setPaint(gjethe);

        g2d.fillOval(x - 8, y - madhësia - 5, madhësia + 16, madhësia + 12);
        g2d.fillOval(x - 3, y - madhësia / 2 - 8, madhësia / 2 + 5, madhësia / 2 + 5);
        g2d.fillOval(x + madhësia / 2, y - madhësia / 2 - 8, madhësia / 2 + 5, madhësia / 2 + 5);
        g2d.fillOval(x + madhësia / 4, y - madhësia - 15, madhësia / 2, madhësia / 2);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 8));
        g2d.drawString("P-" + id, x - 5, y + trunguLartësi + 15);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - 10, y - madhësia - trunguLartësi, madhësia + 25, madhësia + trunguLartësi + 20);
    }

    @Override
    public String getTipi() { return "Pemë"; }
}

class Lule extends Objekti {
    private int madhësia;

    public Lule(int x, int y, Color ngjyra, int id) {
        super(x, y, ngjyra, id);
        this.madhësia = 8 + (int)(Math.random() * 5);
    }

    @Override
    public void vizato(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(50, 150, 50));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x, y + 3, x, y + 22);
        g2d.drawLine(x - 5, y + 10, x, y + 14);
        g2d.drawLine(x + 5, y + 8, x, y + 12);

        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(i * 72);
            int px = x + (int)(Math.cos(angle) * madhësia);
            int py = y + (int)(Math.sin(angle) * madhësia);
            g2d.setColor(ngjyra);
            g2d.fillOval(px - 5, py - 5, 10, 10);
        }

        g2d.setColor(new Color(255, 200, 0));
        g2d.fillOval(x - 4, y - 4, 8, 8);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 7));
        g2d.drawString("L-" + id, x - 8, y + 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - madhësia - 6, y - madhësia - 6, (madhësia + 6) * 2, 40);
    }

    @Override
    public String getTipi() { return "Lule"; }
}

class PanelQyteti extends JPanel {
    private ArrayList<Objekti> objektet = new ArrayList<>();
    private int shtepiID = 1, makinëID = 1, pemëID = 1, luleID = 1;
    private int rrugëY;
    private Objekti iZgjedhur;
    private boolean natë = false;

    public PanelQyteti() {
        setPreferredSize(new Dimension(1000, 550));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                iZgjedhur = gjejObjektin(e.getX(), e.getY());
                firePropertyChange("objektZgjodhur", null, iZgjedhur);
                repaint();
            }
        });
    }

    public Objekti gjejObjektin(int px, int py) {
        for (int i = objektet.size() - 1; i >= 0; i--) {
            if (objektet.get(i).përmbanPikën(px, py)) {
                return objektet.get(i);
            }
        }
        return null;
    }

    public Objekti getObjektiZgjedhur() { return iZgjedhur; }

    public void toggleNatë() {
        natë = !natë;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        rrugëY = getHeight() / 2;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (natë) {
            GradientPaint qielli = new GradientPaint(0, 0, new Color(10, 10, 40), 0, rrugëY - 50, new Color(30, 30, 70));
            g2d.setPaint(qielli);
            g2d.fillRect(0, 0, getWidth(), rrugëY - 50);

            g2d.setColor(Color.WHITE);
            for (int i = 0; i < 50; i++) {
                int sx = (i * 73) % getWidth();
                int sy = (i * 37) % (rrugëY - 60);
                g2d.fillOval(sx, sy, 2 + (i % 3), 2 + (i % 3));
            }

            GradientPaint hëna = new GradientPaint(80, 50, Color.WHITE, 100, 60, Color.LIGHT_GRAY);
            g2d.setPaint(hëna);
            g2d.fillOval(70, 40, 50, 50);
        } else {
            GradientPaint qielli = new GradientPaint(0, 0, new Color(100, 180, 255), 0, rrugëY - 50, new Color(180, 220, 255));
            g2d.setPaint(qielli);
            g2d.fillRect(0, 0, getWidth(), rrugëY - 50);

            g2d.setColor(Color.WHITE);
            g2d.fillOval(200, 45, 80, 35);
            g2d.fillOval(240, 40, 60, 30);
            g2d.fillOval(500, 35, 90, 40);
            g2d.fillOval(550, 30, 70, 35);
            g2d.fillOval(750, 50, 75, 32);

            GradientPaint dielli = new GradientPaint(80, 50, Color.YELLOW, 100, 70, Color.ORANGE);
            g2d.setPaint(dielli);
            g2d.fillOval(70, 40, 60, 60);
        }

        GradientPaint bari = new GradientPaint(0, rrugëY - 50, new Color(50, 180, 50), 0, rrugëY + 50, new Color(30, 120, 30));
        g2d.setPaint(bari);
        g2d.fillRect(0, rrugëY - 50, getWidth(), 100);

        g2d.setColor(new Color(60, 180, 60));
        for (int i = 0; i < getWidth(); i += 10) {
            g2d.drawLine(i, rrugëY - 50, i, rrugëY - 45);
            g2d.drawLine(i + 5, rrugëY + 45, i + 5, rrugëY + 50);
        }

        GradientPaint bariPoshte = new GradientPaint(0, rrugëY + 50, new Color(50, 180, 50), 0, getHeight(), new Color(30, 120, 30));
        g2d.setPaint(bariPoshte);
        g2d.fillRect(0, rrugëY + 50, getWidth(), getHeight() - rrugëY - 50);

        GradientPaint rruga = new GradientPaint(0, rrugëY - 40, new Color(90, 90, 90), 0, rrugëY + 40, new Color(50, 50, 50));
        g2d.setPaint(rruga);
        g2d.fillRect(0, rrugëY - 40, getWidth(), 80);

        g2d.setColor(Color.WHITE);
        for (int i = 0; i < getWidth(); i += 60) {
            g2d.fillRect(i, rrugëY - 3, 40, 6);
        }

        g2d.setColor(new Color(180, 180, 180));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(0, rrugëY - 40, getWidth(), rrugëY - 40);
        g2d.drawLine(0, rrugëY + 40, getWidth(), rrugëY + 40);

        for (Objekti obj : objektet) {
            if (obj == iZgjedhur) {
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(3));
                g2d.draw(obj.getBounds());
            }
            obj.vizato(g2d);
        }
    }

    public void shtoShtepi(Color ngjyra, boolean majtas) {
        int posX, posY;
        if (majtas) {
            posX = 30 + (int)(Math.random() * (getWidth() / 2 - 130));
            posY = rrugëY - 150;
        } else {
            posX = getWidth() / 2 + 30 + (int)(Math.random() * (getWidth() / 2 - 130));
            posY = rrugëY + 85;
        }
        objektet.add(new Shtepi(posX, posY, ngjyra, shtepiID++));
        repaint();
    }

    public void shtoMakinë(Color ngjyra, boolean majtas) {
        int posX = 30 + (int)(Math.random() * (getWidth() - 100));
        int posY = majtas ? rrugëY - 35 : rrugëY + 5;
        objektet.add(new Makinë(posX, posY, ngjyra, makinëID++, majtas));
        repaint();
    }

    public void shtoPeme(Color ngjyra, boolean majtas) {
        int posX, posY;
        if (majtas) {
            posX = 30 + (int)(Math.random() * (getWidth() / 2 - 50));
            posY = rrugëY - 75;
        } else {
            posX = getWidth() / 2 + 30 + (int)(Math.random() * (getWidth() / 2 - 50));
            posY = rrugëY + 45;
        }
        objektet.add(new Peme(posX, posY, ngjyra, pemëID++));
        repaint();
    }

    public void shtoLule(Color ngjyra, boolean majtas) {
        int posX, posY;
        if (majtas) {
            posX = 30 + (int)(Math.random() * (getWidth() / 2 - 30));
            posY = rrugëY - 35;
        } else {
            posX = getWidth() / 2 + 30 + (int)(Math.random() * (getWidth() / 2 - 30));
            posY = rrugëY + 60;
        }
        objektet.add(new Lule(posX, posY, ngjyra, luleID++));
        repaint();
    }

    public void fshiObjekt(Objekti obj) {
        if (obj != null) {
            objektet.remove(obj);
            if (iZgjedhur == obj) iZgjedhur = null;
            repaint();
        }
    }

    public void pastro() {
        objektet.clear();
        iZgjedhur = null;
        shtepiID = 1;
        makinëID = 1;
        pemëID = 1;
        luleID = 1;
        repaint();
    }

    public int getNumri(String tipi) {
        int count = 0;
        for (Objekti obj : objektet) {
            if (obj.getTipi().equals(tipi)) count++;
        }
        return count;
    }

    public int getTotal() { return objektet.size(); }
}

public class Ush4Qyteti extends JFrame {
    private PanelQyteti panelQyteti;
    private JComboBox<String> cmbLloji, cmbNgjyra, cmbAnësi;
    private JButton btnShto, btnFshi, btnPastro, btnNatë;
    private JLabel lblInfo, lblStatistika;
    private Color[] ngjyrat = {
        new Color(200, 50, 50), new Color(50, 100, 200), new Color(34, 139, 34),
        new Color(255, 215, 0), new Color(255, 140, 0), new Color(148, 0, 211),
        new Color(255, 105, 180), new Color(128, 128, 128), Color.WHITE, new Color(255, 255, 200)
    };
    private String[] emratNgjyrave = {
        "E Kuqe", "Blu", "Gjelbër", "Verdhë", "Portokalli", "Vjollcë", "Rozë", "Hiri", "E Bardhë", "Bezhë"
    };

    public Ush4Qyteti() {
        setTitle("Dizenjimi i Qytetit");
        setSize(1050, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel panelKontrollit = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        panelKontrollit.setBorder(BorderFactory.createTitledBorder("Kontrollet"));

        panelKontrollit.add(new JLabel("Lloji:"));
        cmbLloji = new JComboBox<>(new String[]{"Shtepi", "Makinë", "Pemë", "Lule"});
        cmbLloji.setPreferredSize(new Dimension(120, 30));
        panelKontrollit.add(cmbLloji);

        panelKontrollit.add(new JLabel("Ngjyra:"));
        cmbNgjyra = new JComboBox<>(emratNgjyrave);
        cmbNgjyra.setPreferredSize(new Dimension(120, 30));
        panelKontrollit.add(cmbNgjyra);

        panelKontrollit.add(new JLabel("Pozita:"));
        cmbAnësi = new JComboBox<>(new String[]{"Majtas", "Djathtas"});
        cmbAnësi.setPreferredSize(new Dimension(100, 30));
        panelKontrollit.add(cmbAnësi);

        panelKontrollit.add(Box.createHorizontalStrut(10));

        btnShto = new JButton("Shto");
        btnShto.setPreferredSize(new Dimension(90, 35));
        btnShto.setBackground(new Color(34, 139, 34));
        btnShto.setForeground(Color.WHITE);
        btnShto.setFocusPainted(false);
        panelKontrollit.add(btnShto);

        btnFshi = new JButton("Fshi");
        btnFshi.setPreferredSize(new Dimension(90, 35));
        btnFshi.setBackground(new Color(178, 34, 34));
        btnFshi.setForeground(Color.WHITE);
        btnFshi.setFocusPainted(false);
        panelKontrollit.add(btnFshi);

        btnNatë = new JButton("Natë");
        btnNatë.setPreferredSize(new Dimension(80, 35));
        btnNatë.setBackground(new Color(50, 50, 100));
        btnNatë.setForeground(Color.WHITE);
        btnNatë.setFocusPainted(false);
        panelKontrollit.add(btnNatë);

        btnPastro = new JButton("Pastro");
        btnPastro.setPreferredSize(new Dimension(90, 35));
        btnPastro.setBackground(new Color(100, 100, 100));
        btnPastro.setForeground(Color.WHITE);
        btnPastro.setFocusPainted(false);
        panelKontrollit.add(btnPastro);

        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblInfo = new JLabel("Shtyp Shto per te shtuar | Kliko mbi objekt per zgjedhur");
        panelInfo.add(lblInfo);

        JPanel panelStat = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelStat.setBorder(new TitledBorder("Statistika"));
        lblStatistika = new JLabel("Shtëpi: 0 | Makina: 0 | Pemë: 0 | Lule: 0 | Gjithsej: 0");
        panelStat.add(lblStatistika);

        JPanel panelNord = new JPanel();
        panelNord.setLayout(new BoxLayout(panelNord, BoxLayout.Y_AXIS));
        panelNord.add(panelKontrollit);
        panelNord.add(panelInfo);
        panelNord.add(panelStat);
        add(panelNord, BorderLayout.NORTH);

        panelQyteti = new PanelQyteti();
        panelQyteti.addPropertyChangeListener("objektZgjodhur", e -> {
            Objekti obj = (Objekti) e.getNewValue();
            if (obj != null) {
                lblInfo.setText("<html><b>Zgjedhur:</b> " + obj.getTipi() + " #" + obj.getId() + "</html>");
            } else {
                lblInfo.setText("Shtyp Shto per te shtuar | Kliko mbi objekt per zgjedhur");
            }
        });
        add(panelQyteti, BorderLayout.CENTER);

        JPanel panelJug = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelJug.setBorder(new TitledBorder("Legjenda"));
        panelJug.add(new JLabel("Shtepi - ne bar | Makina - ne rruge | Pemë/Lule - anës rrugës"));
        add(panelJug, BorderLayout.SOUTH);

        btnShto.addActionListener(e -> shtoObjekt());
        btnFshi.addActionListener(e -> {
            panelQyteti.fshiObjekt(panelQyteti.getObjektiZgjedhur());
            përditësoStatistikën();
        });
        btnNatë.addActionListener(e -> {
            panelQyteti.toggleNatë();
            btnNatë.setText(btnNatë.getText().equals("Natë") ? "Ditë" : "Natë");
        });
        btnPastro.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "A jeni i sigurt?", "Konfirmo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                panelQyteti.pastro();
                përditësoStatistikën();
            }
        });

        përditësoStatistikën();
        setVisible(true);
    }

    private void shtoObjekt() {
        String lloji = (String) cmbLloji.getSelectedItem();
        Color ngjyra = ngjyrat[cmbNgjyra.getSelectedIndex()];
        boolean majtas = cmbAnësi.getSelectedItem().equals("Majtas");

        switch (lloji) {
            case "Shtepi": panelQyteti.shtoShtepi(ngjyra, majtas); break;
            case "Makinë": panelQyteti.shtoMakinë(ngjyra, majtas); break;
            case "Pemë": panelQyteti.shtoPeme(ngjyra, majtas); break;
            case "Lule": panelQyteti.shtoLule(ngjyra, majtas); break;
        }
        përditësoStatistikën();
    }

    private void përditësoStatistikën() {
        lblStatistika.setText("Shtëpi: " + panelQyteti.getNumri("Shtepi") + 
                             " | Makina: " + panelQyteti.getNumri("Makinë") + 
                             " | Pemë: " + panelQyteti.getNumri("Pemë") +
                             " | Lule: " + panelQyteti.getNumri("Lule") +
                             " | Gjithsej: " + panelQyteti.getTotal());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush4Qyteti());
    }
}
