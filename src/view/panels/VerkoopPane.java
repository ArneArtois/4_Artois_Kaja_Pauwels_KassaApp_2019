package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VerkoopPane extends GridPane {
    Label enterLabel = new Label("Geef de artikelcode in: ");
    TextField codeTextField = new TextField();
    Button submitBtn = new Button("Bevestig");
    Label errorLabel = new Label();
    public VerkoopPane() {
        this.add(enterLabel,0,0);
        this.add(codeTextField,0,1);
        this.add(submitBtn,0,3);
//        codeTextField.setOnAction(event -> {
//            System.out.println(codeTextField.getText());
//        });
//        submitBtn.setOnAction(event ->{
//            System.out.println(codeTextField.getText());
//        });


    }

    public TextField getCodeTextField() {
        return codeTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
