package view.panels;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;

public class VerkoopPane extends GridPane {
    private static Label enterLabel = new Label("Geef de artikelcode in: ");
    private TextField codeTextField = new TextField();
    private static Button submitBtn = new Button("Bevestig");
    private Label errorLabel = new Label();
    private TableView<model.Artikel> tableView = new TableView<>();
    private Label prijs = new Label("Totale prijs: ");
    private double som = 0;

    TableColumn omschrCol = new TableColumn<model.Artikel, Object>("Omschrijving");
    TableColumn prijsCol = new TableColumn("prijs");
    TableColumn aantalCol = new TableColumn("Aantal");

    public VerkoopPane() {
        this.add(enterLabel,0,0);
        this.add(codeTextField,0,1);
        this.add(errorLabel, 0, 2);
        omschrCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,String>("omschrijving")
        );
        prijsCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,Double>("verkoopprijs")
        );
        aantalCol.setCellValueFactory(
                new PropertyValueFactory<Artikel,Integer>("aantalPerKeer")
        );
        tableView.getColumns().addAll(omschrCol, prijsCol, aantalCol);
        this.add(tableView, 0,3);
        this.add(prijs,0,4);
    }

    public Label getPrijs() {
        return prijs;
    }

    public double getSom() {
        return som;
    }

    public double addSom(double val) {
        this.som += val;
        return this.som;
    }

    public TextField getCodeTextField() {
        return codeTextField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TableView<model.Artikel> getTableView() {
        return tableView;
    }
}
