/**
 * file: aescipher.java
 * author: Bhargav Uppalapati
 * course: MSCS 630 - Rivas
 * assignment: project 1
 * due date: FEB 23, 2016
 * 
 * This file contains the Programming of Advanced Encryption System(AES).
 * One important part of the AES is how it produces keys on every round of encryption type.
 */
/**
 * aescipher
 * 
 * This class implements a linked list with single forward links, and supports
 * dynamic addition and deletion of nodes.
 */
public class AEScipher {
	public static String[][] inputKeyMatrix = new String[4][4];
	// Creating a 4x4 Matrix
	public static String[][] finalMatrix = new String[4][44];

	// Creating a 4x44 Matrix
	/**
	 * inputtingStringIntoKMatrix This method takes 128-bit encryption key from
	 * driver.java class and inserts it in a 4x4 Matrix.
	 */
	public void inputtingStringIntoKMatrix(String key) {
		try {
			int keyTraversalCounter = 0;
			for (int kMatrixColumn = 0; kMatrixColumn < 4; kMatrixColumn++) {
				for (int kMatrixRow = 0; kMatrixRow < 4; kMatrixRow = kMatrixRow + 1) {
					inputKeyMatrix[kMatrixRow][kMatrixColumn] = key.substring(keyTraversalCounter,
							keyTraversalCounter + 2);
					// The key is traversed and every two characters are split
					// and inserted into matrix.
					keyTraversalCounter = keyTraversalCounter + 2;
				}
			}
			copyingIntoWHexMatrix();
			// Control flow of the program, copyingIntoWHexMatrix() method is
			// called.
		} catch (Exception ex) {
			System.err.println(
					"Error occured in inputtingStringIntoKMatrix mathod, generation of input 4x4 matrix not possible ");
		}
	}

	/**
	 * copyingIntoWHexMatrix
	 * 
	 * copyingIntoWHexMatrix() method is invoked by inputtingStringIntoKMatrix()
	 * method. Values from 4x4 inputKeyMatrix are copied into new 4x44
	 * generatedKeyMatrix.
	 */

	public static void copyingIntoWHexMatrix() {
		try {
			for (int wMatrixRow = 0; wMatrixRow < 4; wMatrixRow = wMatrixRow + 1) {
				for (int wMatrixColumn = 0; wMatrixColumn < 4; wMatrixColumn++) {
					finalMatrix[wMatrixRow][wMatrixColumn] = inputKeyMatrix[wMatrixRow][wMatrixColumn];
					// Values from 4x4 inputKeyMatrix are copied into new 4x44
					// generatedKeyMatrix.

				}
			}
			finalKeyMatrix();
		} catch (Exception cpy) {
			System.err.println(
					"Error occured in copyingIntoWHexMatrix() method, couldn't copy values from 4x4 martix to 4x44 matrix ");
		}
	}

	public static final String[][] sBoxTable = {
			{ "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76" },
			{ "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0" },
			{ "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15" },
			{ "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75" },
			{ "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84" },
			{ "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF" },
			{ "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8" },
			{ "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2" },
			{ "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73" },
			{ "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB" },
			{ "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79" },
			{ "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08" },
			{ "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A" },
			{ "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E" },
			{ "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF" },
			{ "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" } };

	// sBoxTable is referenced by sBoxCipher to transform bytes, output byte is
	// obtained by the given corresponding input byte.
	// For example if the input byte is 8B the corresponding transformed
	// output byte will be 3D, because you take 8 as the row and B as the column
	// in the table, which points to 3D.
	/**
	 * sBoxCipher
	 *
	 * This function computes the the integer equivalent to a hexadecimal
	 * format, keys from sBoxTable are fetched and split.
	 * 
	 * Parameters: sBoxTableInput:This parameter takes input from sBoxTable
	 */
	public static String sBoxCipher(String sBoxTableInput) {
		try {
			String keys = sBoxTable[Integer.parseInt(sBoxTableInput.split("")[0], 16)][Integer
					.parseInt(sBoxTableInput.split("")[1], 16)];
			//System.out.println(Keys);
			return keys;
		} catch (Exception sBox) {
			System.err.println("Error occured in sBoxCipher method, Could'nt return keys from sBox ");
			return "";
		}
	}

	public static final String[][] rConTable = {
			{ "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A" },
			{ "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39" },
			{ "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A" },
			{ "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8" },
			{ "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF" },
			{ "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC" },
			{ "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B" },
			{ "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3" },
			{ "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94" },
			{ "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20" },
			{ "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35" },
			{ "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F" },
			{ "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04" },
			{ "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63" },
			{ "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD" },
			{ "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D" } };

	// rConTable values are fetched by rConCipher method.
	/**
	 * rConCipher
	 * 
	 * Parameters: rConTableInput: parameter fetches input from rConTable
	 */
	public static String rConCipher(int rConTableInput) {
		try {
			String rConOutput = rConTable[0][rConTableInput];
			return rConOutput;
		} catch (Exception rCon) {
			System.err.println("Error occured in rConCipher method, Couldn't return keys from rCon");
			return "";
		}
	}

