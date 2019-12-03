package view.panels;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class VerkoopPane extends GridPane {
    Label enterLabel = new Label("Geef de artikelcode in: ");
    TextField codeTextField = new TextField();
    Button submitBtn = new Button("Bevestig");
    Label errorLabel = new Label();
    TableView tableView = new TableView();
    TableColumn omschrCol = new TableColumn("Omschrijving");
    TableColumn prijsCol = new TableColumn("Prijs");
    TableColumn aantalCol = new TableColumn("Aantal");
    public VerkoopPane() {
        this.add(enterLabel,0,0);
        this.add(codeTextField,0,1);
        this.add(errorLabel, 0, 2);
        tableView.getColumns().addAll(omschrCol, prijsCol, aantalCol);
        this.add(tableView, 0,3);
    }

    public TableColumn getOmschrCol() {
        return omschrCol;
    }

    public TableColumn getPrijsCol() {
        return prijsCol;
    }

    public TableColumn getAantalCol() {
        return aantalCol;
    }

    public TextField getCodeTextField() {
        return codeTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TableView getTableView() {
        return tableView;
    }
}
