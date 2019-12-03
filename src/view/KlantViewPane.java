package view;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Artikel;

public class KlantViewPane extends GridPane {
    private TableView <Artikel> tableView = new TableView<>();

    TableColumn omschrCol = new TableColumn<model.Artikel, Object>("Omschrijving");
    TableColumn prijsCol = new TableColumn("prijs");
    TableColumn aantalCol = new TableColumn("Aantal");

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
        }

        public TableView<model.Artikel> getTableView() {
            return tableView;
        }
    }