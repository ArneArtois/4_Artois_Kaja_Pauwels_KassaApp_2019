package view;

import controller.VerkoopController;
import database.ArtikelDBContext;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panels.KlantViewPane;
import view.panels.PropertiesPane;
//import view.domain.Controller;

import java.util.Properties;

public class KlantView {
	private Stage stage = new Stage();

	//private Controller controller;
	private KlantViewPane borderPane;


	public KlantView(VerkoopController verkoopController, KlantViewPane klantViewPane){
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
		verkoopController.setKlantViewPane(klantViewPane);
		klantViewPane.prefHeightProperty().bind(scene.heightProperty());
		klantViewPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(klantViewPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}
