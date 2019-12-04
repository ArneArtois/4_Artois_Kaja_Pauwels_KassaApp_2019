package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Artikel;
import view.domain.Controller;

import java.util.ArrayList;

public class KlantView {
	private Stage stage = new Stage();

	private Controller controller;
	private KlantViewPane borderPane;

	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		borderPane = new KlantViewPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	public void setMemory(ArrayList<Artikel> artikels){
		ObservableList<Artikel> observableArrayList =
				FXCollections.observableArrayList(artikels);
		borderPane.getTableView().setItems(observableArrayList);
	}
}
