package view;


import controller.KlantViewController;
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
    //TODO Mag dit? Niet zeker?
    public static VerkoopController verkoopController;
	public KassaMainPane(){
	    TabPane tabPane = new TabPane();
        PropertiesPane propertiesPane = new PropertiesPane();
        Tab instellingTab = new Tab("Instellingen", propertiesPane);
        Properties properties = propertiesPane.getInstellingen();
        ArtikelDBContext db = new ArtikelDBContext();
        db.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
        db.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
        Verkoop verkoop = new Verkoop();
        verkoopController = new VerkoopController(verkoop,db);
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


	    this.setCenter(tabPane);
	}
}
