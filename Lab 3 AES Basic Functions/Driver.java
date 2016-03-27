
/**
 * file: aescipher.java
 * author: Bhargav Uppalapati
 * course: MSCS 630 - Rivas
 * assignment: project 1
 * due date: FEB 23, 2016
 * 
 * This file contains the Programming of Advanced Encryption System(AES).
 * This file also contains a driver class which reads input from a Key.txt file. The input which is in the form
 * Hexadecimal key is accessed by aescipher class.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * driver
 * 
 * This class implements a driver which reads and takes Hexadecimal key input
 * from a .txt file
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		try {
			BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Key");
			String keyValue = standardInput.readLine();
			// Characters read by BufferedReader are taken as input and saved in
			// keyValue.
			AEScipher cipher = new AEScipher();
			// An instance to a class aescipher is created
			cipher.inputtingStringIntoKMatrix(keyValue);
			// Instance is created and the first method
			// "inputtingStringIntoKMatrix" is called and input value is
			// passed to that method.
		} catch (IOException excep) {
			System.err.println("Invalid Format in ");
		}
	}
}