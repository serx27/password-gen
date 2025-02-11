import javax.swing.*;
import java.security.SecureRandom;

public class Main {
    // Zeichen-Pool
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*()-_=+[]{}|;:,.<>?/";
    // GUI starten
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI_Swing::new);
    }

    public static String generatePassword(int length, boolean useUppercase, boolean useLowercase, boolean useDigits, boolean useSpecialCharacters) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length has to be greater than 0!");
        }

        // Erstellen des Passwortes basierend auf gewählten Optionen
        String characterPool = "";
        if (useUppercase) characterPool += UPPERCASE;
        if (useLowercase) characterPool += LOWERCASE;
        if (useDigits) characterPool += DIGITS;
        if (useSpecialCharacters) characterPool += SPECIAL_CHARACTERS;

        if (characterPool.isEmpty()) {
            throw new IllegalArgumentException("Please check at least one option!");
        }

        // Erstelle den Zufallsgenerator
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Passwortlänge
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(randomIndex));
        }

        return password.toString();
    }
}
