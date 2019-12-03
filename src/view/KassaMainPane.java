package view;


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
        VerkoopPane verkoopPane = new VerkoopPane();
        Tab kassaTab = new Tab("Kassa", verkoopPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane();
        PropertiesPane propertiesPane = new PropertiesPane();
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen", propertiesPane);
        Tab logTab = new Tab("Log");
        ObservableList<Artikel> data = FXCollections.observableArrayList();
        Properties properties = propertiesPane.getInstellingen();
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
        //ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
        //List<Artikel> artikelen = artikelTekstLoadSave.load();
        ArtikelDBContext db = new ArtikelDBContext();
        db.setDBStrategy(ArtikelDBStrategyFactory.createStrategy("InMemory"));
        //ArtikelDBStrategy db = ArtikelDBStrategyFactory.createStrategy("InMemory");
        //HashMap<Integer, Artikel> artikelenMap = db.loadArtikelen();
        db.setLoadSaveStrategy(LoadSaveStrategyFactory.createStrategy(properties.getProperty("method")));
        List<Artikel> artikelen = db.load();
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
        Collections.sort(artikelen, new ComparatorByOmschrijving());
        //artikelen.sort(new ComparatorByOmschrijving());
        int i = 1;
        for(Artikel artikel : artikelen) {
            productOverviewPane.add(new Label(Integer.toString(artikel.getCode())), 0, i+1, 1, 1);
            productOverviewPane.add(new Label(artikel.getOmschrijving()), 1, i+1, 1, 1);
            productOverviewPane.add(new Label(artikel.getArtikelGroep()), 2, i+1, 1, 1);
            productOverviewPane.add(new Label(Double.toString(artikel.getVerkoopprijs())), 3, i+1, 1, 1);
            productOverviewPane.add(new Label(Integer.toString(artikel.getInVoorraad())), 4, i+1, 1, 1);
            i++;
        }

        verkoopPane.getCodeTextField().setOnAction(event -> {
            int code = Integer.parseInt(verkoopPane.getCodeTextField().getText());

            //verkoopPane.getErrorLabel().setText("Niet bestaande code");
            Artikel a = db.get(code);
            if(a != null) {
                System.out.println(a.toString());
                data.add(a);
                verkoopPane.getTableView().setItems(data);
            } else {
                System.out.println("a is null");
                verkoopPane.getErrorLabel().setText("Niet bestaande code");
            }
        });

	    this.setCenter(tabPane);
	}
}
