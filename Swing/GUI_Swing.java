import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Swing extends JFrame {
    private JTextField passwordLengthField;
    private JCheckBox uppercaseCheckbox;
    private JCheckBox lowercaseCheckbox;
    private JCheckBox digitsCheckbox;
    private JCheckBox specialCharactersCheckbox;
    private JTextField passwordField;

    public GUI_Swing() {
        // Fenstereinstellungen
        setTitle("Password-Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(7, 1));

        // Passwortlänge
        JPanel lengthPanel = new JPanel(new FlowLayout());
        lengthPanel.add(new JLabel("Password Length:"));
        passwordLengthField = new JTextField("12", 5); // Defaultwert 12
        lengthPanel.add(passwordLengthField);
        add(lengthPanel);

        // Generiertes Passwort anzeigen
        passwordField = new JTextField();
        passwordField.setEditable(false);
        add(passwordField);

        // Checkboxen für Optionen
        uppercaseCheckbox = new JCheckBox("Uppercase");
        lowercaseCheckbox = new JCheckBox("Lowercase");
        digitsCheckbox = new JCheckBox("Digits");
        specialCharactersCheckbox = new JCheckBox("Special Characters");

        add(uppercaseCheckbox);
        add(digitsCheckbox);
        add(lowercaseCheckbox);
        add(specialCharactersCheckbox);

        // Copy-Button
        JButton copyButton = new JButton("Copy");
        copyButton.setEnabled(false); // aktiviert erst, wenn ein Passwort generiert wurde
        add(copyButton);

        // Event-Listener für Copy-Button
        copyButton.addActionListener(e -> {
            String password = passwordField.getText();
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No password to copy!", "Error", JOptionPane.ERROR_MESSAGE);
            }
                else {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                        new StringSelection(password), null);
                JOptionPane.showMessageDialog(this, "Password has been copied!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        // Generate-Button
        JButton generateButton = new JButton("Generate Password");
        add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                generatePassword();
                copyButton.setEnabled(true);

            }
        });


        // Fenster sichtbar machen
        setVisible(true);
    }

    private void generatePassword() {
        try {
            // Passwortlänge auslesen
            int length = Integer.parseInt(passwordLengthField.getText());

            // Optionen aus Checkboxen auslesen
            boolean useUppercase = uppercaseCheckbox.isSelected();
            boolean useLowercase = lowercaseCheckbox.isSelected();
            boolean useDigits = digitsCheckbox.isSelected();
            boolean useSpecialCharacters = specialCharactersCheckbox.isSelected();

            // Passwort generieren
            String password = Main.generatePassword(length, useUppercase, useLowercase, useDigits, useSpecialCharacters);

            // Ergebnis anzeigen
            passwordField.setText(password);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input for password length!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
