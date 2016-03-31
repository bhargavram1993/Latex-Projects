
/**
 * file: Driver.java
 * author: Bhargav Uppalapati
 * course: MSCS 630 - Rivas
 * assignment: project 2
 * due date: MAR 31, 2016
 * 
 * This file contains the Programming of Advanced Encryption System(AES) algorithm.
 * This file also contains a driver class which reads input from a Key.txt and text case files. 
 * The input which is in the form
 * Hexadecimal key and Hexadecimal message is accessed by aescipher class.
 * The output is in the form of a cipher text.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Driver
 * 
 * This class implements a driver which reads and takes Hexadecimal key and
 * message input
 */
public class Driver {
  public static void main(String[] args) throws IOException {
    try {
      BufferedReader standardInput = new BufferedReader(
          new InputStreamReader(System.in));
      // Characters read by BufferedReader are taken as input and saved in
      // keyValue.
      String keyValue = standardInput.readLine();
      String inputMessage = standardInput.readLine();
      String text = inputMessage;
      // Instance is created and the first method
      // "inputtingStringIntoKMatrix" is called and input value is
      // passed to that method.
      String[] roundKeysMatrix = AEScipher.inputtingStringIntoKMatrix(keyValue);
      for (int i = 0; i < 11; i++) {
        StringBuilder val = new StringBuilder();
        String[][] aesAddKeyResult = AEScipher.aesAddKey(roundKeysMatrix[i],
            text);
        String[][] aesMixColumnResult = new String[4][4];
        String[][] aesShiftRowsResult = new String[4][4];
        String[][] aesNibbleResult = new String[4][4];
        if (i <= 9) {
          aesNibbleResult = AEScipher.aesNibbleSubstitution(aesAddKeyResult);
          aesShiftRowsResult = AEScipher.aesShiftRows(aesNibbleResult);
          if (i <= 8) {
            aesMixColumnResult = AEScipher.aesMixColumn(aesShiftRowsResult);
          }
        }
        for (int k = 0; k < 4; k++) {
          for (int j = 0; j < 4; j++) {
            if (i == 9) {
              val = val.append(aesShiftRowsResult[j][k]);
            } else if (i == 10) {
              val = val.append(aesAddKeyResult[j][k]);
            } else {
              val = val.append(aesMixColumnResult[j][k]);
            }
          }
        }
        text = val.toString().toUpperCase();
      }
      System.out.println(text);
    } catch (IOException excep) {
      System.err.println("Invalid Format in ");
    }
  }
}