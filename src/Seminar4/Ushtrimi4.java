package seminar4;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Ushtrimi4 extends JFrame {

    private JTextField textEmri;
    private JComboBox<String> comboMadhesia;
    private JCheckBox checkDjathe;
    private JCheckBox checkKarpudha;
    private JCheckBox checkUllinj;
    private JRadioButton radioNeLokal;
    private JRadioButton radioMeVete;
    private JTextArea textAreaPorosia;

    public Ushtrimi4() {
        setTitle("Porosia e Pices");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelForma = new JPanel(new GridLayout(6, 1, 5, 5));

        panelForma.add(new JLabel("Emri i klientit "));
        textEmri = new JTextField();
        panelForma.add(textEmri);

        panelForma.add(new JLabel("Madhesia e pices "));
        comboMadhesia = new JComboBox<>(new String[]{"E vogel", "Mesatare", "E madhe"});
        panelForma.add(comboMadhesia);

        JPanel panelCheckbox = new JPanel(new GridLayout(1, 3));
        checkDjathe = new JCheckBox("Djathe ekstra");
        checkKarpudha = new JCheckBox("Karpudha");
        checkUllinj = new JCheckBox("Ullinje Berati ;p");
        panelCheckbox.add(checkDjathe);
        panelCheckbox.add(checkKarpudha);
        panelCheckbox.add(checkUllinj);
        panelForma.add(panelCheckbox);

        JPanel panelRadio = new JPanel(new GridLayout(1, 2));
        radioNeLokal = new JRadioButton("Ne lokal");
        radioMeVete = new JRadioButton("Me vete");
        ButtonGroup group = new ButtonGroup();
        group.add(radioNeLokal);
        group.add(radioMeVete);
        radioNeLokal.setSelected(true);
        panelRadio.add(radioNeLokal);
        panelRadio.add(radioMeVete);
        panelForma.add(panelRadio);

        JButton btnPerfundo = new JButton("Perfundo porosine");
        panelForma.add(btnPerfundo);

        textAreaPorosia = new JTextArea();
        textAreaPorosia.setEditable(false);
        textAreaPorosia.setText("Porosia do te shfaqet ketu");

        add(panelForma, BorderLayout.NORTH);
        add(textAreaPorosia, BorderLayout.CENTER);

        btnPerfundo.addActionListener(e -> perfundoPorosine());

        setVisible(true);
    }

    private void perfundoPorosine() {
        String emri = textEmri.getText().trim();

        if (emri.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ju lutem vendosni emrin e klientit", "Gabim", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String madhesia = (String) comboMadhesia.getSelectedItem();

        StringBuilder sb = new StringBuilder();
        sb.append("Emri: ").append(emri).append("\n");
        sb.append("Madhesia: ").append(madhesia).append("\n");
        sb.append("Perbersit shtese:\n");

        if (checkDjathe.isSelected()) sb.append("  - Djathe ekstra\n");
        if (checkKarpudha.isSelected()) sb.append("  - Karpudha\n");
        if (checkUllinj.isSelected()) sb.append("  - Ullinje\n");

        if (!checkDjathe.isSelected() && !checkKarpudha.isSelected() && !checkUllinj.isSelected()) {
            sb.append("  (asnje)\n");
        }

        sb.append("\nMenyra e marrjes: ");
        if (radioNeLokal.isSelected()) {
            sb.append("Ne lokal\n");
        } else {
            sb.append("Me vete\n");
        }

        textAreaPorosia.setText(sb.toString());
    }
}
