public class aescipher {
	private static String[][] inputKeyMatrix = new String[4][4];
    public static String[][] generatedKeyMatrix = new String[4][44];
	
    public void inputtingStringIntoKMatrix(String key)
    {   try{
        int i =0;
        for (int column = 0; column < 4; column++) {            
        for (int row = 0; row < 4; row = row + 1) {
        	inputKeyMatrix[row][column] = key.substring(i,i+2);
                i=i+2;
            }
        }
        generatingWHexMatrix();
    }
    catch(Exception ex){
    	System.err.println("Error occured in generation of input 4x4 matrix ");
    }
    }
    public static void generatingWHexMatrix(){
    
    }
}
