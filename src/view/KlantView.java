package view;

import controller.VerkoopController;
import database.ArtikelDBContext;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Artikel;
import model.VerkoopModel;
import view.panels.PropertiesPane;
//import view.domain.Controller;

import java.util.ArrayList;
import java.util.Properties;

public class KlantView {
	private Stage stage = new Stage();

	//private Controller controller;
	private KlantViewPane borderPane;


	public KlantView(VerkoopController verkoopController){
		PropertiesPane propertiesPane = new PropertiesPane();
		Properties properties = propertiesPane.getInstellingen();
		ArtikelDBContext db = new ArtikelDBContext();
		db.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
		db.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
		//VerkoopPane verkoopPane = new VerkoopPane(verkoopController);
		//verkoopController.setVerkoopPane(verkoopPane);

		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		borderPane = new KlantViewPane();
		verkoopController.setKlantViewPane(borderPane);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	/*public void setController(Controller controller) {
		this.controller = controller;
	}*/
	public void setMemory(ArrayList<Artikel> artikels){
		ObservableList<Artikel> observableArrayList =
				FXCollections.observableArrayList(artikels);
		borderPane.getTableView().setItems(observableArrayList);
	}
}
