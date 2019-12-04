package view;


import controller.VerkoopController;
import database.ArtikelDBContext;
import database.ArtikelDBInMemory;
import database.ArtikelTekstLoadSave;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import database.strategy.ArtikelDBStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Artikel;
import model.ComparatorByOmschrijving;
import model.Verkoop;
import view.panels.ProductOverviewPane;
import view.panels.PropertiesPane;
import view.panels.VerkoopPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){
	    TabPane tabPane = new TabPane();
        PropertiesPane propertiesPane = new PropertiesPane();
        Tab instellingTab = new Tab("Instellingen", propertiesPane);
        Properties properties = propertiesPane.getInstellingen();
        //VerkoopPane verkoopPane = new VerkoopPane();
        ArtikelDBContext db = new ArtikelDBContext();
        db.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
        //ArtikelDBStrategy db = ArtikelDBStrategyFactory.createStrategy("InMemory");
        //HashMap<Integer, Artikel> artikelenMap = db.loadArtikelen();
        db.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
        Verkoop verkoop = new Verkoop();
        VerkoopController verkoopController = new VerkoopController(verkoop,db);
        VerkoopPane verkoopPane = new VerkoopPane(verkoopController);
        verkoopController.setVerkoopPane(verkoopPane);

        Tab kassaTab = new Tab("Kassa", verkoopPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);

        Tab logTab = new Tab("Log");
        ObservableList<Artikel> data = FXCollections.observableArrayList();

        if(properties.getProperty("method") == null || properties.getProperty("method").trim().isEmpty()) {
            try {
                OutputStream os = new FileOutputStream("src/bestanden/instellingen.properties");
                properties.setProperty("method", "Tekst");
                properties.store(os, "LoadSaveMethod");
                os.close();
            } catch (IOException e) {
                throw new ViewException(e.getMessage());
            }
        }
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
        //artikelen.sort(new ComparatorByOmschrijving());

       /* verkoopPane.getCodeTextField().setOnAction(event -> {
            int code = Integer.parseInt(verkoopPane.getCodeTextField().getText());

            //verkoopPane.getErrorLabel().setText("Niet bestaande code");
            Artikel a = db.get(code);
            if(a != null) {
                System.out.println(a.toString());
                data.add(a);
                verkoopPane.getTableView().setItems(data);
                verkoopPane.getErrorLabel().setText(" ");
                double bedrag = a.getVerkoopprijs();
                verkoopPane.getPrijs().setText("Totale prijs: " + verkoopPane.addSom(bedrag));

            } else {
                System.out.println("a is null");
                verkoopPane.getErrorLabel().setText("Niet bestaande code");
            }
            verkoopPane.getCodeTextField().clear();
        });*/

	    this.setCenter(tabPane);
	}
}
