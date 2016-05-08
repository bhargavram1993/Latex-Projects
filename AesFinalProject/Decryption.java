
/**
 * file: Decryption.java
 * author: Murali Krishna, Bhargav Uppalapati.
 * course: MSCS 630 - Rivas
 * assignment: Lab3
 * due date: 3/31/2016
 * <p>
 * This file contains the Programming of Advanced Encryption System(AES).
 * One important part of the AES is how it produces keys on every round of encryption type.
 * and with the help of the keys a message is converted into cipher text.
 * This file contains the implementation of Decryption standard which accepts the
 * cipher text as input and gives plain text as output.
 */
public class Decryption {
    /**
     * S_BOx array
     */
    private static final String[][] S_BOX = {
            {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67",
                    "2B", "FE", "D7", "AB", "76"},
            {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2",
                    "AF", "9C", "A4", "72", "C0"},
            {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5",
                    "F1", "71", "D8", "31", "15"},
            {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80",
                    "E2", "EB", "27", "B2", "75"},
            {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6",
                    "B3", "29", "E3", "2F", "84"},
            {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE",
                    "39", "4A", "4C", "58", "CF"},
            {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02",
                    "7F", "50", "3C", "9F", "A8"},
            {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA",
                    "21", "10", "FF", "F3", "D2"},
            {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E",
                    "3D", "64", "5D", "19", "73"},
            {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8",
                    "14", "DE", "5E", "0B", "DB"},
            {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC",
                    "62", "91", "95", "E4", "79"},
            {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4",
                    "EA", "65", "7A", "AE", "08"},
            {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74",
                    "1F", "4B", "BD", "8B", "8A"},
            {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57",
                    "B9", "86", "C1", "1D", "9E"},
            {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87",
                    "E9", "CE", "55", "28", "DF"},
            {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D",
                    "0F", "B0", "54", "BB", "16"}};
    /**
     * Inverse S-Box array
     */
    private static final String[][] INVERSE_S_BOX = {
            {"52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3",
                    "9E", "81", "F3", "D7", "FB"},
            {"7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43",
                    "44", "C4", "DE", "E9", "CB"},
            {"54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95",
                    "0B", "42", "FA", "C3", "4E"},
            {"08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2",
                    "49", "6D", "8B", "D1", "25"},
            {"72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C",
                    "CC", "5D", "65", "B6", "92"},
            {"6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15", "46",
                    "57", "A7", "8D", "9D", "84"},
            {"90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4", "58",
                    "05", "B8", "B3", "45", "06"},
            {"D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF", "BD",
                    "03", "01", "13", "8A", "6B"},
            {"3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2", "CF",
                    "CE", "F0", "B4", "E6", "73"},
            {"96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37",
                    "E8", "1C", "75", "DF", "6E"},
            {"47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62",
                    "0E", "AA", "18", "BE", "1B"},
            {"FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0",
                    "FE", "78", "CD", "5A", "F4"},
            {"1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10",
                    "59", "27", "80", "EC", "5F"},
            {"60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A",
                    "9F", "93", "C9", "9C", "EF"},
            {"A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB",
                    "3C", "83", "53", "99", "61"},
            {"17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69", "14",
                    "63", "55", "21", "0C", "7D"}};
    /**
     * R_CON table
     */
    private static final String[][] R_CON = {
            {"8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36",
                    "6C", "D8", "AB", "4D", "9A"},
            {"2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D",
                    "FA", "EF", "C5", "91", "39"},
            {"72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33",
                    "66", "CC", "83", "1D", "3A"},
            {"74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40",
                    "80", "1B", "36", "6C", "D8"},
            {"AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A",
                    "D4", "B3", "7D", "FA", "EF"},
            {"C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25",
                    "4A", "94", "33", "66", "CC"},
            {"83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08",
                    "10", "20", "40", "80", "1B"},
            {"36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6",
                    "97", "35", "6A", "D4", "B3"},
            {"7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61",
                    "C2", "9F", "25", "4A", "94"},
            {"33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01",
                    "02", "04", "08", "10", "20"},
            {"40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E",
                    "BC", "63", "C6", "97", "35"},
            {"6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4",
                    "D3", "BD", "61", "C2", "9F"},
            {"25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8",
                    "CB", "8D", "01", "02", "04"},
            {"08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D",
                    "9A", "2F", "5E", "BC", "63"},
            {"C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91",
                    "39", "72", "E4", "D3", "BD"},
            {"61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D",
                    "3A", "74", "E8", "CB", "8D"}};

