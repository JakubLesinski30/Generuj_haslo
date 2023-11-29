import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Generuj extends JFrame {

    private static final String ZNAKI = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-=[{]}<>.?/!@#$%^&*()_+";

    public Generuj() {
        setTitle("Generator Haseł");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 320);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#FFECB3"));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.decode("#FFECB3"));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton generujButton = new JButton("Generuj Ponownie");
        generujButton.setBackground(Color.decode("#536DFE"));
        generujButton.setForeground(Color.BLACK);
        generujButton.setFont(new Font("Arial", Font.BOLD, 18));

        generujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generujIPokazHasla(textArea);
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(generujButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        generujIPokazHasla(textArea);
    }

    private void generujIPokazHasla(JTextArea textArea) {
        int iloscHasel = 11;
        int dlugoscHasla = 12;
    
        textArea.setText("");
        for (int i = 0; i < iloscHasel; i++) {
            String wygenerowaneHaslo = generujHaslo(dlugoscHasla);
            textArea.append(wygenerowaneHaslo);
            if (i < iloscHasel - 1) {
                textArea.append("\n");
            }
        }
    
        Font font = new Font("Arial", Font.BOLD, 17);
        textArea.setFont(font);
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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Generuj generuj = new Generuj();
                generuj.setResizable(false);
                generuj.setVisible(true);
            }
        });
    }
}
