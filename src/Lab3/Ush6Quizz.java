import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Ush6Quizz extends JFrame {
    private ButtonGroup[] groups = new ButtonGroup[5];
    private JRadioButton[][] optionsArray = new JRadioButton[5][4];
    private JLabel scoreLabel;

    private int[] correctAnswers = {0, 1, 0, 1, 1};

    public Ush6Quizz() {
        setTitle("Quiz App");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        scoreLabel = new JLabel("Rezultati: 0/5");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scoreLabel);
        mainPanel.add(Box.createVerticalStrut(15));

        for (int i = 0; i < 5; i++) {
            groups[i] = new ButtonGroup();
        }

        addQuestion(mainPanel, 0, "1. Cili eshte kryeqyteti i Shqipërise?", "Tirana", "Durresi", "Shkodra", "Berati");
        addQuestion(mainPanel, 1, "2. Sa planete ka sistemi yjor?", "7", "8", "9", "10");
        addQuestion(mainPanel, 2, "3. Cila gjuhe programimi u krijua nga James Gosling?", "Java", "Python", "C++", "JavaScript");
        addQuestion(mainPanel, 3, "4. Cili është simboli i shumëzimit në Java?", "/", "*", "%", "+");
        addQuestion(mainPanel, 4, "5. Sa bit ka një byte?", "4", "8", "16", "32");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton submitBtn = new JButton("Përfundo Quiz");
        submitBtn.addActionListener(e -> calculateResult());

        JButton resetBtn = new JButton("Rifillo");
        resetBtn.addActionListener(e -> resetQuiz());

        buttonPanel.add(submitBtn);
        buttonPanel.add(resetBtn);

        mainPanel.add(buttonPanel);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);

        setLocationRelativeTo(null);
    }

    private void addQuestion(JPanel panel, int qIdx, String question, String... labels) {
        JLabel qLabel = new JLabel(question);
        qLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(qLabel);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 0));

        for (int i = 0; i < labels.length; i++) {
            optionsArray[qIdx][i] = new JRadioButton(labels[i]);
            groups[qIdx].add(optionsArray[qIdx][i]);
            p.add(optionsArray[qIdx][i]);
        }
        panel.add(p);
    }

    private void calculateResult() {
        int correctCount = 0;
        boolean allAnswered = true;

        for (int i = 0; i < 5; i++) {
            if (optionsArray[i][correctAnswers[i]].isSelected()) {
                correctCount++;
            }

            if (groups[i].getSelection() == null) {
                allAnswered = false;
            }
        }

        if (!allAnswered) {
            JOptionPane.showMessageDialog(this, "Ju lutem plotësoni te gjitha pyetjet!", "Kujdes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = (correctCount >= 3) ? "Urime" : "Rezultati";
        JOptionPane.showMessageDialog(this, "Keni " + correctCount + "/5 përgjigje të sakta.", title, JOptionPane.INFORMATION_MESSAGE);
        scoreLabel.setText("Rezultati: " + correctCount + "/5");
    }

    private void resetQuiz() {
        for (ButtonGroup g : groups) {
            g.clearSelection();
        }
        scoreLabel.setText("Rezultati: 0/5");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ush6Quizz().setVisible(true));
    }
}