    //Creating a two dimensional matrix of four rows and columns to store the Key.
    private static String[][] keyMatrix;
    //Creating a two dimensional stateMatrix of four rows and columns
    private static String[][] stateMatrix = new String[4][4];
    private static String[][] roundKeyMatrix;
    //Creating a two dimensional wMatrix of four rows and forty four columns
    private static int roundKeyMatrixColumn = 0;

    private static int round;
    private static int inputKeyMatrixLength;
    //Creating a stringbuilder of static type
    public static StringBuilder plain = new StringBuilder();

    /**
     * matrixInformation
     * <p>
     * Depending upon the key size the round keys size varies.
     * </p>
     */
    private static void matrixInformation() {
        if (inputKeyMatrixLength == 4) {
            roundKeyMatrixColumn = 44;
            round = 11;
        } else if (inputKeyMatrixLength == 6) {
            roundKeyMatrixColumn = 52;
            round = 13;
        } else if (inputKeyMatrixLength == 8) {
            roundKeyMatrixColumn = 60;
            round = 15;
        }
        roundKeyMatrix = new String[4][roundKeyMatrixColumn];
        keyMatrix = new String[4][inputKeyMatrixLength];
    }

    /**
     * passing the input hexadecimal key to convert into 2D-array of keMatrix
     *
     * @param KeyHex input to the method
     */
    private static void aesRoundKeys(String KeyHex) {
        try {

            int count = 0;
            for (int column = 0; column < inputKeyMatrixLength; column++) {
                for (int row = 0; row < 4; row++) {
                    //Splitting into two elements and storing them in an array.
                    keyMatrix[row][column] = KeyHex.substring(count, count + 2);
                    count = count + 2;
                }
            }
            // calling computeMatrix to generate 4*44 matrix
            computingMatrix();
        } catch (Exception e) {
            //System.out.println("aesRoundKeys" + e);
        }
    }

    /**
     * In computing matrix round key matrix is genereated
     */
    private static void computingMatrix() {

        // copying the input into roundKeyMatrix
        for (int row = 0; row < 4; row = row + 1) {
            for (int column = 0; column < inputKeyMatrixLength; column++) {
                roundKeyMatrix[row][column] = keyMatrix[row][column];
            }
        }
        //generating remaining elements in array to fill 40 columns
        for (int column = inputKeyMatrixLength; column < roundKeyMatrixColumn; column++) {
            // If the Column index is not a multiple
            // We XOR as denoted in the following xorCaliculation:
            if (column % inputKeyMatrixLength != 0) {
                //If column index is multiple of 4 and if it is equal to length of the input key matrix it goes in
                //and computed-xor value of the previous column and then xor that result with the present column
                // subtracted input length of the key
                if (column % 4 == 0 && inputKeyMatrixLength == 8) {
                    for (int row = 0; row < 4; row++) {
                        roundKeyMatrix[row][column] = aesSBox(roundKeyMatrix[row][column - 1]);
                        roundKeyMatrix[row][column] = xorComputing(
                                roundKeyMatrix[row][column], roundKeyMatrix[row][column
                                        - inputKeyMatrixLength]);
                    }
                } else
                    //It calculates xor of prvious column and present column subtracted with the length of the input key matrix
                    for (int row = 0; row < 4; row++) {
                        roundKeyMatrix[row][column] = xorComputing(
                                roundKeyMatrix[row][column - inputKeyMatrixLength],
                                roundKeyMatrix[row][column - 1]);
                    }
            } else {
                //creating a new matrix for storing and shifting elements.
                String[][] wNew = new String[1][4];
                // Shifting the columns
                wNew[0][0] = roundKeyMatrix[1][column - 1];
                wNew[0][1] = roundKeyMatrix[2][column - 1];
                wNew[0][2] = roundKeyMatrix[3][column - 1];
                wNew[0][3] = roundKeyMatrix[0][column - 1];
                // aesSbox method is called for getting corresponding S-box values for
                // elements.
                for (int i = 0; i < 4; i++) {
                    wNew[0][i] = aesSBox(wNew[0][i]);
                }
                //Getting the Rcon value from the rcon table by calling aesRcon function
                String rconVal = aesRcon(column);
                //xor of the first element if wnew matrix and rconvalue
                wNew[0][0] = xorComputing(wNew[0][0], rconVal);
                for (int row = 0; row < 4; row++) {
                    //Calculates xor operation
                    roundKeyMatrix[row][column] = xorComputing(roundKeyMatrix[row][column
                            - inputKeyMatrixLength], wNew[0][row]);
                }
            }
        }

    }

