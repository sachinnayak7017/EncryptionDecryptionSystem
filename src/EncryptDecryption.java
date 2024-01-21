import java.io.*;
import java.util.Scanner;

public class EncryptDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Caesar Cipher File Encryption/Decryption program!");

        System.out.print("Enter the path of the text file to encrypt/decrypt: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the key for encryption/decryption (an integer value): ");
        int key = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Do you want to encrypt or decrypt the file? (Enter 'encrypt' or 'decrypt'): ");
        String operation = scanner.nextLine().toLowerCase();

        if (operation.equals("encrypt")) {
            encryptFile(filePath, key);
            System.out.println("Encryption completed successfully.");
        } else if (operation.equals("decrypt")) {
            decryptFile(filePath, key);
            System.out.println("Decryption completed successfully.");
        } else {
            System.out.println("Invalid operation. Please enter 'encrypt' or 'decrypt'.");
        }

        scanner.close();
    }

    private static void encryptFile(String filePath, int key) {
        processFile(filePath, key, true);
    }

    private static void decryptFile(String filePath, int key) {
        processFile(filePath, key, false);
    }

    private static void processFile(String filePath, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputFileName(filePath, encrypt)))) {

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char encryptedChar = encryptDecrypt((char) currentChar, key, encrypt);
                writer.write(encryptedChar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char encryptDecrypt(char character, int key, boolean encrypt) {
        if (Character.isLetter(character)) {
            char base = Character.isLowerCase(character) ? 'a' : 'A';
            int shifted = (character - base + (encrypt ? key : -key) + 26) % 26;
            return (char) (base + shifted);
        } else {
            return character; // Do not encrypt/decrypt non-alphabetic characters
        }
    }

    private static String getOutputFileName(String filePath, boolean encrypt) {
        String suffix = encrypt ? "_encrypted" : "_decrypted";
        int dotIndex = filePath.lastIndexOf('.');
        return filePath.substring(0, dotIndex) + suffix + filePath.substring(dotIndex);
    }
}


