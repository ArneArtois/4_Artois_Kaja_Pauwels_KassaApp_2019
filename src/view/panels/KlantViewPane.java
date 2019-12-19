package view.panels;

import controller.VerkoopController;
import database.ArtikelDBContext;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.VerkoopModel;
import view.panels.PropertiesPane;
import view.panels.VerkoopPane;

import java.util.*;

public class KlantViewPane extends GridPane {
    private TableView<Artikel> tableView = new TableView<>();

    TableColumn omschrCol = new TableColumn<model.Artikel, Object>("Omschrijving");
    TableColumn prijsCol = new TableColumn("prijs");
    TableColumn aantalCol = new TableColumn("Aantal");
    Label prijs = new Label("Totale prijs: ");
    Label korting = new Label();
    Label teBetalen = new Label();
    private double prijsDouble = 0;
    private Button betaal = new Button("Betalen");
    private VerkoopController verkoopController;

    public KlantViewPane(VerkoopController verkoopController) {
        this.verkoopController = verkoopController;

        omschrCol.setCellValueFactory(
                new PropertyValueFactory<Artikel, String>("omschrijving")
        );
        prijsCol.setCellValueFactory(
                new PropertyValueFactory<Artikel, Double>("verkoopprijs")
        );
        aantalCol.setCellValueFactory(
                new PropertyValueFactory<Artikel, Integer>("aantalPerKeer")
        );
        tableView.getColumns().addAll(omschrCol, prijsCol, aantalCol);
        this.add(tableView, 0, 3);
        this.add(prijs, 0, 4);
        this.add(korting, 0, 5);
        this.add(teBetalen, 0, 6);
        this.add(betaal, 0, 7);
        this.betaal.setOnAction(event -> {
            verkoopController.betaalVerkoop();
        });
    }

    public TableView<model.Artikel> getTableView() {
        return tableView;
    }

    public void updateDisplay(double prijs, List<Artikel> artikelen) {
        this.prijs.setText("Totale prijs: " + prijs);
        this.prijsDouble = prijs;
        ObservableList<Artikel> observableList = FXCollections.observableArrayList(verkoopController.convertToCustomerView(artikelen));
        this.tableView.setItems(observableList);
        this.tableView.refresh();
    }
    public void afsluit(){
        this.korting.setText("Korting: " + verkoopController.getKorting());
        this.teBetalen.setText("Te betalen: " + (prijsDouble - verkoopController.getKorting()));
    }
}