	/**
	 * finalKeyMatrix
	 *
	 * This function computes the keys and inserts the keys in a 4x44 matrix
	 * column wise starting from the 5th column, by fetching inputs from the
	 * user and calculates round keys using sBoxTable and rConTable. The first 4
	 * columns are filled, for the rest 40 columns we do the following
	 * operations.
	 * 
	 */
	public static void finalKeyMatrix() {
		try {
			String[][] temporaryMatrix = null;
			for (int finalMatrixColumn = 4; finalMatrixColumn < 44; finalMatrixColumn++) {
				if (finalMatrixColumn % 4 != 0) {
					// If the finalMatrixColumn index is not a multiple of 4.
					// We XOR as denoted in the following xorCaliculation:
					for (int finalMatrixRow = 0; finalMatrixRow < 4; finalMatrixRow++) {
						finalMatrix[finalMatrixRow][finalMatrixColumn] = xorCaliculation(
								finalMatrix[finalMatrixRow][finalMatrixColumn - 4],
								finalMatrix[finalMatrixRow][finalMatrixColumn - 1]);
					}
				} else {
					// If the finalMatrixColumn index is a multiple of 4 then we
					// compute the following.
					temporaryMatrix = new String[1][4];
					// For the construction of a new column we will use the
					// elements of the previous column.
					// and store them into a temporary matrix.
					temporaryMatrix[0][0] = finalMatrix[0][finalMatrixColumn - 1];
					temporaryMatrix[0][1] = finalMatrix[1][finalMatrixColumn - 1];
					temporaryMatrix[0][2] = finalMatrix[2][finalMatrixColumn - 1];
					temporaryMatrix[0][3] = finalMatrix[3][finalMatrixColumn - 1];
					// Then we perform a shift to the left as follows.
					temporaryMatrix[0][0] = finalMatrix[1][finalMatrixColumn - 1];
					temporaryMatrix[0][1] = finalMatrix[2][finalMatrixColumn - 1];
					temporaryMatrix[0][2] = finalMatrix[3][finalMatrixColumn - 1];
					temporaryMatrix[0][3] = finalMatrix[0][finalMatrixColumn - 1];
					// Next we transform each of the four bytes into temporary
					// using an S-box function.
					for (int i = 0; i < 1; i++) {
						for (int j = 0; j < 4; j++) {
							temporaryMatrix[i][j] = sBoxCipher(temporaryMatrix[i][j]);
						}
					}
					int r = finalMatrixColumn / 4;
					// Get the Rcon(j) constant for the j-th round by using the
					// rConTable,Perform an XOR operation using the
					// corresponding round constant obtained
					temporaryMatrix[0][0] = xorCaliculation(rConCipher(r), temporaryMatrix[0][0]);
					for (int row = 0; row < 4; row++) {
						// finally, finalMatrix can be obtained by xoring
						// previous column with the temporary matrix.
						finalMatrix[row][finalMatrixColumn] = xorCaliculation(finalMatrix[row][finalMatrixColumn - 4],
								temporaryMatrix[0][row]);
					}
				}
			}
			printingFinalKeyMatrix();
		} catch (Exception rCon) {
			System.err.println(
					"Error occured in finalKeyMatrix() method, Couldn't perform left shift and XOR operations ");
		}
	}

	/**
	 * printingFinalKeyMatrix
	 *
	 * This function prints the final matrix
	 * 
	 */
	public static void printingFinalKeyMatrix() {
		try {
			int printCounter = 0;
			int b = 0;
			// printing 4x4 matrix 11 times.
			while (printCounter < 11) {
				for (int colCounter = 0; colCounter < 4; b++, colCounter++) {
					for (int a = 0; a < 4; a++) {
						System.out.print(finalMatrix[a][b].toUpperCase());
					}
				}
				System.out.println("");
				// to get each key in one line after other
				printCounter++;
			
			}
			System.out.print(finalMatrix[0][0]);
		} catch (Exception rCon) {
			System.err.println("Error in printingFinalKeyMatrix() method, Couldn't print final keys ");
		}
		System.out.print(finalMatrix[1][0]);
	}

	/**
	 * xorCaliculation
	 *
	 * This function defines a xor calculation and this xorCaliculation method
	 * is used by finalKeyMatrix method.
	 * 
	 */
	public static String xorCaliculation(String value1, String value2) {
		try {
			int firstValue = Integer.parseInt(value1, 16);
			int secondValue = Integer.parseInt(value2, 16);
			String xorResult = Integer.toHexString(firstValue ^ secondValue);
			if (xorResult.length() == 1)
				return ("0" + xorResult);
			else
				return xorResult;
		} catch (Exception xor) {
			System.err.println("Error in printingFinalKeyMatrix() method");
			return "";
		}
	}
	public static void aesAddKey(){
		try{
			String[][] addRoundKeyMatrix = new String[4][4];
			addRoundKeyMatrix[0][0] = xorCaliculation(sBoxCipher(null), finalMatrix[0][0]);
			System.out.println(addRoundKeyMatrix[0][0]);
		}catch (Exception addkey){
			
		}
	}
}
