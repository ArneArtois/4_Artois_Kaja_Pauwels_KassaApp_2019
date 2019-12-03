package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VerkoopPane extends GridPane {
    Label enterLabel = new Label("Geef de artikelcode in: ");
    TextField codeTextField = new TextField();
    Button submitBtn = new Button("Bevestig");
    Label errorLabel = new Label();
    TableView tableView = new TableView();
    public VerkoopPane() {
        this.add(enterLabel,0,0);
        this.add(codeTextField,0,1);
        this.add(errorLabel, 0, 2);
        this.add(tableView, 0,3);
    }

    public TextField getCodeTextField() {
        return codeTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
