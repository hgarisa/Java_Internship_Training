package Java_IO.Pipes.Chat_Encryption;

/*
  A simple Caesar cipher utility for encrypting/decrypting messages.
 */

public class CaesarCipher
{
    // Shift each character by this value
    private static final int SHIFT = 3;

    public static String encrypt(String plainText) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            encrypted.append((char)(ch + SHIFT));
        }
        return encrypted.toString();
    }

    public static String decrypt(String encryptedText) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : encryptedText.toCharArray()) {
            decrypted.append((char)(ch - SHIFT));
        }
        return decrypted.toString();
    }
}