    /**
     * Computing Xor for two inputs and return the result
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
     * <p>
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
     * gets the value from the S_BOX and returns
     *
     * @param inHex input to the method
     * @return return the values from the sbox
     */
    private static String inverseAesSbox(String inHex) {
        int rowNumber = Integer.parseInt(inHex.substring(0, 1), 16);
        int columnNumber = Integer.parseInt(inHex.substring(1, 2), 16);
        return INVERSE_S_BOX[rowNumber][columnNumber];
    }

    /**
     * Function for accessing R-CON values from the table.
     *
     * @param round
     * @return return values obtained from R_CON table
     */
    private static String aesRcon(int round) {
        int rowNumber = 0;
        return R_CON[rowNumber][round / inputKeyMatrixLength];
    }


    /**
     * In this method the input cipher string is converted into 2-dimensional array
     *
     * @param cipher: plain text which is given as input.
     */
    private static void matrixCreation(String cipher) {
        int count = 0;
        for (int column = 0; column < 4; column++) {
            for (int row = 0; row < 4; row++) {
                //Separating into two elements and storing them in an array.
                stateMatrix[row][column] = cipher.substring(count, count + 2);
                count = count + 2;
            }
        }
    }

    /**
     * This method calls other methods in an order to perfrom decryption for the given cipher key and convert into plain text.
     *
     * @param text: cihpher text
     * @param key:  key
     */
    static int loop;

    public static String aes(String cipher, String key) {
        //The length of the key is divided with 8 to determine the length of the 2-dimensional array
        int inputLength = key.length() / 8;
        //Value is assigned
        inputKeyMatrixLength = inputLength;
        //Calling method to define the length of round matrix and the number of rounds.
        matrixInformation();
        //Calling method to convert a string into 2-dimensional array
        matrixCreation(cipher);
        //Calling method to convert a given input key into 2-dimensinal array
        aesRoundKeys(key);
        //Creating a 2-dimensional keyMatrix of size 4*4 which holds the present round key.
        String[][] keyMatrix = new String[4][4];
        //Initializing rounCount to zero and increment it later to keep track of in which round we are in.
        int roundCount = 0;
        //In this we copy the roundkeys from last to first.
        for (int wmatCol = roundKeyMatrix[0].length - 4; wmatCol >= 0 && roundCount <= round - 1; wmatCol -= 8) {
            for (int col = 0; col < 4 && wmatCol < roundKeyMatrix[0].length; col++, wmatCol++) {
                for (int row = 0; row < 4; row++) {
                    keyMatrix[row][col] = roundKeyMatrix[row][wmatCol];
                }
            }
            //If round count is zero and for the last round it goes in
            //For the first round stateXor inverseNibblesubstitution and shiftrows is done
            //For the last round only stateXor is calculated
            if (roundCount == 0 || roundCount == round - 1) {
                stateMatrix = aesStateXor(stateMatrix, keyMatrix);
                if (roundCount == 0) {
                    // Calls aesNibbleSub method by passing parameter as input
                    stateMatrix = inverseAesNibbleSubstitution(stateMatrix);
                    // Calls aesShiftRow method by passing parameter as input
                    stateMatrix = aesShiftRow(stateMatrix);
                }
                loop = roundCount++;
            } else {
                //Calling rounCalculation
                stateMatrix = roundCalculation(stateMatrix, keyMatrix);
                loop = roundCount++;
            }

        }
        //Printing the genereated plain text.
        System.out.println("Plain Text:");
        plain = new StringBuilder();
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
               // System.out.print(stateMatrix[row][col]);
                plain.append(stateMatrix[row][col].toUpperCase());

            }

        }

