package view;

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
import model.Verkoop;
import view.panels.PropertiesPane;
import view.panels.VerkoopPane;

import java.util.List;
import java.util.Properties;

public class KlantViewPane extends GridPane {
    private TableView <Artikel> tableView = new TableView<>();

    TableColumn omschrCol = new TableColumn<model.Artikel, Object>("Omschrijving");
    TableColumn prijsCol = new TableColumn("prijs");
    TableColumn aantalCol = new TableColumn("Aantal");
    Label prijs = new Label("Totale prijs: ");

    public KlantViewPane(){

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
            this.add(prijs, 0,4);
        }

        public TableView<model.Artikel> getTableView() {
            return tableView;
        }

    public void updateDisplay(double prijs, List<Artikel> artikelen) {
        this.prijs.setText("Totale prijs: " + prijs);
        ObservableList<Artikel> observableList =  FXCollections.observableArrayList(artikelen);
        this.tableView.setItems(observableList);
    }
}