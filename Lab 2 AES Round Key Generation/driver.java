import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class driver {
	public static void main(String[] args) throws IOException {       
        try{
        	BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Key");
            String keyValue = standardInput.readLine();
            aescipher cipher = new aescipher();
            cipher.inputtingStringIntoKMatrix(keyValue);
        }catch(IOException excep){
            System.err.println("Invalid Format in ");
        }
    }
}