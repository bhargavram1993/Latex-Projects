package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.setTitle("AES");

        //Creating a grid pane of primary stage
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setPrefWidth(100);
        grid.setHgap(15);


        scene.setRoot(grid);

        //Creating text field for the input
        final TextField input = new TextField();
        input.setPromptText("Enter Your Plain Text or Cipher.");
        input.setPrefColumnCount(70);
        input.getText();
        GridPane.setConstraints(input, 0, 0);
        grid.getChildren().add(input);

        //Creating text field for the key
        final TextField inputKey = new TextField();
        inputKey.setPromptText("Enter your Key.");
        input.setPrefColumnCount(40);
        GridPane.setConstraints(inputKey, 0, 1);
        grid.getChildren().add(inputKey);

        //Creating text field for displaying output
        final TextField output = new TextField();
        output.setPrefColumnCount(40);
        output.setPromptText("Output");
        GridPane.setConstraints(output, 0, 2);
        grid.getChildren().add(output);

        //Creating a button for encryption
        Button encrypt = new Button("Encryption");
        GridPane.setConstraints(encrypt, 1, 0);
        grid.getChildren().add(encrypt);

        //Creating a button for decryption
        Button decryption = new Button("Decryption");
        GridPane.setConstraints(decryption, 1, 1);
        grid.getChildren().add(decryption);

        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);



        // Setting an action for encrypt button which invoke aes method in AesCipher for encryption
        //and displays output on the third row in the scene.

        encrypt.setOnAction((event) -> {
            String text = input.getText();
            String key= inputKey.getText();

            //Checks whether the input fields empty or not.
            //if empty will not do any encryption prints a label to enter the required fields.
            if ((input.getText() != null && !input.getText().isEmpty())) {
                if ((inputKey.getText() != null && !inputKey.getText().isEmpty())){
                    AesCipher encryption = new AesCipher();
                    encryption.aes(text, key);
                    output.setText(AesCipher.cipher.toString());
                }

            } else if ((inputKey.getText() != null && !inputKey.getText().isEmpty())){
                label.setText("Enter the fields.");
            }else {
                label.setText("Enter the fields.");

            }

        });

        // Setting an action for decrytption button which invoke aes method in Decryption for
        //decrypting the cipher text and displays output on the third row in the scene.
        decryption.setOnAction((event) -> {
            String text = input.getText();
            String key= inputKey.getText();
             //Checks whether the input fields empty or not.
            //if empty will not do any encryption prints a label to enter the required fields.
            if ((input.getText() != null && !input.getText().isEmpty())) {
                if ((inputKey.getText() != null && !inputKey.getText().isEmpty())){
                    Decryption decrypt = new Decryption();
                    decrypt.aes(text, key);
                    output.setText(Decryption.plain.toString());
                }

            } else if ((inputKey.getText() != null && !inputKey.getText().isEmpty())){
                label.setText("Enter the fields.");
            }else {
                label.setText("Enter the fields.");

            }

        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}