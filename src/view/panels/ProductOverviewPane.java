package view.panels;

import database.ArtikelDBContext;
import database.ArtikelTekstLoadSave;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;
import model.ComparatorByOmschrijving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table;

	
	
	public ProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Products:"), 0, 0, 1, 1);
        table = new TableView<Artikel>();
        PropertiesPane propertiesPane = new PropertiesPane();
        Properties properties = propertiesPane.getInstellingen();

        TableColumn<Artikel, Integer> colArtikelcode = new TableColumn<Artikel, Integer>("Artikelcode");
        colArtikelcode.setMinWidth(100);
        colArtikelcode.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("Code"));
        TableColumn<Artikel, String> colOmschrijving = new TableColumn<Artikel, String>("Omschrijving");
        colOmschrijving.setMinWidth(100);
        colOmschrijving.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Omschrijving"));
        TableColumn<Artikel, String> colArtikelgroep = new TableColumn<Artikel, String>("Artikelgroep");
        colArtikelgroep.setMinWidth(100);
        colArtikelgroep.setCellValueFactory(new PropertyValueFactory<Artikel, String>("ArtikelGroep"));
        TableColumn<Artikel, Double> colPrijs = new TableColumn<Artikel, Double>("Prijs");
        colPrijs.setMinWidth(100);
        colPrijs.setCellValueFactory(new PropertyValueFactory<Artikel, Double>("Verkoopprijs"));
        TableColumn<Artikel, Integer> colActueleVoorraad = new TableColumn<Artikel, Integer>("Actuele voorraad");
        colActueleVoorraad.setMinWidth(200);
        colActueleVoorraad.setCellValueFactory(new PropertyValueFactory<Artikel, Integer>("InVoorraad"));
        table.getColumns().addAll(colArtikelcode, colOmschrijving, colArtikelgroep, colPrijs, colActueleVoorraad);
        this.add(table, 0, 1);
        ArtikelDBContext db = new ArtikelDBContext();
        db.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
        db.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
        ObservableList<Artikel> data = FXCollections.observableArrayList(db.load());
        data.sort(new ComparatorByOmschrijving());
        table.setItems(data);
    }
}
