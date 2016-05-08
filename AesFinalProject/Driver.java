
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * File: Driver.java
 * Assignment: Lab3
 * DueDate: 3/31/2016
 * This is a Driver.java file which contains a main method
 * runs the program to genrating cypherText
 * @author Murali Krishna, Bhargav Uppalapati.
 *
 */
/**
 * Driver Class consists the main method which starts the program by calling
 * methods in AesCipher.java
 */
public class Driver {
    public static void main(String args[]) throws FileNotFoundException {

        Scanner getInput = new Scanner(System.in);
        String text = getInput.nextLine();
        String key = "";
        String[] array = new String[text.length() / 4];
        if(getInput.hasNext())

            key = getInput.nextLine();

        if (text.length() != 32) {
            if (text.length() > 32) {
                int x = 0;
                String temp = "";
                for (int j = 0; j < text.length(); j++) {
                    if (temp.length() < 32) {
                        temp += text.charAt(j);
                    } else {
                        array[x] = temp;
                        temp = "";
                        temp += text.charAt(j);
                        x++;
                    }
                }
                array[x] = temp;
            }
            if (text.length() < 32) {
                array[0] = messagePadding(text);
            }
        } else if (text.length() == 32) {
            array[0] = text;
        } else {
            System.out.println("Invalid PlainText entered");
        }
        StringBuilder cipher = AesCipher.aes(array[0], key);
        System.out.println("");
        String decryptedText=Decryption.aes(cipher.toString(), key);
        getInput.close();

        String PaddedMsg="";
        for (int k = 0; k < decryptedText.length(); k++) {
            if (k == decryptedText.length() - 1) {
                int count = 0;
                 PaddedMsg = decryptedText.toString();
                String paddedVal = PaddedMsg.substring(30, 32);
                for (int i = 0; i < 16; i++) {
                    String val = PaddedMsg.charAt(i * 2) + "" + PaddedMsg.charAt(i * 2 + 1);
                    if (paddedVal.equals(val)) {
                        count++;
                    }
                }
                if (count == Integer.parseInt(paddedVal)) {
                    PaddedMsg = PaddedMsg.replace(paddedVal, "");
                }

            }
        }
        System.out.println(PaddedMsg.toUpperCase());

    }

    public static String messagePadding(String text) {
        StringBuffer str = new StringBuffer(text);
        int paddingSize = 32 - text.length();
        for (int i = 0; i < paddingSize / 2; i++) {
            str = str.append("0").append(paddingSize / 2);
        }
        return str.toString();
    }
}
