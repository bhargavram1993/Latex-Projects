
/**
 * file: AesCipher.java
 * author: Murali Krishna, Bhargav Uppalapati.
 * course: MSCS 630 - Rivas
 * assignment: Lab3
 * due date: 3/31/2016
 *
 * This file contains the Programming of Advanced Encryption System(AES).
 * One important part of the AES is how it produces keys on every round of encryption type.
 * and with the help of the keys a message is converted into cipher text.
 */
public class AesCipher {
    /**
     * S_BOx array
     */
    private static final String[][] S_BOX = {
            { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67",
                    "2B", "FE", "D7", "AB", "76" },
            { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2",
                    "AF", "9C", "A4", "72", "C0" },
            { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5",
                    "F1", "71", "D8", "31", "15" },
            { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80",
                    "E2", "EB", "27", "B2", "75" },
            { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6",
                    "B3", "29", "E3", "2F", "84" },
            { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE",
                    "39", "4A", "4C", "58", "CF" },
            { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02",
                    "7F", "50", "3C", "9F", "A8" },
            { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA",
                    "21", "10", "FF", "F3", "D2" },
            { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E",
                    "3D", "64", "5D", "19", "73" },
            { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8",
                    "14", "DE", "5E", "0B", "DB" },
            { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC",
                    "62", "91", "95", "E4", "79" },
            { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4",
                    "EA", "65", "7A", "AE", "08" },
            { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74",
                    "1F", "4B", "BD", "8B", "8A" },
            { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57",
                    "B9", "86", "C1", "1D", "9E" },
            { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87",
                    "E9", "CE", "55", "28", "DF" },
            { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D",
                    "0F", "B0", "54", "BB", "16" } };
    /**
     * R_CON array
     */
    private static final String[][] R_CON = {
            { "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36",
                    "6C", "D8", "AB", "4D", "9A" },
            { "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D",
                    "FA", "EF", "C5", "91", "39" },
            { "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33",
                    "66", "CC", "83", "1D", "3A" },
            { "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40",
                    "80", "1B", "36", "6C", "D8" },
            { "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A",
                    "D4", "B3", "7D", "FA", "EF" },
            { "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25",
                    "4A", "94", "33", "66", "CC" },
            { "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08",
                    "10", "20", "40", "80", "1B" },
            { "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6",
                    "97", "35", "6A", "D4", "B3" },
            { "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61",
                    "C2", "9F", "25", "4A", "94" },
            { "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01",
                    "02", "04", "08", "10", "20" },
            { "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E",
                    "BC", "63", "C6", "97", "35" },
            { "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4",
                    "D3", "BD", "61", "C2", "9F" },
            { "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8",
                    "CB", "8D", "01", "02", "04" },
            { "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D",
                    "9A", "2F", "5E", "BC", "63" },
            { "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91",
                    "39", "72", "E4", "D3", "BD" },
            { "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D",
                    "3A", "74", "E8", "CB", "8D" } };

    //Creating a two dimensional matrix of four rows and columns to store the Key.
    private static String[][] keyMatrix;
    //Creating a two dimensional stateMatrix of four rows and columns.
    private static String[][] stateMatrix = new String[4][4];
    //Creating a two dimensional Matrix of four rows and forty four columns
    //to store the round keys.
    private static String[][] roundKeyMatrix;
    private static int roundKeyMatrixColumn;
    private static int round;
    private static int keyMatrixColumn;
    public static StringBuilder cipher = new StringBuilder();
    /**
     * matrixInformation
     *
     * Depending upon the key size the round keys size varies.
     */
    private static void matrixInformation(){
        if(keyMatrixColumn ==4){
            roundKeyMatrixColumn=44;
            round=11;
        }
        else if(keyMatrixColumn ==6){
            roundKeyMatrixColumn=52;
            round=13;
        }
        else if(keyMatrixColumn ==8){
            roundKeyMatrixColumn=60;
            round=15;
        }
        roundKeyMatrix = new String[4][roundKeyMatrixColumn];
        keyMatrix= new String[4][keyMatrixColumn];
    }

    /**
     * aesRoundKeys
     *
     * passing the input hexadecimal key into 2D-array of keMatrix
     *
     * @param KeyHex
     */
    public static void aesRoundKeys(String KeyHex) {
        try {
            int count = 0;
            for (int column = 0; column < keyMatrixColumn; column++) {
                for (int row = 0; row < 4; row++) {
                    //Splitting into two elements and storing them in an array.
                    keyMatrix[row][column] = KeyHex.substring(count, count + 2);
                    count = count + 2;
                }
            }
            // calling computeMatrix to generate 4*44 matrix
            computingMatrix();
        } catch (Exception e) {

        }
    }

