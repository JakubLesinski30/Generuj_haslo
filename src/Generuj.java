import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class Generuj extends JFrame {

    private static final String ZNAKI = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-=[{]}<>.?/!@#$%^&*()_+";

    private JButton generujButton;
    private JPanel passwordsPanel;

    public Generuj() {
        setTitle("Generator Haseł");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#FFECB3"));

        passwordsPanel = new JPanel();
        passwordsPanel.setLayout(new BoxLayout(passwordsPanel, BoxLayout.Y_AXIS));
        passwordsPanel.setBackground(Color.decode("#FFECB3"));
        JScrollPane scrollPane = new JScrollPane(passwordsPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.decode("#FFECB3"));

        JLabel labelIloscHasel = new JLabel("Ilość haseł:");
        JTextField fieldIloscHasel = new JTextField(5);

        JLabel labelDlugoscHasla = new JLabel("Długość hasła:");
        JTextField fieldDlugoscHasla = new JTextField(5);

        generujButton = new JButton("Generuj");
        generujButton.setBackground(Color.decode("#536DFE"));
        generujButton.setForeground(Color.WHITE);
        generujButton.setFont(new Font("Arial", Font.BOLD, 18));

        generujButton.addActionListener(e -> {
            try {
                int iloscHasel = Integer.parseInt(fieldIloscHasel.getText());
                int dlugoscHasla = Integer.parseInt(fieldDlugoscHasla.getText());
                generujIPokazHasla(passwordsPanel, iloscHasel, dlugoscHasla);
                generujButton.setText("Generuj Ponownie");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Wprowadź poprawne liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(labelIloscHasel);
        inputPanel.add(fieldIloscHasel);
        inputPanel.add(labelDlugoscHasla);
        inputPanel.add(fieldDlugoscHasla);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(generujButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void generujIPokazHasla(JPanel passwordsPanel, int iloscHasel, int dlugoscHasla) {
        passwordsPanel.removeAll();
        passwordsPanel.revalidate();
        passwordsPanel.repaint();

        for (int i = 0; i < iloscHasel; i++) {
            String wygenerowaneHaslo = generujHaslo(dlugoscHasla);

            JPanel rowPanel = new JPanel(new BorderLayout());
            rowPanel.setBackground(Color.decode("#FFECB3"));

            JTextArea hasloTextArea = new JTextArea(wygenerowaneHaslo);
            hasloTextArea.setEditable(false);
            hasloTextArea.setBackground(Color.decode("#FFECB3"));
            hasloTextArea.setLineWrap(true);

            JButton kopiujButton = new JButton("Kopiuj");
            kopiujButton.setBackground(Color.BLACK);
            kopiujButton.setForeground(Color.WHITE);

            kopiujButton.addActionListener(e -> skopiujDoSchowka(wygenerowaneHaslo));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBackground(Color.decode("#FFECB3"));
            buttonPanel.add(kopiujButton);

            rowPanel.add(hasloTextArea, BorderLayout.CENTER);
            rowPanel.add(buttonPanel, BorderLayout.EAST);

            passwordsPanel.add(rowPanel);
        }

        revalidate();
        repaint();
    }

    private void skopiujDoSchowka(String tekst) {
        StringSelection stringSelection = new StringSelection(tekst);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(null, "Skopiowano do schowka: " + tekst);
    }

    private String generujHaslo(int dlugosc) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(dlugosc);

        for (int i = 0; i < dlugosc; i++) {
            int indeks = secureRandom.nextInt(ZNAKI.length());
            char znak = ZNAKI.charAt(indeks);
            sb.append(znak);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Generuj generuj = new Generuj();
            generuj.setResizable(false);
            generuj.setVisible(true);
        });
    }
}
