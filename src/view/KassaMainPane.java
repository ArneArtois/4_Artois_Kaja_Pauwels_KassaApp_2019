package view;


import controller.VerkoopController;
import database.ArtikelDBContext;
import database.factory.ArtikelDBStrategyFactory;
import database.factory.LoadSaveStrategyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Artikel;
import model.VerkoopModel;
import view.panels.ProductOverviewPane;
import view.panels.PropertiesPane;
import view.panels.VerkoopPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class KassaMainPane extends BorderPane {
    //TODO Mag dit? Niet zeker?
    public VerkoopController verkoopController;
    public ArtikelDBContext context;
    private Properties properties;
    private PropertiesPane propertiesPane;
	public KassaMainPane(VerkoopController verkoopController, ArtikelDBContext context, PropertiesPane propertiesPane, Properties properties){
	    this.context = context;
	    this.propertiesPane = propertiesPane;
	    this.verkoopController = verkoopController;
	    this.properties = properties;
	    TabPane tabPane = new TabPane();
        Tab instellingTab = new Tab("Instellingen", propertiesPane);
        this.context.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
        this.context.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));

        Tab kassaTab = new Tab("Kassa", verkoopController.getVerkoopPane());
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
