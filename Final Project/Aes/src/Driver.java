import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * file: Driver.java
 * author: Murali Krishna, Bhargav Uppalapati.
 * course: MSCS 630 - Rivas
 * assignment: Lab3
 * due date: 3/31/2016
 * 
 * Driver Class consists the main method which starts the program by calling
 * methods in AesCipher.java
 */
public class Driver {
  public static void main(String args[]) throws FileNotFoundException {
    Scanner getInput = new Scanner(System.in);
    String text = getInput.nextLine();
    while (getInput.hasNext()) {
      String key = getInput.nextLine();
      //AesCipher.aes(text,key);
      Decryption.aes(text, key);
      //Decryption.aesMixColumn();
    }
    getInput.close();
  }
}