return plain.toString();
    }

    /**
     * This mehtod calls other function to help in decryption process.
     * @param text: plain text
     * @param key:  key
     * @return returns input.
     */
    private static String[][] roundCalculation(String[][] text, String[][] key) {
        String[][] input;
        // Calls the aesStateXor by passing parameters as input
        input = aesStateXor(text, key);
        // Calls aesMixColumn method by passing parameter as input
        input = aesMixColumn(input);
        // Calls aesShiftRow method by passing parameter as input
        input = aesShiftRow(input);
        // Calls aesNibbleSub method by passing parameter as input
        input = inverseAesNibbleSubstitution(input);
        return input;
    }

    /**
     * This method is used to XOR the state matrix and round key
     *
     * @param cHex
     * @param keyHex
     * @return returns the output of XOR for the given input.
     */
    private static String[][] aesStateXor(String[][] cHex, String[][] keyHex) {
        String[][] outputXor = new String[4][4];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                outputXor[row][column] = xorComputing(cHex[row][column],
                        keyHex[row][column]);
            }
        }
        return outputXor;
    }

    /**
     * aesShiftRow
     * This function does the shift operation of 2-D array.
     * @param inStateHex Contains input that need to be shifted.
     * @return returns the shiftMatrix
     */
    private static String[][] aesShiftRow(String[][] inStateHex) {
        String[][] shiftMatrix = new String[inStateHex.length][inStateHex[0].length];
        int count = 4;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                shiftMatrix[row][column] = inStateHex[row][(column + count) % 4];
            }
            count--;
        }
        return shiftMatrix;
    }

    /**
     * inverseAesNibbleSubstitution
     * inputs and output are 4 by 4 matrices of pairs of hex digits, and that will
     * perform the nibble Substitution operation, i.e., the entries of the output
     * matrix result from running the corresponding input matrix entries through
     * the AES S-Box.
     *
     * @param inStateHex Contains input for getting values from the sbox
     * @return return inStateHex matrix
     */
    private static String[][] inverseAesNibbleSubstitution(String[][] inStateHex) {

        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                inStateHex[row][column] = inverseAesSbox(inStateHex[row][column]);
            }
        }
        return inStateHex;
    }

    /**
     * aesMixColumn uses mixColumnMatrix inputs and calculates the resultant
     * matrix.
     */
    private static int[][] mixColumnMatrix = {{0x0e, 0xb, 0x0d, 0x09},
            {0x09, 0x0e, 0x0b, 0x0d}, {0x0d, 0x09, 0x0e, 0x0b},
            {0x0b, 0x0d, 0x09, 0x0e}};
    /*
     * rather than implementing galois multiplication, Rijndael implementations
     * simply uses pre-calculated lookup tables to perform the byte
     * multiplication by 9
     */
    private static int[][] ninesMultiplicationMatrix = {
            {0x00, 0x09, 0x12, 0x1b, 0x24, 0x2d, 0x36, 0x3f, 0x48, 0x41, 0x5a,
                    0x53, 0x6c, 0x65, 0x7e, 0x77},
            {0x90, 0x99, 0x82, 0x8b, 0xb4, 0xbd, 0xa6, 0xaf, 0xd8, 0xd1, 0xca,
                    0xc3, 0xfc, 0xf5, 0xee, 0xe7},
            {0x3b, 0x32, 0x29, 0x20, 0x1f, 0x16, 0x0d, 0x04, 0x73, 0x7a, 0x61,
                    0x68, 0x57, 0x5e, 0x45, 0x4c},
            {0xab, 0xa2, 0xb9, 0xb0, 0x8f, 0x86, 0x9d, 0x94, 0xe3, 0xea, 0xf1,
                    0xf8, 0xc7, 0xce, 0xd5, 0xdc},
            {0x76, 0x7f, 0x64, 0x6d, 0x52, 0x5b, 0x40, 0x49, 0x3e, 0x37, 0x2c,
                    0x25, 0x1a, 0x13, 0x08, 0x01},
            {0xe6, 0xef, 0xf4, 0xfd, 0xc2, 0xcb, 0xd0, 0xd9, 0xae, 0xa7, 0xbc,
                    0xb5, 0x8a, 0x83, 0x98, 0x91},
            {0x4d, 0x44, 0x5f, 0x56, 0x69, 0x60, 0x7b, 0x72, 0x05, 0x0c, 0x17,
                    0x1e, 0x21, 0x28, 0x33, 0x3a},
            {0xdd, 0xd4, 0xcf, 0xc6, 0xf9, 0xf0, 0xeb, 0xe2, 0x95, 0x9c, 0x87,
                    0x8e, 0xb1, 0xb8, 0xa3, 0xaa},
            {0xec, 0xe5, 0xfe, 0xf7, 0xc8, 0xc1, 0xda, 0xd3, 0xa4, 0xad, 0xb6,
                    0xbf, 0x80, 0x89, 0x92, 0x9b},
            {0x7c, 0x75, 0x6e, 0x67, 0x58, 0x51, 0x4a, 0x43, 0x34, 0x3d, 0x26,
                    0x2f, 0x10, 0x19, 0x02, 0x0b},
            {0xd7, 0xde, 0xc5, 0xcc, 0xf3, 0xfa, 0xe1, 0xe8, 0x9f, 0x96, 0x8d,
                    0x84, 0xbb, 0xb2, 0xa9, 0xa0},
            {0x47, 0x4e, 0x55, 0x5c, 0x63, 0x6a, 0x71, 0x78, 0x0f, 0x06, 0x1d,
                    0x14, 0x2b, 0x22, 0x39, 0x30},
            {0x9a, 0x93, 0x88, 0x81, 0xbe, 0xb7, 0xac, 0xa5, 0xd2, 0xdb, 0xc0,
                    0xc9, 0xf6, 0xff, 0xe4, 0xed},
            {0x0a, 0x03, 0x18, 0x11, 0x2e, 0x27, 0x3c, 0x35, 0x42, 0x4b, 0x50,
                    0x59, 0x66, 0x6f, 0x74, 0x7d},
            {0xa1, 0xa8, 0xb3, 0xba, 0x85, 0x8c, 0x97, 0x9e, 0xe9, 0xe0, 0xfb,
                    0xf2, 0xcd, 0xc4, 0xdf, 0xd6},
            {0x31, 0x38, 0x23, 0x2a, 0x15, 0x1c, 0x07, 0x0e, 0x79, 0x70, 0x6b,
                    0x62, 0x5d, 0x54, 0x4f, 0x46}};
    /*
     * rather than implementing galois multiplication, Rijndael implementations
     * simply uses pre-calculated lookup tables to perform the byte
     * multiplication by 11.
     */
    private static int[][] elevenMultiplicationMatrix = {
            {0x00, 0x0b, 0x16, 0x1d, 0x2c, 0x27, 0x3a, 0x31, 0x58, 0x53, 0x4e,
                    0x45, 0x74, 0x7f, 0x62, 0x69},
            {0xb0, 0xbb, 0xa6, 0xad, 0x9c, 0x97, 0x8a, 0x81, 0xe8, 0xe3, 0xfe,
                    0xf5, 0xc4, 0xcf, 0xd2, 0xd9},
            {0x7b, 0x70, 0x6d, 0x66, 0x57, 0x5c, 0x41, 0x4a, 0x23, 0x28, 0x35,
                    0x3e, 0x0f, 0x04, 0x19, 0x12},
            {0xcb, 0xc0, 0xdd, 0xd6, 0xe7, 0xec, 0xf1, 0xfa, 0x93, 0x98, 0x85,
                    0x8e, 0xbf, 0xb4, 0xa9, 0xa2},
            {0xf6, 0xfd, 0xe0, 0xeb, 0xda, 0xd1, 0xcc, 0xc7, 0xae, 0xa5, 0xb8,
                    0xb3, 0x82, 0x89, 0x94, 0x9f},
            {0x46, 0x4d, 0x50, 0x5b, 0x6a, 0x61, 0x7c, 0x77, 0x1e, 0x15, 0x08,
                    0x03, 0x32, 0x39, 0x24, 0x2f},
            {0x8d, 0x86, 0x9b, 0x90, 0xa1, 0xaa, 0xb7, 0xbc, 0xd5, 0xde, 0xc3,
                    0xc8, 0xf9, 0xf2, 0xef, 0xe4},
            {0x3d, 0x36, 0x2b, 0x20, 0x11, 0x1a, 0x07, 0x0c, 0x65, 0x6e, 0x73,
                    0x78, 0x49, 0x42, 0x5f, 0x54},
            {0xf7, 0xfc, 0xe1, 0xea, 0xdb, 0xd0, 0xcd, 0xc6, 0xaf, 0xa4, 0xb9,
                    0xb2, 0x83, 0x88, 0x95, 0x9e},
            {0x47, 0x4c, 0x51, 0x5a, 0x6b, 0x60, 0x7d, 0x76, 0x1f, 0x14, 0x09,
                    0x02, 0x33, 0x38, 0x25, 0x2e},
            {0x8c, 0x87, 0x9a, 0x91, 0xa0, 0xab, 0xb6, 0xbd, 0xd4, 0xdf, 0xc2,
                    0xc9, 0xf8, 0xf3, 0xee, 0xe5},
            {0x3c, 0x37, 0x2a, 0x21, 0x10, 0x1b, 0x06, 0x0d, 0x64, 0x6f, 0x72,
                    0x79, 0x48, 0x43, 0x5e, 0x55},
            {0x01, 0x0a, 0x17, 0x1c, 0x2d, 0x26, 0x3b, 0x30, 0x59, 0x52, 0x4f,
                    0x44, 0x75, 0x7e, 0x63, 0x68},
            {0xb1, 0xba, 0xa7, 0xac, 0x9d, 0x96, 0x8b, 0x80, 0xe9, 0xe2, 0xff,
                    0xf4, 0xc5, 0xce, 0xd3, 0xd8},
            {0x7a, 0x71, 0x6c, 0x67, 0x56, 0x5d, 0x40, 0x4b, 0x22, 0x29, 0x34,
                    0x3f, 0x0e, 0x05, 0x18, 0x13},
            {0xca, 0xc1, 0xdc, 0xd7, 0xe6, 0xed, 0xf0, 0xfb, 0x92, 0x99, 0x84,
                    0x8f, 0xbe, 0xb5, 0xa8, 0xa3}};
    /*
     * rather than implementing galois multiplication, Rijndael implementations
     * simply uses pre-calculated lookup tables to perform the byte
     * multiplication by 13.
     */
    private static int[][] thirteenMultiplicationMarix = {
            {0x00, 0x0d, 0x1a, 0x17, 0x34, 0x39, 0x2e, 0x23, 0x68, 0x65, 0x72,
                    0x7f, 0x5c, 0x51, 0x46, 0x4b},
            {0xd0, 0xdd, 0xca, 0xc7, 0xe4, 0xe9, 0xfe, 0xf3, 0xb8, 0xb5, 0xa2,
                    0xaf, 0x8c, 0x81, 0x96, 0x9b},
            {0xbb, 0xb6, 0xa1, 0xac, 0x8f, 0x82, 0x95, 0x98, 0xd3, 0xde, 0xc9,
                    0xc4, 0xe7, 0xea, 0xfd, 0xf0},
            {0x6b, 0x66, 0x71, 0x7c, 0x5f, 0x52, 0x45, 0x48, 0x03, 0x0e, 0x19,
                    0x14, 0x37, 0x3a, 0x2d, 0x20},
            {0x6d, 0x60, 0x77, 0x7a, 0x59, 0x54, 0x43, 0x4e, 0x05, 0x08, 0x1f,
                    0x12, 0x31, 0x3c, 0x2b, 0x26},
            {0xbd, 0xb0, 0xa7, 0xaa, 0x89, 0x84, 0x93, 0x9e, 0xd5, 0xd8, 0xcf,
                    0xc2, 0xe1, 0xec, 0xfb, 0xf6},
            {0xd6, 0xdb, 0xcc, 0xc1, 0xe2, 0xef, 0xf8, 0xf5, 0xbe, 0xb3, 0xa4,
                    0xa9, 0x8a, 0x87, 0x90, 0x9d},
            {0x06, 0x0b, 0x1c, 0x11, 0x32, 0x3f, 0x28, 0x25, 0x6e, 0x63, 0x74,
                    0x79, 0x5a, 0x57, 0x40, 0x4d},
            {0xda, 0xd7, 0xc0, 0xcd, 0xee, 0xe3, 0xf4, 0xf9, 0xb2, 0xbf, 0xa8,
                    0xa5, 0x86, 0x8b, 0x9c, 0x91},
            {0x0a, 0x07, 0x10, 0x1d, 0x3e, 0x33, 0x24, 0x29, 0x62, 0x6f, 0x78,
                    0x75, 0x56, 0x5b, 0x4c, 0x41},
            {0x61, 0x6c, 0x7b, 0x76, 0x55, 0x58, 0x4f, 0x42, 0x09, 0x04, 0x13,
                    0x1e, 0x3d, 0x30, 0x27, 0x2a},
            {0xb1, 0xbc, 0xab, 0xa6, 0x85, 0x88, 0x9f, 0x92, 0xd9, 0xd4, 0xc3,
                    0xce, 0xed, 0xe0, 0xf7, 0xfa},
            {0xb7, 0xba, 0xad, 0xa0, 0x83, 0x8e, 0x99, 0x94, 0xdf, 0xd2, 0xc5,
                    0xc8, 0xeb, 0xe6, 0xf1, 0xfc},
            {0x67, 0x6a, 0x7d, 0x70, 0x53, 0x5e, 0x49, 0x44, 0x0f, 0x02, 0x15,
                    0x18, 0x3b, 0x36, 0x21, 0x2c},
            {0x0c, 0x01, 0x16, 0x1b, 0x38, 0x35, 0x22, 0x2f, 0x64, 0x69, 0x7e,
                    0x73, 0x50, 0x5d, 0x4a, 0x47},
            {0xdc, 0xd1, 0xc6, 0xcb, 0xe8, 0xe5, 0xf2, 0xff, 0xb4, 0xb9, 0xae,
                    0xa3, 0x80, 0x8d, 0x9a, 0x97}};
    /*
     * rather than implementing galois multiplication, Rijndael implementations
     * simply uses pre-calculated lookup tables to perform the byte
     * multiplication by 14
     */
    private static final int[][] fourteenMultiplicationMarix = {
            {0x00, 0x0e, 0x1c, 0x12, 0x38, 0x36, 0x24, 0x2a, 0x70, 0x7e, 0x6c,
                    0x62, 0x48, 0x46, 0x54, 0x5a},
            {0xe0, 0xee, 0xfc, 0xf2, 0xd8, 0xd6, 0xc4, 0xca, 0x90, 0x9e, 0x8c,
                    0x82, 0xa8, 0xa6, 0xb4, 0xba},
            {0xdb, 0xd5, 0xc7, 0xc9, 0xe3, 0xed, 0xff, 0xf1, 0xab, 0xa5, 0xb7,
                    0xb9, 0x93, 0x9d, 0x8f, 0x81},
            {0x3b, 0x35, 0x27, 0x29, 0x03, 0x0d, 0x1f, 0x11, 0x4b, 0x45, 0x57,
                    0x59, 0x73, 0x7d, 0x6f, 0x61},
            {0xad, 0xa3, 0xb1, 0xbf, 0x95, 0x9b, 0x89, 0x87, 0xdd, 0xd3, 0xc1,
                    0xcf, 0xe5, 0xeb, 0xf9, 0xf7},
            {0x4d, 0x43, 0x51, 0x5f, 0x75, 0x7b, 0x69, 0x67, 0x3d, 0x33, 0x21,
                    0x2f, 0x05, 0x0b, 0x19, 0x17},
            {0x76, 0x78, 0x6a, 0x64, 0x4e, 0x40, 0x52, 0x5c, 0x06, 0x08, 0x1a,
                    0x14, 0x3e, 0x30, 0x22, 0x2c},
            {0x96, 0x98, 0x8a, 0x84, 0xae, 0xa0, 0xb2, 0xbc, 0xe6, 0xe8, 0xfa,
                    0xf4, 0xde, 0xd0, 0xc2, 0xcc},
            {0x41, 0x4f, 0x5d, 0x53, 0x79, 0x77, 0x65, 0x6b, 0x31, 0x3f, 0x2d,
                    0x23, 0x09, 0x07, 0x15, 0x1b},
            {0xa1, 0xaf, 0xbd, 0xb3, 0x99, 0x97, 0x85, 0x8b, 0xd1, 0xdf, 0xcd,
                    0xc3, 0xe9, 0xe7, 0xf5, 0xfb},
            {0x9a, 0x94, 0x86, 0x88, 0xa2, 0xac, 0xbe, 0xb0, 0xea, 0xe4, 0xf6,
                    0xf8, 0xd2, 0xdc, 0xce, 0xc0},
            {0x7a, 0x74, 0x66, 0x68, 0x42, 0x4c, 0x5e, 0x50, 0x0a, 0x04, 0x16,
                    0x18, 0x32, 0x3c, 0x2e, 0x20},
            {0xec, 0xe2, 0xf0, 0xfe, 0xd4, 0xda, 0xc8, 0xc6, 0x9c, 0x92, 0x80,
                    0x8e, 0xa4, 0xaa, 0xb8, 0xb6},
            {0x0c, 0x02, 0x10, 0x1e, 0x34, 0x3a, 0x28, 0x26, 0x7c, 0x72, 0x60,
                    0x6e, 0x44, 0x4a, 0x58, 0x56},
            {0x37, 0x39, 0x2b, 0x25, 0x0f, 0x01, 0x13, 0x1d, 0x47, 0x49, 0x5b,
                    0x55, 0x7f, 0x71, 0x63, 0x6d},
            {0xd7, 0xd9, 0xcb, 0xc5, 0xef, 0xe1, 0xf3, 0xfd, 0xa7, 0xa9, 0xbb,
                    0xb5, 0x9f, 0x91, 0x83, 0x8d}};

    /**
     * The Multiplied result is passed to the mixColumnAddition method and xored
     * to get the sum.
     * MixColumns step can be performed by multiplying a coordinate vector of
     * four numbers in Rijndael's Galois field.
     * @param a Takes input
     * @param b Takes input
     * @return
     */
    private static int mixColumnMultiplication(int a, int b) {
        if (a == 0x09) {
            return ninesMultiplicationMatrix[b / 16][b % 16];
        } else if (a == 0x0b) {
            return elevenMultiplicationMatrix[b / 16][b % 16];
        } else if (a == 0x0d) {
            return thirteenMultiplicationMarix[b / 16][b % 16];
        } else if (a == 0x0e) {
            return fourteenMultiplicationMarix[b / 16][b % 16];
        }
        return 0;
    }

    /**
     * mixColumnAddition
     *
     * This math is done in Rijndael's Galois field, the addition below is
     * actually an exclusive or operation, and then multiplication
     * inputs and output are 4 by 4 matrices of pairs of hex digits and will
     * perform the Mix Column operation of AES to transform the input state into
     * output state. Using multiplications in the Galois field.
     * Returns the added resultant matrix.
     */
    private static String mixColumnAddition(String[][] Key, int[][] mixclmn,
                                            int i, int j) {
        int sum = 0;
        for (int k = 0; k < 4; k++) {
            int a = mixclmn[i][k];
            int b = Integer.parseInt(Key[k][j], 16);
            sum = sum ^ mixColumnMultiplication(a, b);
        }
        String result = String.format("%02x", sum);
        return result.toUpperCase();
    }

    /**
     * inputs and output are 4 by 4 matrices of pairs of hex digits and will
     * perform the Mix Column operation of AES to transform the input state into
     * output state. Using multiplications in the Galois field.
     * Returns the Matrix which is shifted.
     */
    private static String[][] aesMixColumn(String[][] Key) {
        String aesMixColumnMatrix[][] = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                aesMixColumnMatrix[i][j] = mixColumnAddition(Key,
                        mixColumnMatrix, i, j);
            }
        }
        return aesMixColumnMatrix;
    }
}