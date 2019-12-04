package view.panels;

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


public class ProductOverviewPane extends GridPane {
	//private TableView<Product> table;
	
	
	public ProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(new Label("Artikelcode"), 0, 1, 1, 1);
		this.add(new Label("Omschrijving"), 1, 1, 1, 1);
		this.add(new Label("Artikelgroep"), 2, 1, 1, 1);
		this.add(new Label("Prijs"), 3, 1, 1, 1);
		this.add(new Label("Actuele voorraad"), 4, 1, 1, 1);
	}
	
	

}
