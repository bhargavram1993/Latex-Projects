/**
 * file: AEScipher.java
 * author: Bhargav Uppalapati
 * course: MSCS 630 - Rivas
 * assignment: project 2
 * due date: MAR 31, 2016
 * 
 * This file contains the Programming of Advanced Encryption System(AES).
 * One important part of the AES is how it produces keys on every round of encryption type.
 */
/**
 * AEScipher
 * 
 */
public class AEScipher {
	// Creating a 4x4 Matrix for input key
	public static String[][] inputKeyMatrix = new String[4][4];
	// Creating a 4x44 Matrix 
	public static String[][] finalMatrix = new String[4][44];
	public static String[] roundKeys = new String[11];
	/**
	 * inputtingStringIntoKMatrix This method takes 128-bit encryption key from
	 * driver.java class and inserts it in a 4x4 Matrix.
	 * 
	 * @return
	 */
	public static String[] inputtingStringIntoKMatrix(String key) {
		stringIntoMatrixConversion(key);
		// Control flow of the program, copyingIntoWHexMatrix() method is
		// called.
		copyingIntoWHexMatrix();
		return roundKeys;
	}

	public static String[][] stringIntoMatrixConversion(String key) {
		String[][] tempMatrix = new String[4][4];
		try {
			int keyTraversalCounter = 0;
			for (int kMatrixColumn = 0; kMatrixColumn < 4; kMatrixColumn++) {
				for (int kMatrixRow = 0; kMatrixRow < 4; kMatrixRow = kMatrixRow + 1) {
					tempMatrix[kMatrixRow][kMatrixColumn] = key.substring(keyTraversalCounter, keyTraversalCounter + 2);
					// The key is traversed and every two characters are split
					// and inserted into matrix.
					keyTraversalCounter = keyTraversalCounter + 2;
				}
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						inputKeyMatrix[i][j] = tempMatrix[i][j];
					}
				}
			}
		} catch (Exception ex) {
			System.err.println(
					"Error occured in inputtingStringIntoKMatrix mathod, generation of input 4x4 matrix not possible ");
		}
		return tempMatrix;
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
			// System.out.println(Keys);
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
					for (int fianlMatrixRow = 0; fianlMatrixRow < 4; fianlMatrixRow++) {
						// finally, finalMatrix can be obtained by xoring
						// previous column with the temporary matrix.
						finalMatrix[fianlMatrixRow][finalMatrixColumn] = xorCaliculation(
								finalMatrix[fianlMatrixRow][finalMatrixColumn - 4], temporaryMatrix[0][fianlMatrixRow]);
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
			StringBuilder val = new StringBuilder();
			int printCounter = 0;
			int b = 0;
			// printing 4x4 matrix 11 times.
			while (printCounter < 11) {
				for (int colCounter = 0; colCounter < 4; b++, colCounter++) {
					for (int a = 0; a < 4; a++) {
						val = val.append(finalMatrix[a][b]);

						// System.out.print(finalMatrix[a][b].toUpperCase());
					}
				}
				roundKeys[printCounter] = val.toString();
				val = new StringBuilder();

				// to get each key in one line after other
				printCounter++;
			}
		} catch (Exception rCon) {
			System.err.println("Error in printingFinalKeyMatrix() method, Couldn't print final keys ");
		}
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

	public static String[][] aesAddKey(String Key, String Message) {
		try {
			String[][] aesXORMatrix = new String[4][4];
			String[][] keyMatrix = stringIntoMatrixConversion(Key);
			String[][] messageMatrix = stringIntoMatrixConversion(Message);
			for (int i = 0; i < keyMatrix.length; i++) {
				for (int j = 0; j < keyMatrix[i].length; j++) {
					aesXORMatrix[i][j] = xorCaliculation(keyMatrix[i][j], messageMatrix[i][j]);
				}
			}
			// addRoundKeyMatrix[0][0] = xorCaliculation();
			return aesXORMatrix;
		} catch (Exception addkey) {
			return null;
		}
	}

	public static String[][] aesNibbleSubstitution(String[][] Key) {
		String nibbledSubstitutionMatrix[][] = new String[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				nibbledSubstitutionMatrix[i][j] = sBoxCipher(Key[i][j]);
			}
		}
		return nibbledSubstitutionMatrix;
	}

	public static String[][] aesShiftRows(String[][] Key) {
		String aesShiftRowsMatrix[][] = new String[4][4];
		int shiftValue;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				switch (i) {
				case 0:
					shiftValue = j + i;
					aesShiftRowsMatrix[i][j] = Key[i][shiftValue];
					break;
				case 1:
					if (j == 3)
						shiftValue = j - 3;
					else
						shiftValue = j + i;
					aesShiftRowsMatrix[i][j] = Key[i][shiftValue];
					break;
				case 2:
					if (j >= 2)
						shiftValue = j - 2;
					else
						shiftValue = j + i;
					aesShiftRowsMatrix[i][j] = Key[i][shiftValue];
					break;
				case 3:
					if (j >= 1)
						shiftValue = j - 1;
					else
						shiftValue = j + i;
					aesShiftRowsMatrix[i][j] = Key[i][shiftValue];
					break;
				}
			}
		}
		return aesShiftRowsMatrix;
	}

	public static int[][] mixColumn = { { 0x02, 0x03, 0x01, 0x01 }, { 0x01, 0x02, 0x03, 0x01 },
			{ 0x01, 0x01, 0x02, 0x03 }, { 0x03, 0x01, 0x01, 0x02 } };

	public static String[][] aesMixColumn(String[][] Key) {
		String aesMixColumnMatrix[][] = new String[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				aesMixColumnMatrix[i][j] = mixColumnAddition(Key, mixColumn, i, j);
			}
		}
		return aesMixColumnMatrix;
	}

	public static String mixColumnAddition(String[][] Key, int[][] mixclmn, int i, int j) {
		int sum = 0;
		for (int k = 0; k < 4; k++) {
			int a = mixclmn[i][k];
			int b = Integer.parseInt(Key[k][j], 16);
			sum = sum ^ mixColumnMultiplication(a, b);
		}
		String result = String.format("%02x", sum);
		return result.toUpperCase();
	}

	public static int[][] mc2 = {
			{ 0x00, 0x02, 0x04, 0x06, 0x08, 0x0a, 0x0c, 0x0e, 0x10, 0x12, 0x14, 0x16, 0x18, 0x1a, 0x1c, 0x1e },
			{ 0x20, 0x22, 0x24, 0x26, 0x28, 0x2a, 0x2c, 0x2e, 0x30, 0x32, 0x34, 0x36, 0x38, 0x3a, 0x3c, 0x3e },
			{ 0x40, 0x42, 0x44, 0x46, 0x48, 0x4a, 0x4c, 0x4e, 0x50, 0x52, 0x54, 0x56, 0x58, 0x5a, 0x5c, 0x5e },
			{ 0x60, 0x62, 0x64, 0x66, 0x68, 0x6a, 0x6c, 0x6e, 0x70, 0x72, 0x74, 0x76, 0x78, 0x7a, 0x7c, 0x7e },
			{ 0x80, 0x82, 0x84, 0x86, 0x88, 0x8a, 0x8c, 0x8e, 0x90, 0x92, 0x94, 0x96, 0x98, 0x9a, 0x9c, 0x9e },
			{ 0xa0, 0xa2, 0xa4, 0xa6, 0xa8, 0xaa, 0xac, 0xae, 0xb0, 0xb2, 0xb4, 0xb6, 0xb8, 0xba, 0xbc, 0xbe },
			{ 0xc0, 0xc2, 0xc4, 0xc6, 0xc8, 0xca, 0xcc, 0xce, 0xd0, 0xd2, 0xd4, 0xd6, 0xd8, 0xda, 0xdc, 0xde },
			{ 0xe0, 0xe2, 0xe4, 0xe6, 0xe8, 0xea, 0xec, 0xee, 0xf0, 0xf2, 0xf4, 0xf6, 0xf8, 0xfa, 0xfc, 0xfe },
			{ 0x1b, 0x19, 0x1f, 0x1d, 0x13, 0x11, 0x17, 0x15, 0x0b, 0x09, 0x0f, 0x0d, 0x03, 0x01, 0x07, 0x05 },
			{ 0x3b, 0x39, 0x3f, 0x3d, 0x33, 0x31, 0x37, 0x35, 0x2b, 0x29, 0x2f, 0x2d, 0x23, 0x21, 0x27, 0x25 },
			{ 0x5b, 0x59, 0x5f, 0x5d, 0x53, 0x51, 0x57, 0x55, 0x4b, 0x49, 0x4f, 0x4d, 0x43, 0x41, 0x47, 0x45 },
			{ 0x7b, 0x79, 0x7f, 0x7d, 0x73, 0x71, 0x77, 0x75, 0x6b, 0x69, 0x6f, 0x6d, 0x63, 0x61, 0x67, 0x65 },
			{ 0x9b, 0x99, 0x9f, 0x9d, 0x93, 0x91, 0x97, 0x95, 0x8b, 0x89, 0x8f, 0x8d, 0x83, 0x81, 0x87, 0x85 },
			{ 0xbb, 0xb9, 0xbf, 0xbd, 0xb3, 0xb1, 0xb7, 0xb5, 0xab, 0xa9, 0xaf, 0xad, 0xa3, 0xa1, 0xa7, 0xa5 },
			{ 0xdb, 0xd9, 0xdf, 0xdd, 0xd3, 0xd1, 0xd7, 0xd5, 0xcb, 0xc9, 0xcf, 0xcd, 0xc3, 0xc1, 0xc7, 0xc5 },
			{ 0xfb, 0xf9, 0xff, 0xfd, 0xf3, 0xf1, 0xf7, 0xf5, 0xeb, 0xe9, 0xef, 0xed, 0xe3, 0xe1, 0xe7, 0xe5 } };

	public static int[][] mc3 = {
			{ 0x00, 0x03, 0x06, 0x05, 0x0c, 0x0f, 0x0a, 0x09, 0x18, 0x1b, 0x1e, 0x1d, 0x14, 0x17, 0x12, 0x11 },
			{ 0x30, 0x33, 0x36, 0x35, 0x3c, 0x3f, 0x3a, 0x39, 0x28, 0x2b, 0x2e, 0x2d, 0x24, 0x27, 0x22, 0x21 },
			{ 0x60, 0x63, 0x66, 0x65, 0x6c, 0x6f, 0x6a, 0x69, 0x78, 0x7b, 0x7e, 0x7d, 0x74, 0x77, 0x72, 0x71 },
			{ 0x50, 0x53, 0x56, 0x55, 0x5c, 0x5f, 0x5a, 0x59, 0x48, 0x4b, 0x4e, 0x4d, 0x44, 0x47, 0x42, 0x41 },
			{ 0xc0, 0xc3, 0xc6, 0xc5, 0xcc, 0xcf, 0xca, 0xc9, 0xd8, 0xdb, 0xde, 0xdd, 0xd4, 0xd7, 0xd2, 0xd1 },
			{ 0xf0, 0xf3, 0xf6, 0xf5, 0xfc, 0xff, 0xfa, 0xf9, 0xe8, 0xeb, 0xee, 0xed, 0xe4, 0xe7, 0xe2, 0xe1 },
			{ 0xa0, 0xa3, 0xa6, 0xa5, 0xac, 0xaf, 0xaa, 0xa9, 0xb8, 0xbb, 0xbe, 0xbd, 0xb4, 0xb7, 0xb2, 0xb1 },
			{ 0x90, 0x93, 0x96, 0x95, 0x9c, 0x9f, 0x9a, 0x99, 0x88, 0x8b, 0x8e, 0x8d, 0x84, 0x87, 0x82, 0x81 },
			{ 0x9b, 0x98, 0x9d, 0x9e, 0x97, 0x94, 0x91, 0x92, 0x83, 0x80, 0x85, 0x86, 0x8f, 0x8c, 0x89, 0x8a },
			{ 0xab, 0xa8, 0xad, 0xae, 0xa7, 0xa4, 0xa1, 0xa2, 0xb3, 0xb0, 0xb5, 0xb6, 0xbf, 0xbc, 0xb9, 0xba },
			{ 0xfb, 0xf8, 0xfd, 0xfe, 0xf7, 0xf4, 0xf1, 0xf2, 0xe3, 0xe0, 0xe5, 0xe6, 0xef, 0xec, 0xe9, 0xea },
			{ 0xcb, 0xc8, 0xcd, 0xce, 0xc7, 0xc4, 0xc1, 0xc2, 0xd3, 0xd0, 0xd5, 0xd6, 0xdf, 0xdc, 0xd9, 0xda },
			{ 0x5b, 0x58, 0x5d, 0x5e, 0x57, 0x54, 0x51, 0x52, 0x43, 0x40, 0x45, 0x46, 0x4f, 0x4c, 0x49, 0x4a },
			{ 0x6b, 0x68, 0x6d, 0x6e, 0x67, 0x64, 0x61, 0x62, 0x73, 0x70, 0x75, 0x76, 0x7f, 0x7c, 0x79, 0x7a },
			{ 0x3b, 0x38, 0x3d, 0x3e, 0x37, 0x34, 0x31, 0x32, 0x23, 0x20, 0x25, 0x26, 0x2f, 0x2c, 0x29, 0x2a },
			{ 0x0b, 0x08, 0x0d, 0x0e, 0x07, 0x04, 0x01, 0x02, 0x13, 0x10, 0x15, 0x16, 0x1f, 0x1c, 0x19, 0x1a } };

	public static int mixColumnMultiplication(int a, int b) {
		if (a == 1) {
			return b;
		} else if (a == 2) {
			return mc2[b / 16][b % 16];
		} else if (a == 3) {
			return mc3[b / 16][b % 16];
		}
		return 0;
	}
}