    /**
     * computingMatrix
     *
     * Generating Round Key Matrix which stores round keys.
     */
    private static void computingMatrix() {
        try {
            // copying the input into roundKeyMatrix.
            for (int row = 0; row < 4; row = row + 1) {
                for (int column = 0; column < keyMatrixColumn; column++) {
                    roundKeyMatrix[row][column] = keyMatrix[row][column];
                }
            }
            //generating remaining elements in array to fill 40 columns
            for (int column = keyMatrixColumn; column < roundKeyMatrixColumn; column++) {
                // If the Column index is not a multiple of 4.
                // We XOR as denoted in the following xorCaliculation:
                if (column % keyMatrixColumn != 0) {
                    if(column % 4 ==0 && keyMatrixColumn ==8){
                        for (int row = 0; row < 4; row++) {
                            roundKeyMatrix[row][column] = aesSBox(roundKeyMatrix[row][column-1]);
                            roundKeyMatrix[row][column] = xorComputing( roundKeyMatrix[row][column],
                                    roundKeyMatrix[row][column- keyMatrixColumn]);
                        }
                    }else
                        for (int row = 0; row < 4; row++) {
                            roundKeyMatrix[row][column] = xorComputing(
                                    roundKeyMatrix[row][column - keyMatrixColumn],
                                    roundKeyMatrix[row][column - 1]);
                        }
                } else {
                    //creating a new matrix for storing and shifting elements.
                    String[][] wNewMatrix = new String[1][4];
                    // Shifting the columns
                    wNewMatrix[0][0] = roundKeyMatrix[1][column - 1];
                    wNewMatrix[0][1] = roundKeyMatrix[2][column - 1];
                    wNewMatrix[0][2] = roundKeyMatrix[3][column - 1];
                    wNewMatrix[0][3] = roundKeyMatrix[0][column - 1];
                    // aesSbox method is called for getting corresponding S-box values for
                    // elements.
                    for (int i = 0; i < 4; i++) {
                        wNewMatrix[0][i] = aesSBox(wNewMatrix[0][i]);
                    }
                    String rconValue = aesRcon(column);
                    wNewMatrix[0][0] = xorComputing(wNewMatrix[0][0], rconValue);
                    for (int row = 0; row < 4; row++) {
                        roundKeyMatrix[row][column] = xorComputing(
                                roundKeyMatrix[row][column - keyMatrixColumn], wNewMatrix[0][row]);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(" exception occured at computingMatrix() " +e);
        }
    }

    /**
     * xorComputing
     *
     * This function defines a XOR calculation
     *
     * @param value1
     * @param value2
     * @return xorResult: check the length and return
     */
    private static String xorComputing(String value1, String value2) {
        int input1 = Integer.parseInt(value1, 16);
        int input2 = Integer.parseInt(value2, 16);
        int xor = input1 ^ input2;
        String xorResult = Integer.toHexString(xor);
        return xorResult.length() == 1 ? ("0" + xorResult) : xorResult;
    }

    /**
     * aesSBox
     *
     * This function computes the the integer equivalent to a hexadecimal format,
     * keys from S_BOX are fetched and split.
     *
     * @param inHex: This parameter takes input from S_BOX.
     * @return returns the values obtained from the S_BOX.
     */
    private static String aesSBox(String inHex) {
        int rowNumber = Integer.parseInt(inHex.substring(0, 1), 16);
        int columnNumber = Integer.parseInt(inHex.substring(1, 2), 16);
        return S_BOX[rowNumber][columnNumber];
    }

    /**
     * aesRcon
     *
     * function for accessing R-CON values from the table.
     *
     * @param round
     * @return return values obtained from R_CON table
     */
    private static String aesRcon(int round) {
        int rowNumber = 0;
        return R_CON[rowNumber][round / keyMatrixColumn];
    }

    /**
     * matrixCreation
     *
     * Generating matrix
     * @param text: plain text which is given as input.
     */
    public static void matrixCreation(String text) {
        int count = 0;
        for (int column = 0; column < 4; column++) {
            for (int row = 0; row < 4; row++) {
                //Separating into two elements and storing them in an array.
                stateMatrix[row][column] = text.substring(count, count + 2);
                count = count + 2;
            }
        }
    }

    /**
     * aesStateXor
     *
     * This method is used to XOR the state matrix and round key
     *
     * @param sHex
     * @param keyHex
     * @return returns the output of XOR for the given input.
     */
    private static String[][] aesStateXor(String[][] sHex, String[][] keyHex) {
        String[][] outputXor = new String[4][4];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                outputXor[row][column] = xorComputing(stateMatrix[row][column],
                        keyHex[row][column]);
            }
        }
        return outputXor;
    }

    /**
     * aesNibbleSubstitution
     *
     * inputs and output are 4 by 4 matrices of pairs of hex digits, and that will
     * perform the nibble Substitution operation, i.e., the entries of the output
     * matrix result from running the corresponding input matrix entries through
     * the AES S-Box.
     *
     * @param inStateHex
     * @return return inStateHex matrix
     */
    private static String[][] aesNibbleSubstitution(String[][] inStateHex) {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                inStateHex[row][column] = aesSBox(inStateHex[row][column]);
            }
        }
        return inStateHex;
    }

    /**
     * aesShiftRow
     *
     * This function does the shift operation of 2-D array
     *
     * @param inStateHex
     * @return returns the shiftMatrix
     */
    public static String[][] aesShiftRow(String[][] inStateHex) {
        String[][] shiftMatrix = new String[inStateHex.length][inStateHex[0].length];
        int count = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                shiftMatrix[row][column] = inStateHex[row][(column + count) % 4];
            }
            count++;
        }
        return shiftMatrix;
    }

    /**
     * Declaring a static constant matrix for multiplying mix columns
     */
    private static String[][] constantmatrix = { { "02", "03", "01", "01" },
            { "01", "02", "03", "01" }, { "01", "01", "02", "03" },
            { "03", "01", "01", "02" } };

    /**
     * The aesMixColumn function is used generate mix column
     *
     * @param inStateHex
     * @return returns the matrix
     */
    private static String[][] aesMixColumn(String[][] inStateHex) {
        String sum = "0";
        int rowCount = 0;
        int column = 0;
        String[][] newMatrix = new String[4][4];
        while (column <= 3) {
            for (int i = 0; i < 4 && column < 4; i++, rowCount++) {
                sum = "0";
                for (int j = 0, row = 0; j < 4; j++, row++) {
                    if (constantmatrix[i][j] == "02") {
                        sum = xorComputing(sum,
                                multiplication(inStateHex[row][column]));
                    } else if (constantmatrix[i][j] == "03") {
                        String temp = xorComputing(inStateHex[row][column],
                                multiplication(inStateHex[row][column]));
                        sum = xorComputing(sum, temp);
                    } else if (constantmatrix[i][j] == "01") {
                        sum = xorComputing(sum, inStateHex[row][column]);
                    }
                }
                newMatrix[i][column] = sum;
                if (rowCount == 3) {
                    column++;
                    rowCount = -1;
                }
            }
        }
        return newMatrix;
    }

    /**
     * This function performs the left shift operations
     *
     * @param : Taking input
     * @return returns xorComputed output
     */
    private static String multiplication(String valueIn) {
        String binValue = Integer.toBinaryString(Integer.parseInt(valueIn, 16));
        // Checks the length of the binary string and pad with 0 in the left
        while (binValue.length() < 8) {
            binValue = "0" + binValue;
        }
        // Left shift by one
        int intvalueIn = Integer.parseInt(binValue, 2);
        String binaryShift = Integer.toBinaryString(intvalueIn << 1);
        binaryShift = (binaryShift.length() > 8 ? binaryShift.substring(1)
                : binaryShift);
        // Checking MSB is one if so doing XOR with 27 otherwise only left shift.
        if (binValue.substring(0, 1).equals("1")) {
            String constantVal = Integer.toString(27, 2);
            while (constantVal.length() < 8) {
                constantVal = "0" + constantVal;
            }
            return xorComputing(
                    Integer.toHexString(Integer.parseInt(binaryShift, 2)),
                    Integer.toHexString(Integer.parseInt(constantVal, 2)));
        } else {
            return Integer.toHexString(Integer.parseInt(binaryShift, 2));
        }
    }


    /**
     * calculates round calculations for 10 rounds
     * @param text: plain text
     * @param key: key
     *
     * @return returns input.
     */

    private static  String[][] roundCalculation(String[][] text, String[][] key) {

        String[][] input;
        // Calls the aesStateXor by passing parameters as input
        input = AesCipher.aesStateXor(text, key);
        // Calls aesNibbleSub method by passing parameter as input
        input = AesCipher.aesNibbleSubstitution(input);
        // Calls aesShiftRow method by passing parameter as input
        input = AesCipher.aesShiftRow(input);
        // Calls aesMixColumn method by passing parameter as input
        if(loop!=round-2){
            input = AesCipher.aesMixColumn(input);
        }
        return input;
    }

    /**
     * converts the input to cipher text
     *
     * @param text: plain text
     * @param key: cipher key
     */
    private static int  loop;
    public static StringBuilder aes(String text, String key) {
        keyMatrixColumn = key.length()/8;
        matrixInformation();
        aesRoundKeys(key);
        matrixCreation(text);
        String[][] keyMatrix = new String[4][4];
        int count = 0;
        int wmatCol = 0;
        while (wmatCol < roundKeyMatrixColumn) {
            for (int col = 0; col < 4; col++, wmatCol++) {
                for (int row = 0; row < 4; row++) {
                    keyMatrix[row][col] = roundKeyMatrix[row][wmatCol];
                }
            }
            if (count == round-1)
                stateMatrix = aesStateXor(stateMatrix, keyMatrix);
            else {
                loop = count++;
                stateMatrix = roundCalculation(stateMatrix, keyMatrix);
            }
        }
        System.out.println("cipher text");
        cipher= new StringBuilder();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                System.out.print(stateMatrix[i][j].toUpperCase());
               cipher.append(stateMatrix[i][j].toUpperCase());
            }

        }
        return cipher;
    }
}